package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.LoanService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET)
    public String dashboard(ModelMap modelMap, HttpServletRequest request) {
        if ((Boolean) request.getSession().getAttribute("isAdministrator")) {
            this.hydrateDashboard(modelMap);
            return "/Authorized/Dashboard/indexAdmin";
        }

        this.hydrateDashboard(modelMap, (Integer) request.getSession().getAttribute("id"));
        return "/Authorized/Dashboard/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createdAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String dashboardCreatedAccounts(HttpServletRequest request) {
        return new Gson().toJson(new JsonResponse(AccountService.getCreatedAccounts(request.getParameter("from"), request.getParameter("to"))));
    }

    private void hydrateDashboard(ModelMap modelMap) {
        modelMap.addAttribute("customers", CustomerService.getCustomers().size());
        modelMap.addAttribute("movements", MovementService.getMovements().size());
        modelMap.addAttribute("approvedLoans", LoanService.getLoans(true).size());
        modelMap.addAttribute("rejectedLoans", LoanService.getLoans(false).size());
    }

    private void hydrateDashboard(ModelMap modelMap, Integer customerId) {
        List<Account> accounts =  AccountService.getAccounts(customerId);
        modelMap.addAttribute("accounts", accounts.size());
        modelMap.addAttribute("approvedLoans", LoanService.getLoansByCustomerId(customerId).size());
        modelMap.addAttribute("movements", MovementService.getMovements(accounts).size());
    }
}
