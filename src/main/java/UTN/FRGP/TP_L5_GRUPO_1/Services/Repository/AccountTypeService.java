package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.AccountType;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AccountTypeService {

    @Autowired
    private static Session session;

    @Autowired
    private static List<AccountType> accountTypes;

    public static List<AccountType> getAccountTypes() {
        try {
            session = SessionService.getSession();
            accountTypes = session.createCriteria(AccountType.class).list();
        } finally {
            SessionService.commitSession(session);
        }

        return accountTypes;
    }
}
