package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import UTN.FRGP.UTN_LABV_TP_Integrador.Exceptions.UserException;
import UTN.FRGP.UTN_LABV_TP_Integrador.Seeders.LocationSeeder;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    @Test
    public void shouldCreateSuccessfullyACustomer() {
        try {
            Customer customer = new Customer(35345347, "Jhon", "Walker", "jwalker@gmail.com",
                    "jw123jw", "pep.retr.dos", 34000, LocationSeeder.localities.get(0),
                    "Boanesburgo 123", "1975-12-09", "M");

            assertEquals(customer.getBornDate(), new SimpleDateFormat("yyyy-MM-dd").parse("1975-12-09"));
        } catch (ParseException | UserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldFailWhenTryingToCreateCustomer() {
        // Invalid DNI
        assertThrows(UserException.class, () -> new Customer(null, "", "Walker", "jwalker@gmail.com",
                "jw123jw", "pep.retr.dos", 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Name
        assertThrows(UserException.class, () -> new Customer(35345347, null, "Walker", "jwalker@gmail.com",
                "jw123jw", "pep.retr.dos", 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Lastname
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", null, "jwalker@gmail.com",
                "jw123jw", "pep.retr.dos", 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid E-Mail
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", "Walker", null,
                "jw123jw", "pep.retr.dos", 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Password
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", "Walker", "jwalker@gmail.com",
                null, "pep.retr.dos", 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Username
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", "Walker", "jwalker@gmail.com",
                "jw123jw", null, 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Username (Min length)
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", "Walker", "jwalker@gmail.com",
                "jw123jw", "asd", 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Username (Max length)
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", "Walker", "jwalker@gmail.com",
                "jw123jw", "lorem.ipsum.iscariante", 34000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Max Loan Account
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", "Walker", "jwalker@gmail.com",
                "jw123jw", "pep.retr.dos", null, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));

        // Invalid Max Loan Account (Negative number)
        assertThrows(UserException.class, () -> new Customer(35345347, "Jhon", "Walker", "jwalker@gmail.com",
                "jw123jw", "pep.retr.dos", -1000, LocationSeeder.localities.get(0),
                "Boanesburgo 123", "1975-12-09", "M"));
    }
}
