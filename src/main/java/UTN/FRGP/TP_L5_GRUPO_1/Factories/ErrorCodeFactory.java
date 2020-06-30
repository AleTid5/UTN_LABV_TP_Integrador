package UTN.FRGP.TP_L5_GRUPO_1.Factories;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;

import java.util.HashMap;
import java.util.Map;

public abstract class ErrorCodeFactory {
    public static String getDescription(ErrorCodeEnum errorCode) throws ErrorCodeException {
        if (mapOfEntries().containsKey(errorCode)) {
            return mapOfEntries().get(errorCode);
        }

        throw new ErrorCodeException("El codigo de error ingresado es inválido");
    }

    private static Map<ErrorCodeEnum, String> mapOfEntries() {
        Map<ErrorCodeEnum, String> map = new HashMap<>();
        map.put(ErrorCodeEnum.ACCOUNTS_MAX_LIMIT, "El cliente ha alcanzado el máximo permitido de cuentas.");
        map.put(ErrorCodeEnum.DUPLICATED_ACCOUNT, "La cuenta ingresada tiene datos duplicados con otra cuenta (CBU, número de cuenta o Nombre de usuario)");
        map.put(ErrorCodeEnum.DUPLICATED_CUSTOMER, "El cliente ingresado tiene datos duplicados con otro cliente (DNI, E-Mail o nombre de usuario)");
        map.put(ErrorCodeEnum.INVALID_ID, "El Id del usuario es inválido.");
        map.put(ErrorCodeEnum.INVALID_DNI, "La información del DNI del cliente es inválido.");
        map.put(ErrorCodeEnum.INVALID_NAME, "La información del nombre del cliente es inválido.");
        map.put(ErrorCodeEnum.INVALID_LAST_NAME, "La información del apellido del cliente es inválido.");
        map.put(ErrorCodeEnum.INVALID_EMAIL, "La información del E-Mail del cliente es inválido.");
        map.put(ErrorCodeEnum.INVALID_USERNAME, "La información del nombre de usuario del cliente es inválido.");
        map.put(ErrorCodeEnum.INVALID_MAX_LOAN_AMOUNT, "El valor ingresado del monto máximo para préstamos es inválido.");
        map.put(ErrorCodeEnum.INVALID_LOCALITY, "La información de la localidad del cliente es inválido.");
        map.put(ErrorCodeEnum.INVALID_ADDRESS, "La información del domicilio del cliente es inválido.");
        map.put(ErrorCodeEnum.INVALID_GENDER, "El género del cliente es inválido.");

        return map;
    }
}
