package UTN.FRGP.TP_L5_GRUPO_1.Factories;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCode;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;

import java.util.Map;

public abstract class SuccessCodeFactory {
    public static String getDescription(SuccessCode successCode) throws ErrorCodeException {
        if (mapOfEntries().containsKey(successCode)) {
            return mapOfEntries().get(successCode);
        }

        throw new ErrorCodeException("El codigo ingresado es inválido");
    }

    private static Map<SuccessCode, String> mapOfEntries() {
        return Map.ofEntries(
                Map.entry(SuccessCode.CUSTOMER_CREATED, "El cliente ha sido creado exitosamente!"),
                Map.entry(SuccessCode.CUSTOMER_UPDATED, "El cliente ha sido actualizado exitosamente!")
        );
    }
}
