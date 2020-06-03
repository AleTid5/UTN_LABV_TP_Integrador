package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import org.hibernate.Session;

public abstract class Seeder {
    public static void hydrate(Session session) {
        System.out.println("Please override this method :)");
    }
}
