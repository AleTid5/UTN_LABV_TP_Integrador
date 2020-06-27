package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.AccountType;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class AccountSeeder extends Seeder {
    public static final List<AccountType> accountTypes = Arrays.asList(
            new AccountType("Caja ahorro pesos", CurrencySeeder.currencyTypes.get(0)),
            new AccountType("Caja ahorro dólares", CurrencySeeder.currencyTypes.get(1)),
            new AccountType("Cuenta corriente", CurrencySeeder.currencyTypes.get(0)),
            new AccountType("Cuenta corriente especial en pesos", CurrencySeeder.currencyTypes.get(0)),
            new AccountType("Cuenta corriente especial en dólares", CurrencySeeder.currencyTypes.get(1))
    );

    public static final List<Account> accounts = Arrays.asList(
            new Account("FRUTILLA.VELO.ESPINA", UserSeeder.customers.get(0), accountTypes.get(0), 123123456),
            new Account("MESA.ASIA.ZAPALLO", UserSeeder.customers.get(0), accountTypes.get(1), 341435542),
            new Account("CIRCULO.CASA.TORNILLO", UserSeeder.customers.get(0), accountTypes.get(2), 523423455),
            new Account("ESTANTE.EQUIPO.MEDIA", UserSeeder.customers.get(1), accountTypes.get(3), 432433212),
            new Account("CUBO.EQUIPO.MEDIA", UserSeeder.customers.get(1), accountTypes.get(0), 319847263),
            new Account("ESTANTE.ROMBO.MEDIA", UserSeeder.customers.get(1), accountTypes.get(1), 856709321),
            new Account("ESTANTE.EQUIPO.ESFERA", UserSeeder.customers.get(2), accountTypes.get(2), 529049184),
            new Account("CUADRILATERO.EQUIPO.MEDIA", UserSeeder.customers.get(2), accountTypes.get(3), 738040103),
            new Account("ESTANTE.HECTAGONO.MEDIA", UserSeeder.customers.get(2), accountTypes.get(0), 679940284),
            new Account("ESTANTE.EQUIPO.PENTAGONO", UserSeeder.customers.get(3), accountTypes.get(1), 532572941)
    );

    public void hydrate(Session session) {
        accountTypes.forEach(session::save);
        accounts.forEach(session::save);
    }
}
