package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.LoanService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/loans")
@Controller
public class LoanController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET)
    public String loanList(ModelMap modelMap, HttpServletRequest request) {
        if ((Boolean) request.getSession().getAttribute("isAdministrator")) {
            this.hydrateLoans(modelMap);

            return "/Authorized/Loans/index";
        }

        this.hydrateLoans(modelMap, (Integer) request.getSession().getAttribute("id"));
        return "/Authorized/Loans/customerIndex";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/approve/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String approveLoan(@PathVariable("id") Integer loanId) {
        return new Gson().toJson(new JsonResponse(LoanService.approveLoan(LoanService.getLoan(loanId), true)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String rejectLoan(@PathVariable("id") Integer loanId) {
        return new Gson().toJson(new JsonResponse(LoanService.approveLoan(LoanService.getLoan(loanId), false)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pay/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String payLoan(@PathVariable("id") Integer loanId, HttpServletRequest request) {
        return new Gson().toJson(new JsonResponse(LoanService.payLoan(LoanService.getLoan(loanId), AccountService.getAccount(request.getParameter("account")))));
    }

    private void hydrateLoans(ModelMap modelMap) {
        modelMap.addAttribute("unseenLoans", LoanService.getLoans(null));
        modelMap.addAttribute("approvedLoans", LoanService.getLoans(true));
        modelMap.addAttribute("rejectedLoans", LoanService.getLoans(false));
    }

    private void hydrateLoans(ModelMap modelMap, Integer customerId) {
        modelMap.addAttribute("approvedLoans", LoanService.getLoansByCustomerId(customerId));
        modelMap.addAttribute("accounts", AccountService.getAccounts(customerId));
    }
}
