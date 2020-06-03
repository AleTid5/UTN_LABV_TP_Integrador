package UTN.FRGP.UTN_LABV_TP_Integrador.Services;

import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Log;
import org.hibernate.Session;

public abstract class LoggerService {
    public static void log(String message) {
        Session session = SessionService.getSession();

        session.save(new Log(message));

        SessionService.commitSession(session);
    }
}
