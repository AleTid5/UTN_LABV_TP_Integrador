package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Builders.TransferBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.TransferException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/transfers")
@Controller
public class TransferController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET, value = "/thirdParty")
    public String transferList(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("accounts", AccountService.getAccounts((Integer) request.getSession().getAttribute("id")));

        return "Authorized/Transfers/thirdParty";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/thirdParty")
    public void saveTransfer(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "transfers/thirdParty";

        try {
            Movement movement = TransferBuilder.build(request);

            MovementService.saveMovement(movement);
            AccountService.updateAccount(movement.getOriginAccount());
            AccountService.updateAccount(movement.getDestinationAccount());
            parameters.put("successCode", SuccessCodeEnum.TRANSFER_SUCCESSFUL);
        } catch (AccountException e) {
            parameters.put("errorCode", ErrorCodeEnum.valueOf("INVALID_" + e.getField().toString()));
        } catch (TransferException e) {
            parameters.put("errorCode", ErrorCodeEnum.valueOf("INVALID_TRANSFER_" + e.getField().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/own")
    public String OnTransferOwn(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("accounts", AccountService.getAccounts((Integer) request.getSession().getAttribute("id")));

        return "Authorized/Transfers/own";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/own")
    public void OnTransferOwn(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "transfers/own";

        try {
            Movement movement = TransferBuilder.buildMovement(request);
            MovementService.saveMovement(movement);

            AccountService.updateAccount(movement.getOriginAccount());
            AccountService.updateAccount(movement.getDestinationAccount());
            parameters.put("successCode", SuccessCodeEnum.TRANSFER_SUCCESSFUL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }
}
