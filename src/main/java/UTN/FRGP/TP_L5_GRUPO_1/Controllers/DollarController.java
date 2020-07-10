package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Builders.DollarBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Builders.MovementBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CurrencyService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/dollars")
@Controller
public class DollarController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET, value = "/buy")
    public String buyDollarsForm(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("accounts", AccountService.getAccounts((Integer) request.getSession().getAttribute("id")));
        modelMap.addAttribute("purchaseValue", CurrencyService.getCurrencyValueByCurrencyTypeName("USD").getPurchaseValue());

        return "Authorized/Dollars/buy";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/buy")
    public void buyDollars(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "dollars/buy";

        try {
            Movement movement = DollarBuilder.buildDollarPurchase(request);

            MovementService.saveMovement(movement);
            AccountService.updateAccount(movement.getOriginAccount());
            AccountService.updateAccount(movement.getDestinationAccount());
            parameters.put("successCode", SuccessCodeEnum.DOLLARS_PURCHASED);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sell")
    public String sellDollarsForm(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("accounts", AccountService.getAccounts((Integer) request.getSession().getAttribute("id")));
        modelMap.addAttribute("saleValue", CurrencyService.getCurrencyValueByCurrencyTypeName("USD").getSaleValue());

        return "Authorized/Dollars/sell";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sell")
    public void sellDollars(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "dollars/sell";

        try {
            Movement movement = DollarBuilder.buildDollarSale(request);

            MovementService.saveMovement(movement);
            AccountService.updateAccount(movement.getOriginAccount());
            AccountService.updateAccount(movement.getDestinationAccount());
            parameters.put("successCode", SuccessCodeEnum.DOLLARS_SOLD);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }
}
