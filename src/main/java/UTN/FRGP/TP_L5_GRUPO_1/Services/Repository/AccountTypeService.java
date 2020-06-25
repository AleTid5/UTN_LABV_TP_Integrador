package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import UTN.FRGP.TP_L5_GRUPO_1.Models.AccountType;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;

public abstract class AccountTypeService {

    @Autowired
    private static Session session;

    @Autowired
    private static List<AccountType> accountTypes;

    public static List<AccountType> getAccountTypes() {
        try {
            session = SessionService.getSession();
            accountTypes = session.createCriteria(AccountType.class).list();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }

        return accountTypes;
    }

    public static AccountType getAccountById(Integer accountTypesId) { ;
        AccountType accountTypes = null;

        try {
            session = SessionService.getSession();
            accountTypes = (AccountType) session.createCriteria(AccountType.class).uniqueResult();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }

        return accountTypes;
    }

}
