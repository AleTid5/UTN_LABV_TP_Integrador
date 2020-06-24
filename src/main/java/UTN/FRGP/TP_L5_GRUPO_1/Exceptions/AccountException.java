package UTN.FRGP.TP_L5_GRUPO_1.Exceptions;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountE;

public class AccountException extends Exception{
    private AccountE field;

    public AccountException(AccountE accountField) {
        super(String.format("El campo %s es inválido.", accountField));

        this.field = accountField;
    }

    public AccountE getField() {
        return this.field;
    }
}
