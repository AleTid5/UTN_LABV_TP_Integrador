package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class TransferService {

    @Autowired
    private static Session session;

    public static void saveTransfer(Map.Entry<Account, Account> accounts, double amount, String concept) {
        try {

            session= SessionService.getSession();

        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }
    }
}
