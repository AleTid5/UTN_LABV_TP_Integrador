package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public abstract class LoanBuilder {
    public static Loan build(HttpServletRequest request) throws ParseException {
        return build(request, new Loan());
    }
    public static Loan build(HttpServletRequest request, Loan loan) throws ParseException {
        loan.setAccount(AccountService.getAccount(request.getParameter("originAccount")));
        loan.setAmount(Integer.parseInt(request.getParameter("amount")));
        loan.setFeesToPay(Integer.parseInt(request.getParameter("feesToPay")));
        loan.setEndDate(request.getParameter("endDate"));
        loan.setFeeValue();

        return loan;
    }
}
