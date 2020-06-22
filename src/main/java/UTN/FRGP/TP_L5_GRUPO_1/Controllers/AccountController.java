package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/accounts")
@Controller
public class AccountController {

    @RequestMapping(method = RequestMethod.GET)
    public String accountList(ModelMap modelMap) {
        modelMap.addAttribute("accounts", AccountService.getAccounts());

        return "/Authorized/Accounts/index";
    }



}
