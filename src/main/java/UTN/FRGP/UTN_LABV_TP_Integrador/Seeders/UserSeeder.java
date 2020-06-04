package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import UTN.FRGP.UTN_LABV_TP_Integrador.Models.BankAdministrator;
import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Customer;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserSeeder extends Seeder {
    public static final ArrayList<Customer> customers = new ArrayList<Customer>() {
        {
            try {
                add(new Customer(39100507, "Alejandro", "Tidele", "aleetidele@gmail.com",
                        "ale123ale", 15000, LocationSeeder.localities.get(0),
                        "Avenida Maipu 3390", new SimpleDateFormat("yyyy-MM-dd").parse("1995-08-12"), "M"));
                add(new Customer(15323332, "Juan", "Perez", "jperez@gmail.com",
                        "jp123jp", 15000, LocationSeeder.localities.get(1),
                        "Dr. Romero 423", new SimpleDateFormat("yyyy-MM-dd").parse("1991-02-23"), "M"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    public static final ArrayList<BankAdministrator> bankAdministrators = new ArrayList<BankAdministrator>() {
        {
            add(new BankAdministrator(25023212, "Lucas", "Perisich", "lperisich@bank.com", "lucas123"));
            add(new BankAdministrator(43213322, "Tomas", "Hernandez", "thernandez@bank.com", "tomas123"));
        }
    };

    public static void hydrate(Session session) {
        UserSeeder.customers.forEach(session::save);
        UserSeeder.bankAdministrators.forEach(session::save);
    }
}
