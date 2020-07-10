package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Builders.AccountBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountTypeService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementService;
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
import java.util.List;
import java.util.Map;

@RequestMapping("/accounts")
@Controller
public class AccountController {

	@Autowired
    AbstractController abstractController;
	
    @RequestMapping(method = RequestMethod.GET)
    public String accountList(ModelMap modelMap, HttpServletRequest request) {
        if ((Boolean) request.getSession().getAttribute("isAdministrator")) {
            modelMap.addAttribute("accounts", AccountService.getAccounts());

            return "/Authorized/Accounts/index";
        }

        List<Account> accounts = AccountService.getHistory((Integer) request.getSession().getAttribute("id"));

        modelMap.addAttribute("accounts", accounts);
        modelMap.addAttribute("movements", MovementService.getMovements(accounts));

        return "/Authorized/Accounts/indexHistory";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String createAccount(ModelMap modelMap, HttpServletRequest request) {
        try {
            abstractController.validateIsAdministrator(request.getSession());
            modelMap.addAttribute("customers", CustomerService.getCustomers());
            modelMap.addAttribute("accountTypes", AccountTypeService.getAccountTypes());

            return "/Authorized/Accounts/add";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/Unauthorized/Login/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void saveAccount(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "accounts";

        try {
            abstractController.validateIsAdministrator(request.getSession());
            Account account = AccountBuilder.build(request);
            AccountService.saveAccount(account);
            parameters.put("successCode", SuccessCodeEnum.ACCOUNT_CREATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCodeEnum.DUPLICATED_ACCOUNT);
            url += "/add";
        } catch (AccountException e) {
            parameters.put("errorCode", ErrorCodeEnum.ACCOUNTS_MAX_LIMIT);
            url += "/add";
        } catch (Exception e) {
            url = "";
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{cbu}")
    public String editCustomer(@PathVariable("cbu") String accountCBU, ModelMap modelMap, HttpServletRequest request) {
        try {
            abstractController.validateIsAdministrator(request.getSession());
            Account account = AccountService.getAccount(accountCBU);
            modelMap.addAttribute("account", account);
            modelMap.addAttribute("customers", CustomerService.getCustomers());
            modelMap.addAttribute("accountTypes", AccountTypeService.getAccountTypes());

            return "/Authorized/Accounts/edit";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/Unauthorized/Login/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{cbu}")
    public void updateCustomer(@PathVariable("cbu") String accountCBU, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "accounts";

        try {
            abstractController.validateIsAdministrator(request.getSession());
            Account account = AccountBuilder.build(request, AccountService.getAccount(accountCBU));

            AccountService.updateAccount(account);
            parameters.put("successCode", SuccessCodeEnum.ACCOUNT_UPDATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCodeEnum.DUPLICATED_ACCOUNT);
            url += "/edit/" + accountCBU;
        } catch (AccountException e) {
            parameters.put("errorCode", ErrorCodeEnum.valueOf("INVALID_" + e.getField().toString()));
            url += "/edit/" + accountCBU;
        }  catch (Exception e) {
            url = "";
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{cbu}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteAccount(@PathVariable("cbu") String accountCBU, HttpServletRequest request) {
        try {
            abstractController.validateIsAdministrator(request.getSession());

            return new Gson().toJson(AccountService.removeAccount(AccountService.getAccount(accountCBU)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/Unauthorized/Login/index";
    }
}
