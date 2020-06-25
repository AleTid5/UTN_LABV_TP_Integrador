package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountTypeService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.Helper;
import com.google.gson.Gson;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/accounts")
@Controller
public class AccountController {

	@Autowired
    AbstractController abstractController;	
	
    @RequestMapping(method = RequestMethod.GET)
    public String accountList(ModelMap modelMap) {
        modelMap.addAttribute("accounts", AccountService.getAccounts());

        return "/Authorized/Accounts/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String createAccount(ModelMap modelMap) {
        modelMap.addAttribute("customers", CustomerService.getCustomers());
        modelMap.addAttribute("accountTypes", AccountTypeService.getAccountTypes());

        return "/Authorized/Accounts/add";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void saveAccount(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "accounts";

        try {
            Account account = Helper.buildAccountFromRequest(request);
            AccountService.saveAccount(account);
            parameters.put("successCode", SuccessCodeEnum.ACCOUNT_CREATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCodeEnum.DUPLICATED_ACCOUNT);
            url += "/add";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteAccount(@PathVariable("id") int accountId) {
        return new Gson().toJson(AccountService.removeAccount(AccountService.getAccountById(accountId)));
    }
}
