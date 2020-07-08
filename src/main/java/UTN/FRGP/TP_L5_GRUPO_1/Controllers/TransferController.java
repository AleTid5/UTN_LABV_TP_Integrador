package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Builders.CustomerBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import org.hibernate.exception.ConstraintViolationException;
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

    @RequestMapping(method = RequestMethod.GET)
    public String transferList(ModelMap modelMap) {

        return "/Authorized/Transfers/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void saveTransfer(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "transfers";

        try {
            Transfer transfer = TransferBuilder.build(request);

            TransferService.saveTransfer(transfer);
            parameters.put("successCode", SuccessCodeEnum.TRANSFER_CREATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCodeEnum.DUPLICATED_CUSTOMER);
            url += "/add";
        } catch (UserException e) {
            parameters.put("errorCode", ErrorCodeEnum.valueOf("INVALID_" + e.getField().toString()));
            url += "/add";
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }
}
