package UTN.FRGP.TP_L5_GRUPO_1.Factories;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCode;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;

import java.util.HashMap;
import java.util.Map;

public abstract class ErrorCodeFactory {
    public static String getDescription(ErrorCode errorCode) throws ErrorCodeException {
        if (mapOfEntries().containsKey(errorCode)) {
            return mapOfEntries().get(errorCode);
        }

        throw new ErrorCodeException("El codigo de error ingresado es inválido");
    }

    private static Map<ErrorCode, String> mapOfEntries() {
        Map<ErrorCode, String> map = new HashMap<>();
        map.put(ErrorCode.DUPLICATED_CUSTOMER, "El cliente ingresado tiene datos duplicados con otro cliente (DNI, E-Mail o Nombre de usuario)");
        map.put(ErrorCode.INVALID_ID, "El Id del usuario es inválido.");
        map.put(ErrorCode.INVALID_DNI, "La información del DNI del cliente es inválido.");
        map.put(ErrorCode.INVALID_NAME, "La información del nombre del cliente es inválido.");
        map.put(ErrorCode.INVALID_LAST_NAME, "La información del apellido del cliente es inválido.");
        map.put(ErrorCode.INVALID_EMAIL, "La información del E-Mail del cliente es inválido.");
        map.put(ErrorCode.INVALID_USERNAME, "La información del nombre de usuario del cliente es inválido.");
        map.put(ErrorCode.INVALID_MAX_LOAN_AMOUNT, "El valor ingresado del monto máximo para préstamos es inválido.");
        map.put(ErrorCode.INVALID_LOCALITY, "La información de la localidad del cliente es inválido.");
        map.put(ErrorCode.INVALID_ADDRESS, "La información del domicilio del cliente es inválido.");
        map.put(ErrorCode.INVALID_GENDER, "El género del cliente es inválido.");

        return map;
    }
}
