package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Builders.TransferBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/tranfers")
@Controller
public class TransferController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET, value = "/thirdParty")
    public String transferList(ModelMap modelMap ) {

        return "/Authorized/Transfers/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/thirdParty")
    public void saveTransfer(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "transfers";

        try {
            Map.Entry<Account,Account> accounts = TransferBuilder.build(request).entrySet().iterator().next();
            Movement movement = new Movement();
            MovementService.saveMovement();
            TransferService.saveTransfer(accounts,Double.parseDouble(request.getParameter("amount")),request.getParameter("concept"));
            parameters.put("successCode", SuccessCodeEnum.TRANSFER_CREATED);
        } catch (AccountException e) {
            parameters.put("errorCode", ErrorCodeEnum.valueOf("INVALID_" + e.getField().toString()));
            url += "/add";
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }
}
