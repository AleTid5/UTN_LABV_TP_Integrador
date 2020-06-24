package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCode;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCode;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountTypeService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.Helper;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@RequestMapping("/accounts")
@Controller
public class AccountController {
	private static Session session;
	
	@Autowired
    AbstractController abstractController;	
	
    @RequestMapping(method = RequestMethod.GET)
    public String accountList(ModelMap modelMap) {
        modelMap.addAttribute("accounts", AccountService.getAccounts());

        return "/Authorized/Accounts/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String createAccount(ModelMap modelMap) {
        modelMap.addAttribute("accountTypes", AccountTypeService.getAccounts());

        return "/Authorized/Accounts/add";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void saveAccount(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "accounts";

        try {
            Account account = Helper.buildAccountFromRequest(request);
            AccountService.saveAccount(account);
            parameters.put("successCode", SuccessCode.ACCOUNT_CREATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCode.DUPLICATED_ACCOUNT);
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
        Account account = AccountService.getAccountById(accountId);
        account.setIsActive(false);

        return new Gson().toJson(AccountService.removeAccount(account));
    }
}
