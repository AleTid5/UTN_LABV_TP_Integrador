package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Interfaces.iAccount;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
public abstract class AccountService implements iAccount {

    @Autowired
    private static Session session;

    @Autowired
    private static List<Account> accounts;

    public static List<Account> getAccounts() {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(Account.class)
                    .add(Restrictions.eq("isActive", true))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return accounts;
    }

    public static List<Account> getAccounts(Integer customerId) {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(Account.class)
                    .add(Restrictions.eq("isActive", true))
                    .add(Restrictions.eq("customer.id", customerId))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return accounts;
    }

    public static Map<String, Long> getCreatedAccounts(String from, String to) {
        LinkedHashMap<String, Long> createdAccounts = new LinkedHashMap<>();

        try {
            session = SessionService.getSession();
            Criteria criteria = session.createCriteria(Account.class)
                    .setProjection(Projections.projectionList()
                            .add(Projections.alias(Projections.sqlGroupProjection("date(creationDate) as createdDate", "createdDate", new String[] { "createdDate" }, new Type[] { StandardBasicTypes.DATE }), "createdDate"))
                            .add(Projections.count("CBU")));

            if (nonNull(from) && nonNull(to)) {
                try {
                    Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
                    Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
                    criteria = criteria.add(Restrictions.between("creationDate", fromDate, toDate));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<Object[]> accountsList = criteria.addOrder(Order.asc("createdDate")).list();
            accountsList.stream().forEach(list -> createdAccounts.put(list[0].toString().substring(0, 10), (Long) list[1]));
        } finally {
            SessionService.commitSession(session);
        }

        return createdAccounts;
    }

    public static Account getAccount(String accountCBU) {
        Account account;

        try {
            session = SessionService.getSession();
            account = (Account) session.createCriteria(Account.class)
                    .add(Restrictions.eq("CBU", accountCBU))
                    .uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return account;
    }

    public static Account getAccountByAlias(String alias) {
        Account account;

        try {
            session = SessionService.getSession();
            account = (Account) session.createCriteria(Account.class)
                    .add(Restrictions.eq("alias", alias))
                    .uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return account;
    }

    public static List<Account> getHistory(Integer customerId) {
        try {
            session = SessionService.getSession();
            accounts = session.createCriteria(Account.class)
                    .add(Restrictions.eq("isActive", true))
                    .add(Restrictions.eq("customer.id", customerId))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return accounts;
    }

    public static void saveAccount(Account account) throws AccountException {
        AccountService.canUserHaveAnotherAccount(account);

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

    private static void canUserHaveAnotherAccount(Account account) throws AccountException {
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
}
