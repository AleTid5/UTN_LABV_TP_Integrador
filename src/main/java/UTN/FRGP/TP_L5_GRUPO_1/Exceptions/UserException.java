package UTN.FRGP.TP_L5_GRUPO_1.Exceptions;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.UserEnum;

public class UserException extends Exception {
    private UserEnum field;

    public UserException(UserEnum customerField) {
        super(String.format("El campo %s es inválido.", customerField));

        this.field = customerField;
    }

    public UserEnum getField() {
        return this.field;
    }
}
