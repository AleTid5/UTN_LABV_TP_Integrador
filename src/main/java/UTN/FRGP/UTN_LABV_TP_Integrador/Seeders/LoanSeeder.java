package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import java.util.ArrayList;

import org.hibernate.Session;

import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Loan;

public class LoanSeeder extends Seeder {
	public static final ArrayList<Loan> loans = new ArrayList<Loan>(){
        {
            add(new Loan(AccountSeeder.accounts.get(0),40000,8,5000));
            add(new Loan(AccountSeeder.accounts.get(1),5000,5,1000));
            add(new Loan(AccountSeeder.accounts.get(2),20000,10,2000));
            add(new Loan(AccountSeeder.accounts.get(3),100000,2,5000));
        }
    };
    
    public static void hydrate(Session session) {
        loans.forEach(session::save);
    }
}
