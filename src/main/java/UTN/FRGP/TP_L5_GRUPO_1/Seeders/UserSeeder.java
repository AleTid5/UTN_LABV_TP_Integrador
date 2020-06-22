package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.BankAdministrator;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import org.hibernate.Session;

import java.text.ParseException;
import java.util.ArrayList;

public class UserSeeder extends Seeder {
    public static final ArrayList<Customer> customers = new ArrayList<Customer>() {
        {
            try {
                add(new Customer(39100507, "Alejandro", "Tidele", "aleetidele@gmail.com", "ale123ale",
                        "AleTid5", 15000, LocationSeeder.localities.get(0),
                        "Avenida Maipu 3390","1995-08-12", "M"));
                add(new Customer(15323332, "Juan", "Perez", "jperez@gmail.com", "jp123jp",
                        "juan.the.best7", 15000, LocationSeeder.localities.get(1),
                        "Dr. Romero 423", "1991-02-23", "M"));
            } catch (ParseException | UserException e) {
                e.printStackTrace();
            }
        }
    };

    public static final ArrayList<BankAdministrator> bankAdministrators = new ArrayList<BankAdministrator>() {
        {
            try {
                add(new BankAdministrator(25023212, "Lucas", "Perisich", "lperisich@bank.com", "lucas123"));
                add(new BankAdministrator(43213322, "Tomas", "Hernandez", "thernandez@bank.com", "tomas123"));
            } catch (UserException e) {
                e.printStackTrace();
            }
        }
    };

    public void hydrate(Session session) {
        UserSeeder.customers.forEach(session::save);
        UserSeeder.bankAdministrators.forEach(session::save);
    }
}
