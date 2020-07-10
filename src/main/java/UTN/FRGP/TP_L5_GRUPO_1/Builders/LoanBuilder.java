package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;

import javax.servlet.http.HttpServletRequest;

public abstract class LoanBuilder {
    public static Loan build(HttpServletRequest request) {
        return build(request, new Loan());
    }
    public static Loan build(HttpServletRequest request, Loan loan) {
        loan.setAccount(AccountService.getAccount(request.getParameter("originAccount")));
        loan.setAmount(Double.parseDouble(request.getParameter("amount")));
        loan.setFeesToPay(Integer.parseInt(request.getParameter("feesToPay")));
        loan.setFeeValue();

        return loan;
    }
}
