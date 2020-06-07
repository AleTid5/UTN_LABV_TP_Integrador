package UTN.FRGP.UTN_LABV_TP_Integrador.Exceptions;

import UTN.FRGP.UTN_LABV_TP_Integrador.Enums.UserEnum;

public class UserException extends Exception {
    public UserException(UserEnum customerField) {
        super(String.format("El campo %s es inválido.", customerField));
    }
}
