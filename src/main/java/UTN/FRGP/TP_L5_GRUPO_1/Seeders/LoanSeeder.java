package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import java.text.ParseException;
import java.util.ArrayList;

import org.hibernate.Session;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;

public class LoanSeeder extends Seeder {
	public static final ArrayList<Loan> loans = new ArrayList<Loan>(){
        {
            try {
                add(new Loan(AccountSeeder.accounts.get(0),40000,8,5000, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(1),5000,5,1000, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(2),20000,10,2000, "2020-12-12"));
                add(new Loan(AccountSeeder.accounts.get(3),100000,2,5000, "2020-12-12"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };
    
    public static void hydrate(Session session) {
        loans.forEach(session::save);
    }
}
