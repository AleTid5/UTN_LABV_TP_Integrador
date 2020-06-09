package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Account;
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
    public static final ArrayList<Account> accounts = new ArrayList<Account>() {
        {
            add(new Account("0000007900204047542202", "FRUTILLA.VELO.ESPINA",
                    UserSeeder.customers.get(0), accountTypes.get(2), 123123456));
            add(new Account("0000007900205234255678", "MESA.ASIA.ZAPALLO",
                    UserSeeder.customers.get(0), accountTypes.get(0), 341435542));
            add(new Account("0002512987896541231233", "CIRCULO.CASA.TORNILLO",
                    UserSeeder.customers.get(1), accountTypes.get(2), 523423455));
            add(new Account("0002512413896541231235", "ESTANTE.EQUIPO.MEDIA",
                    UserSeeder.customers.get(1), accountTypes.get(2), 432433212));
        }
    };

    public static void hydrate(Session session) {
        accountTypes.forEach(session::save);
        accounts.forEach(session::save);
    }
}
