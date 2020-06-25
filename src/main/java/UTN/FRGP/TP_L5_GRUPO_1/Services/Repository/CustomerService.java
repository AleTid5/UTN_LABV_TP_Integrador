package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
            customers = session.createCriteria(Customer.class).add(Restrictions.eq("isActive", true)).list();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }
        
        return customers;
    }

    public static Customer getCustomerById(Integer userId) { ;
        Customer customer = null;

        try {
            session = SessionService.getSession();
            customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("id", userId)).uniqueResult();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }

        return customer;
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

    public static void updateCustomer(Customer customer) {
        try {
            session = SessionService.getSession();
            session.update(customer);
            session.flush();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            throw e;
        }
    }

    public static JsonResponse removeCustomer(Customer customer) {
        try {
            customer.setIsActive(false);
            session = SessionService.getSession();
            session.update(customer);
            session.flush();
            SessionService.commitSession(session);
            return new JsonResponse();
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            return new JsonResponse(false);
        }
    }
}
