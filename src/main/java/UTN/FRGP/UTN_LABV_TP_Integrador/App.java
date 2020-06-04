package UTN.FRGP.UTN_LABV_TP_Integrador;

import UTN.FRGP.UTN_LABV_TP_Integrador.Seeders.UserSeeder;
import UTN.FRGP.UTN_LABV_TP_Integrador.Seeders.LocationSeeder;
import UTN.FRGP.UTN_LABV_TP_Integrador.Services.SessionService;
import org.hibernate.Session;

public class App {
    public static void main( String[] args ) {
        Session session = SessionService.getSession();

        LocationSeeder.hydrate(session);
        UserSeeder.hydrate(session);

        SessionService.commitSession(session);
        SessionService.closeSessionFactory();
    }
}
