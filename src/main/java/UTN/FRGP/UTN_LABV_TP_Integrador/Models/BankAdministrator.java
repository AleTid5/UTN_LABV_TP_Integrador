package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.Entity;

@Entity
public class BankAdministrator extends User {
    public BankAdministrator(Integer dni, String name, String lastName, String email, String password) {
        super(dni, name, lastName, email, password);
    }
}
