package UTN.FRGP.TP_L5_GRUPO_1.Exceptions;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.UserEnum;

public class UserException extends Exception {
    public UserException(UserEnum customerField) {
        super(String.format("El campo %s es inválido.", customerField));
    }
}
