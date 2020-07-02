package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

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

@RequestMapping("/loans")
@Controller
public class LoanController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET)
    public String loanList(ModelMap modelMap) {
        modelMap.addAttribute("unseenLoans", LoanService.getLoans(null));
        modelMap.addAttribute("approvedLoans", LoanService.getLoans(true));
        modelMap.addAttribute("rejectedLoans", LoanService.getLoans(false));

        return "/Authorized/Loans/index";
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
}
