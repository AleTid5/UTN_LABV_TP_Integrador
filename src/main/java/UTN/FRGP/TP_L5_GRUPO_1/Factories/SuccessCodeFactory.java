package UTN.FRGP.TP_L5_GRUPO_1.Factories;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCode;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCode;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;

import java.util.HashMap;
import java.util.Map;

public abstract class SuccessCodeFactory {
    public static String getDescription(SuccessCode successCode) throws ErrorCodeException {
        if (mapOfEntries().containsKey(successCode)) {
            return mapOfEntries().get(successCode);
        }

        throw new ErrorCodeException("El codigo ingresado es inválido");
    }

    private static Map<SuccessCode, String> mapOfEntries() {
        Map<SuccessCode, String> map = new HashMap<>();
        map.put(SuccessCode.CUSTOMER_CREATED, "El cliente ha sido creado exitosamente!");
        map.put(SuccessCode.CUSTOMER_UPDATED, "El cliente ha sido actualizado exitosamente!");

        return map;
    }
}
