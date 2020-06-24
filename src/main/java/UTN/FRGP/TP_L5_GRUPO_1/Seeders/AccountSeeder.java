package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.AccountType;
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
    public static final ArrayList<Account> accounts = new ArrayList<Account>() {
        {
            add(new Account("FRUTILLA.VELO.ESPINA",
                    UserSeeder.customers.get(0), accountTypes.get(2), 123123456));
            add(new Account("MESA.ASIA.ZAPALLO",
                    UserSeeder.customers.get(0), accountTypes.get(0), 341435542));
            add(new Account("CIRCULO.CASA.TORNILLO",
                    UserSeeder.customers.get(1), accountTypes.get(2), 523423455));
            add(new Account("ESTANTE.EQUIPO.MEDIA",
                    UserSeeder.customers.get(1), accountTypes.get(2), 432433212));
        }
    };

    public void hydrate(Session session) {
        accountTypes.forEach(session::save);
        accounts.forEach(session::save);
    }
}
