package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class CustomerService {

    @Autowired
    private static Session session;

    @Autowired
    private static List<Customer> customers;

    public static List<Customer> getCustomers() {
        try {
            session = SessionService.getSession();

            customers = session.createCriteria(Customer.class).list();

            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }
        
        return customers;
    }

    public static void saveCustomer(Customer customer) {
        try {
            session = SessionService.getSession();

            session.save(customer);

            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            throw e;
        }
    }
}
