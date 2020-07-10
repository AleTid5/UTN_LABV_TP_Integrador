package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class LoanSeeder extends Seeder {
    public static final List<Loan> loans = Arrays.asList(
            new Loan(AccountSeeder.accounts.get(0), 40000.0, 8),
            new Loan(AccountSeeder.accounts.get(1), 5000.0, 5),
            new Loan(AccountSeeder.accounts.get(2), 20000.0, 10),
            new Loan(AccountSeeder.accounts.get(3), 4000.0, 2),
            new Loan(AccountSeeder.accounts.get(4), 15000.0, 2),
            new Loan(AccountSeeder.accounts.get(5), 13200.0, 5),
            new Loan(AccountSeeder.accounts.get(6), 100000.0, 2),
            new Loan(AccountSeeder.accounts.get(7), 7000.0, 7),
            new Loan(AccountSeeder.accounts.get(8), 100000.0, 2),
            new Loan(AccountSeeder.accounts.get(9), 4500.0, 1)
    );

    public void hydrate(Session session) {
        loans.forEach(session::save);
    }
}
