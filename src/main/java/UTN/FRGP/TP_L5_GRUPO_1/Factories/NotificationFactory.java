package UTN.FRGP.TP_L5_GRUPO_1.Factories;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.NotificationEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;

import java.util.HashMap;
import java.util.Map;

public class NotificationFactory {
    public static String getDescription(NotificationEnum errorCode) throws ErrorCodeException {
        if (mapOfEntries().containsKey(errorCode)) {
            return mapOfEntries().get(errorCode);
        }

        throw new ErrorCodeException("El codigo de notificación ingresado es inválido");
    }

    private static Map<NotificationEnum, String> mapOfEntries() {
        Map<NotificationEnum, String> map = new HashMap<>();
        map.put(NotificationEnum.LOAN_APPROVED, "Uno de los préstamos que solicitó, ¡ha sido aprobado! 🎉");
        map.put(NotificationEnum.LOAN_REJECTED, "Lo sentimos, uno de los préstamos solicitados no ha sido aprobado.");

        return map;
    }
}
