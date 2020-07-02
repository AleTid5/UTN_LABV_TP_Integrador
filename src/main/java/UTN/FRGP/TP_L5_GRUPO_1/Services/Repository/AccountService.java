package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Interfaces.iAccount;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AccountService implements iAccount {

    @Autowired
    private static Session session;

    @Autowired
    private static List<Account> accounts;

    public static List<Account> getAccounts() {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(Account.class).add(Restrictions.eq("isActive", true)).list();
        } finally {
            SessionService.commitSession(session);
        }

        return accounts;
    }

    public static Account getAccountByCBU(String accountCBU) { ;
        Account account;

        try {
            session = SessionService.getSession();
            account = (Account) session.createCriteria(Account.class).add(Restrictions.eq("CBU", accountCBU)).uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return account;
    }

    public static void canUserHaveAnotherAccount(Account account) throws AccountException {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(Account.class)
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

    public static void updateAccount(Account account) {
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

    public static JsonResponse removeAccount(Account account) {
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
}
