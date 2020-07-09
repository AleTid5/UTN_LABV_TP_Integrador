package UTN.FRGP.TP_L5_GRUPO_1.Factories;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;

import java.util.HashMap;
import java.util.Map;

public class SuccessCodeFactory {
    public static String getDescription(SuccessCodeEnum successCode) throws ErrorCodeException {
        if (mapOfEntries().containsKey(successCode)) {
            return mapOfEntries().get(successCode);
        }

        throw new ErrorCodeException("El codigo ingresado es inválido");
    }

    private static Map<SuccessCodeEnum, String> mapOfEntries() {
        Map<SuccessCodeEnum, String> map = new HashMap<>();
        map.put(SuccessCodeEnum.ACCOUNT_CREATED, "La cuenta ha sido creada exitosamente!");
        map.put(SuccessCodeEnum.ACCOUNT_UPDATED, "La cuenta ha sido actualizada exitosamente!");
        map.put(SuccessCodeEnum.CUSTOMER_CREATED, "El cliente ha sido creado exitosamente!");
        map.put(SuccessCodeEnum.CUSTOMER_UPDATED, "El cliente ha sido actualizado exitosamente!");
        map.put(SuccessCodeEnum.LOAN_CREATED, "El préstamo ha sido solicitado exitosamente!");
        map.put(SuccessCodeEnum.TRANSFER_SUCCESSFUL, "La transferencia ha sido realizada exitosamente!");

        return map;
    }
}
