package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import UTN.FRGP.UTN_LABV_TP_Integrador.Exceptions.UserException;

import javax.persistence.Entity;

@Entity
public class BankAdministrator extends User {
    public BankAdministrator(Integer dni, String name, String lastName, String email, String password) throws UserException {
        super(dni, name, lastName, email, password);
    }
}
