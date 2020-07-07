package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Interfaces.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AccountService implements Account {

    @Autowired
    private static Session session;

    @Autowired
    private static List<UTN.FRGP.TP_L5_GRUPO_1.Models.Account> accounts;

    public static List<UTN.FRGP.TP_L5_GRUPO_1.Models.Account> getAccounts() {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(UTN.FRGP.TP_L5_GRUPO_1.Models.Account.class)
                    .add(Restrictions.eq("isActive", true))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return accounts;
    }

    public static List<UTN.FRGP.TP_L5_GRUPO_1.Models.Account> getHistory(Integer customerId) {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(UTN.FRGP.TP_L5_GRUPO_1.Models.Account.class)
                    .add(Restrictions.eq("isActive", true))
                    .add(Restrictions.eq("customer.id", customerId))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return accounts;
    }

    public static List<UTN.FRGP.TP_L5_GRUPO_1.Models.Account> getAccounts(Integer customerId) { ;
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(UTN.FRGP.TP_L5_GRUPO_1.Models.Account.class)
                    .add(Restrictions.eq("isActive", true))
                    .add(Restrictions.eq("customer.id", customerId))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return accounts;
    }

    public static UTN.FRGP.TP_L5_GRUPO_1.Models.Account getAccount(String accountCBU) { ;
        UTN.FRGP.TP_L5_GRUPO_1.Models.Account account;

        try {
            session = SessionService.getSession();
            account = (UTN.FRGP.TP_L5_GRUPO_1.Models.Account) session.createCriteria(UTN.FRGP.TP_L5_GRUPO_1.Models.Account.class)
                    .add(Restrictions.eq("CBU", accountCBU))
                    .uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return account;
    }

    public static void saveAccount(UTN.FRGP.TP_L5_GRUPO_1.Models.Account account) throws AccountException {
        try {
            AccountService.canUserHaveAnotherAccount(account);
            session = SessionService.getSession();
            session.save(account);
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            throw e;
        }
    }

    public static void updateAccount(UTN.FRGP.TP_L5_GRUPO_1.Models.Account account) {
        try {
            session = SessionService.getSession();
            session.update(account);
            session.flush();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            throw e;
        }
    }

    public static JsonResponse removeAccount(UTN.FRGP.TP_L5_GRUPO_1.Models.Account account) {
        try {
            account.setIsActive(false);
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

    private static void canUserHaveAnotherAccount(UTN.FRGP.TP_L5_GRUPO_1.Models.Account account) throws AccountException {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(UTN.FRGP.TP_L5_GRUPO_1.Models.Account.class)
                    .add(Restrictions.eq("customer.id", account.getCustomer().getId()))
                    .add(Restrictions.eq("isActive", true))
                    .list();

            if (MAX_ACCOUNTS_ALLOWED.equals(accounts.size())) {
                throw new AccountException(AccountEnum.CUSTOMER);
            }
        } finally {
            SessionService.commitSession(session);
        }
    }
}
