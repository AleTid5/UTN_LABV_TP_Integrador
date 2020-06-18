package UTN.FRGP.TP_L5_GRUPO_1.Services;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class LoggerService {
    public static void log(String message) {
        Session session = SessionService.getSession();

        session.save(new Log(message));

        SessionService.commitSession(session);
    }
}
