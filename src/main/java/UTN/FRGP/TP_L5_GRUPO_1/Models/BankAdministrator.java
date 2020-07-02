package UTN.FRGP.TP_L5_GRUPO_1.Models;

import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;

import javax.persistence.Entity;

@Entity
public class BankAdministrator extends User {
    public BankAdministrator() {}

    public BankAdministrator(Integer id) throws UserException {
        super.setId(id);
    }

    public BankAdministrator(Integer dni, String name, String lastName, String email) throws UserException {
        super(dni, name, lastName, email);
        this.setPassword(Integer.toString(dni));
    }
}
