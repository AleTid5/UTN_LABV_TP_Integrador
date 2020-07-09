package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Models.MovementType;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class MovementTypeService {

    @Autowired
    private static Session session;

    public static MovementType getMovementType(MovementTypeEnum movementTypeEnum){
        MovementType movementType;

        try {
            session = SessionService.getSession();
            movementType = (MovementType) session.createCriteria(MovementType.class)
                    .add(Restrictions.eq("name",movementTypeEnum))
                    .uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return movementType;
    }
}
