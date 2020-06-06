package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import UTN.FRGP.UTN_LABV_TP_Integrador.Models.AccountType;
import org.hibernate.Session;

import java.util.ArrayList;

public class AccountSeeder extends Seeder {
    public static final ArrayList<AccountType> accountTypes = new ArrayList<AccountType>() {
        {
            add(new AccountType("Caja ahorro pesos", CurrencySeeder.currencyTypes.get(0)));
            add(new AccountType("Caja ahorro dólares", CurrencySeeder.currencyTypes.get(1)));
            add(new AccountType("Cuenta corriente", CurrencySeeder.currencyTypes.get(0)));
            add(new AccountType("Cuenta corriente especial en pesos", CurrencySeeder.currencyTypes.get(0)));
            add(new AccountType("Cuenta corriente especial en dólares", CurrencySeeder.currencyTypes.get(1)));
        }
    };

    public static void hydrate(Session session) {
        accountTypes.forEach(session::save);
    }
}
