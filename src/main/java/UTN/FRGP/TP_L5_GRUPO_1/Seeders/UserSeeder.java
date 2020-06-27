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
                add(new Customer(38457384, "Lazaro", "Perez", "lperez@gmail.com", "jp123jp",
                        "lazaro.the.best7", 15000, LocationSeeder.localities.get(2),
                        "Dr. Romero 423", "1991-02-23", "M"));
                add(new Customer(44738483, "Pedro", "Perez", "pperez@gmail.com", "jp123jp",
                        "pedro.the.best7", 15000, LocationSeeder.localities.get(3),
                        "Dr. Romero 423", "1991-02-23", "M"));
                add(new Customer(12378728, "Pablo", "Gutierrez", "palala123@gmail.com", "jp123jp",
                        "mesa.color.pescado", 15000, LocationSeeder.localities.get(4),
                        "Dr. Romero 423", "1991-02-23", "M"));
                add(new Customer(12354728, "Federico", "Gutierrez", "fg23@gmail.com", "jp123jp",
                        "mesa.color.leon", 15000, LocationSeeder.localities.get(5),
                        "Dr. Romero 423", "1991-02-23", "M"));
                add(new Customer(32378728, "Leandro", "D' Angelo", "ldan6@gmail.com", "jp123jp",
                        "silla.color.pescado", 15000, LocationSeeder.localities.get(6),
                        "Dr. Romero 423", "1991-02-23", "M"));
                add(new Customer(26378728, "Emmanuel", "Diaz", "emdi32@gmail.com", "jp123jp",
                        "mesa.instrumento.pescado", 15000, LocationSeeder.localities.get(7),
                        "Dr. Romero 423", "1991-02-23", "M"));
                add(new Customer(19378728, "Gabriel", "González", "gago01@gmail.com", "jp123jp",
                        "caja.color.amarillo", 15000, LocationSeeder.localities.get(8),
                        "Dr. Romero 423", "1991-02-23", "M"));
                add(new Customer(31378728, "Pablo", "Martinez", "pm1pm@gmail.com", "jp123jp",
                        "ropa.color.silla", 15000, LocationSeeder.localities.get(9),
                        "Dr. Romero 423", "1991-02-23", "M"));
            } catch (ParseException | UserException e) {
                e.printStackTrace();
            }
        }
    };

    public static final ArrayList<BankAdministrator> bankAdministrators = new ArrayList<BankAdministrator>() {
        {
            try {
                add(new BankAdministrator(25023212, "Lucas", "Perisich", "lperisich@bank.com"));
                add(new BankAdministrator(40213322, "Tomas", "Hernandez", "thernandez@bank.com"));
                add(new BankAdministrator(41203312, "Alejandro", "Martinez", "amartinez@bank.com"));
                add(new BankAdministrator(42213328, "Martin", "Perez", "mperez@bank.com"));
                add(new BankAdministrator(43215329, "Luciano", "Gonzalez", "lgonzalez@bank.com"));
                add(new BankAdministrator(34213322, "Maximiliano", "Ruiz", "mruiz@bank.com"));
                add(new BankAdministrator(45213922, "Juan", "Valverde", "jvalverde@bank.com"));
                add(new BankAdministrator(46213322, "Ernesto", "Tomsen", "etomsen@bank.com"));
                add(new BankAdministrator(47213572, "Luis", "Alario", "lalario@bank.com"));
                add(new BankAdministrator(12354323, "Damián", "Natale", "dnatale@bank.com"));
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
