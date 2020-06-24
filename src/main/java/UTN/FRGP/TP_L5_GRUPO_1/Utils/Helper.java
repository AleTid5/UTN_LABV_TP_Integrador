package UTN.FRGP.TP_L5_GRUPO_1.Utils;

import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Service
public abstract class Helper {
    public static Customer buildCustomerFromRequest(HttpServletRequest request) throws UserException, ParseException {
        return buildCustomerFromRequest(request, new Customer());
    }
    public static Customer buildCustomerFromRequest(HttpServletRequest request, Customer customer) throws UserException, ParseException {
        customer.setName(request.getParameter("name"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setUserName(request.getParameter("userName"));
        customer.setDni(Integer.parseInt(request.getParameter("dni")));
        customer.setEmail(request.getParameter("email"));
        customer.setBornDate(customer.parseBornDate(request.getParameter("bornDate")));
        customer.setAddress(request.getParameter("address"));
        customer.setLocality(Integer.parseInt(request.getParameter("locality")));
        customer.setMaxLoanAmount(Integer.parseInt(request.getParameter("maxLoanAmount")));
        customer.setGender(request.getParameter("gender"));

        return customer;
    }
    public static Account buildAccountFromRequest(HttpServletRequest request) throws AccountException, ParseException, NumberFormatException, UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException {
        return buildAccountFromRequest(request, new Account());
    }
    
    public static Account buildAccountFromRequest(HttpServletRequest request, Account account) throws AccountException, ParseException, NumberFormatException, UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException {
    	account.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));
    	account.setAlias(request.getParameter("alias"));
        account.setAccountType(Integer.parseInt(request.getParameter("accountType")));
        account.setCustomer(Integer.parseInt(request.getParameter("customer")));
    	return account;
    }
}
