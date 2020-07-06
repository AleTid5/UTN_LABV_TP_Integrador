package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;

import javax.servlet.http.HttpServletRequest;

public abstract class AccountBuilder {
    public static Account build(HttpServletRequest request) throws NumberFormatException, AccountException {
        return build(request, new Account());
    }

    public static Account build(HttpServletRequest request, Account account) throws NumberFormatException, AccountException {
        account.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));
        account.setAlias(request.getParameter("alias"));
        account.setAccountType(Integer.parseInt(request.getParameter("accountType")));
        account.setCustomer(Integer.parseInt(request.getParameter("customer")));

        return account;
    }
}
