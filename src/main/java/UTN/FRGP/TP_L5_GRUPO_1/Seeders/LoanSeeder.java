package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;
import org.hibernate.Session;

import java.text.ParseException;
import java.util.ArrayList;

public class LoanSeeder extends Seeder {
    public static final ArrayList<Loan> loans = new ArrayList<Loan>(){
        {
            try {
                add(new Loan(AccountSeeder.accounts.get(0), 40000, 8, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(1), 5000, 5, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(2), 20000, 10, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(3), 4000, 2, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(4), 15000, 2, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(5), 13200, 5, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(6), 100000, 2, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(7), 7000, 7, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(8), 100000, 2, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(9), 4500, 1, "2020-12-12"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    public void hydrate(Session session) {
        loans.forEach(session::save);
    }
}
