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
    private static List<AccountType> accountType;
	
    public static List<AccountType> getAccounts() {
        try {
            session = SessionService.getSession();
            accountType = session.createCriteria(AccountType.class).add(Restrictions.eq("isActive", true)).list();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }
        
        return accountType;
    }
    public static AccountType getAccountById(Integer accountTypeId) { ;
    AccountType accountType = null;

    try {
        session = SessionService.getSession();
        accountType = (AccountType) session.createCriteria(AccountType.class).add(Restrictions.eq("id", accountTypeId)).uniqueResult();
        SessionService.commitSession(session);
    } catch (Exception e) {
        SessionService.rollbackSession(session);
    }

    return accountType;
}

}
