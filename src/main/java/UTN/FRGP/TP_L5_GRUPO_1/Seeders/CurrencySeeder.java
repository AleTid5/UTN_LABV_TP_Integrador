package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.CurrencyType;
import UTN.FRGP.TP_L5_GRUPO_1.Models.CurrencyValue;
import org.hibernate.Session;

import java.util.ArrayList;

public class CurrencySeeder extends Seeder {
    public static final ArrayList<CurrencyType> currencyTypes = new ArrayList<CurrencyType>(){
        {
            add(new CurrencyType("ARS", false));
            add(new CurrencyType("USD", true));
            add(new CurrencyType("EUR", true));
        }
    };

    public static final ArrayList<CurrencyValue> currencyValues = new ArrayList<CurrencyValue>(){
        {
            add(new CurrencyValue(currencyTypes.get(1), 93.10, 85.42, null));
            add(new CurrencyValue(currencyTypes.get(2), 107.5, 102.35, null));
        }
    };

    public void hydrate(Session session) {
        currencyTypes.forEach(session::save);
        currencyValues.forEach(session::save);
    }
}
