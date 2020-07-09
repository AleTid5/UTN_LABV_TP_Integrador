package UTN.FRGP.TP_L5_GRUPO_1.Factories;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeFactory {
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
        map.put(ErrorCodeEnum.INVALID_CREDENTIALS, "Las credenciales son inválidas.");
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
        map.put(ErrorCodeEnum.INVALID_ALIAS, "El Alias ingresado es inválido. Debe contener por lo menos 5 caracteres y como máximo 30.");
        map.put(ErrorCodeEnum.INVALID_CBU, "El CBU ingresado es inválido. Debe contener 22 dígitos numéricos.");
        map.put(ErrorCodeEnum.INVALID_TRANSFER_ALIAS, "No hemos podido hallar el Alias ingresado. Intente nuevamente.");
        map.put(ErrorCodeEnum.INVALID_TRANSFER_CBU, "No hemos podido hallar el CBU ingresado. Intente nuevamente.");
        map.put(ErrorCodeEnum.INVALID_TRANSFER_CUSTOMER, "La cuenta de destino no puede ser la misma que la de origen. Diríjase al módulo \"Transferencias a cuenta propia\".");
        map.put(ErrorCodeEnum.INVALID_TRANSFER_ACCOUNT_TYPE, "No es posible enviar dinero a una cuenta con tipo de moneda diferente a la cuenta de origen.");
        map.put(ErrorCodeEnum.INVALID_TRANSFER_BALANCE, "El monto ingresado es superior al remanente en su cuenta.");
        map.put(ErrorCodeEnum.MISSING_DATA, "Hay datos faltantes.");

        return map;
    }
}
