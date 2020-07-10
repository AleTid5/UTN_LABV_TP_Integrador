package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Builders.LoanBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
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
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.GET, value = "/request")
    public String moneyRequest(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("accounts", AccountService.getAccounts((Integer) request.getSession().getAttribute("id")));

        return "/Authorized/Loans/request";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/request")
    public void loanRequest(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "loans";

        try {
            LoanService.saveLoan(LoanBuilder.build(request));
            parameters.put("successCode", SuccessCodeEnum.LOAN_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/approve/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String approveLoan(@PathVariable("id") Integer loanId, HttpServletRequest request) {
        return new Gson().toJson(new JsonResponse(LoanService.approveLoan(LoanService.getLoan(loanId), true, (Integer) request.getSession().getAttribute("id"))));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String rejectLoan(@PathVariable("id") Integer loanId, HttpServletRequest request) {
        return new Gson().toJson(new JsonResponse(LoanService.approveLoan(LoanService.getLoan(loanId), false, (Integer) request.getSession().getAttribute("id"))));
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
        modelMap.addAttribute("approvedLoans", LoanService.getLoansByCustomerId(true, customerId));
        modelMap.addAttribute("unseenLoans", LoanService.getLoansByCustomerId(null, customerId));
        modelMap.addAttribute("rejectedLoans", LoanService.getLoansByCustomerId(false, customerId));
        modelMap.addAttribute("accounts", AccountService.getAccounts(customerId));
    }
}
