package UTN.FRGP.TP_L5_GRUPO_1.Exceptions;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.LoanEnum;

public class LoanException extends Exception{
    private LoanEnum field;

    public LoanException(LoanEnum accountField) {
        super(String.format("El campo %s es inválido.", accountField));

        this.field = accountField;
    }

    public LoanEnum getField() {
        return this.field;
    }
}
