package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.CurrencyType;
import UTN.FRGP.TP_L5_GRUPO_1.Models.CurrencyValue;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class CurrencySeeder extends Seeder {
    public static final List<CurrencyType> currencyTypes = Arrays.asList(
            new CurrencyType("ARS", false),
            new CurrencyType("USD", true),
            new CurrencyType("EUR", true)
    );

    public static final List<CurrencyValue> currencyValues = Arrays.asList(
            new CurrencyValue(currencyTypes.get(0), 1.0, 1.0, null),
            new CurrencyValue(currencyTypes.get(1), 93.10, 85.42, null),
            new CurrencyValue(currencyTypes.get(2), 107.5, 102.35, null)
    );

    public void hydrate(Session session) {
        currencyTypes.forEach(session::save);
        currencyValues.forEach(session::save);
    }
}
