package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public abstract class CustomerBuilder {
    public static Customer build(HttpServletRequest request) throws UserException, ParseException {
        return build(request, new Customer());
    }
    public static Customer build(HttpServletRequest request, Customer customer) throws UserException, ParseException {
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
}
