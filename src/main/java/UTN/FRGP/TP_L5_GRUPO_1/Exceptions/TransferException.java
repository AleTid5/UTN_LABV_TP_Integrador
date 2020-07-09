package UTN.FRGP.TP_L5_GRUPO_1.Exceptions;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;

public class TransferException extends Exception {
    private AccountEnum field;

    public TransferException(AccountEnum accountField) {
        super(String.format("El campo %s es inválido.", accountField));

        this.field = accountField;
    }

    public AccountEnum getField() {
        return this.field;
    }
}
