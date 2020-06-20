package UTN.FRGP.TP_L5_GRUPO_1.Exceptions;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.User;

public class UserException extends Exception {
    private User field;

    public UserException(User customerField) {
        super(String.format("El campo %s es inválido.", customerField));

        this.field = customerField;
    }

    public User getField() {
        return this.field;
    }
}
