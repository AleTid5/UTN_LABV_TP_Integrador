package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;

import java.util.ArrayList;

public abstract class Seeder {
    public void hydrate(Session session) {
        System.out.println("La base de datos se está populando...");
    }

    /**
     * This method hydrates the database using the Reflection Pattern provided by "burningwave" library.
     */
    public static void plant() {
        Session session = SessionService.getSession();

        ArrayList<Seeder> seeders = new ArrayList<>() {{
            add(new LocationSeeder());
            add(new CurrencySeeder());
            add(new UserSeeder());
            add(new AccountSeeder());
            add(new LoanSeeder());
            add(new MovementSeeder());
        }};

        seeders.forEach((Seeder seeder) -> seeder.hydrate(session));

        SessionService.commitSession(session);
        SessionService.closeSessionFactory();
    }
}
