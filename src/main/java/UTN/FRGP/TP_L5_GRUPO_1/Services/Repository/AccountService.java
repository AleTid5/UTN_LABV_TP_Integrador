package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AccountService {

    @Autowired
    private static Session session;

    @Autowired
    private static List<Account> accounts;

    public static List<Account> getAccounts() {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(Account.class).add(Restrictions.eq("isActive", true)).list();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }

        return accounts;
    }
    
    public static Account getAccountById(Integer accountId) { ;
    Account account = null;

    try {
        session = SessionService.getSession();
        account = (Account) session.createCriteria(Account.class).add(Restrictions.eq("id", accountId)).uniqueResult();
        SessionService.commitSession(session);
    } catch (Exception e) {
        SessionService.rollbackSession(session);
    }

    return account;
}

    public static void saveAccount(Account account) {
        try {
            session = SessionService.getSession();
            session.save(account);
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            throw e;
        }
    }
    public static JsonResponse removeAccount(Account account) {
        try {
            session = SessionService.getSession();
            session.update(account);
            session.flush();
            SessionService.commitSession(session);
            return new JsonResponse();
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            return new JsonResponse(false);
        }
    }
}
