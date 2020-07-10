package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public abstract class Seeder {
    public abstract void hydrate(Session session);

    /**
     * This method hydrates the database using the Reflection Pattern.
     */
    public static void plant() {
        Session session = SessionService.getSession();

        List<Seeder> seeders = Arrays.asList(
                new LocationSeeder(),
                new CurrencySeeder(),
                new UserSeeder(),
                new AccountSeeder(),
                new LoanSeeder(),
                new MovementSeeder()
        );

        seeders.forEach((Seeder seeder) -> seeder.hydrate(session));

        SessionService.commitSession(session);
        SessionService.closeSessionFactory();
    }
}
