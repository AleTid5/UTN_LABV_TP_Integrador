package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Seeder {
    public void hydrate(Session session) {
        System.out.println("La base de datos se está populando...");
    }

    /**
     * This method hydrates the database using the Reflection Pattern provided by "burningwave" library.
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
