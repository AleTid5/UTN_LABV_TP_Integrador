package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;

import java.util.List;

public class CustomerService {
    public static List<Customer> getCustomers() {
        List<Customer> customers = null;
        Session session = null;

        try {
            session = SessionService.getSession();

            customers = session.createCriteria(Customer.class).list();

            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }
        
        return customers;
    }
}
