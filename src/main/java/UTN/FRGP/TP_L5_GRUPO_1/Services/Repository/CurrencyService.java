package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.CurrencyType;
import UTN.FRGP.TP_L5_GRUPO_1.Models.CurrencyValue;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class CurrencyService {

    @Autowired
    private static Session session;

    public static CurrencyValue getCurrencyValueByCurrencyTypeName(String currencyTypeName) {
        CurrencyType currencyType;
        CurrencyValue currencyValue;

        try {
            session = SessionService.getSession();
            currencyType = (CurrencyType) session.createCriteria(CurrencyType.class)
                    .add(Restrictions.eq("name", currencyTypeName))
                    .uniqueResult();
            currencyValue = (CurrencyValue) session.createCriteria(CurrencyValue.class)
                    .add(Restrictions.eq("currencyType.id", currencyType.getId()))
                    .uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return currencyValue;
    }
}
