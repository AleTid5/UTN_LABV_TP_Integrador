package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class MovementService {

    @Autowired
    private static List<Movement> movements;

    @Autowired
    private static Session session;

    public static List<Movement> getMovements(List<Account> accounts) {
        try {
            session = SessionService.getSession();
            movements = session.createCriteria(Movement.class)
                    .add(Restrictions.or(
                            Restrictions.in("originAccount", accounts),
                            Restrictions.in("destinationAccount", accounts)))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }
        return movements;
    }
}