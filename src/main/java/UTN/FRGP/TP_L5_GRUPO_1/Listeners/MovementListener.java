package UTN.FRGP.TP_L5_GRUPO_1.Listeners;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Models.CurrencyValue;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CurrencyService;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Arrays;
import java.util.List;

public class MovementListener {
    @PrePersist
    @PreUpdate
    public void preSaveMovement(final Movement movement) throws Exception {
        if (movement.getAmount() > movement.getOriginAccount().getBalance()) {
            throw new Exception("No puede hacer la transaccion.");
        }

        List<MovementTypeEnum> specialMovementTypes = Arrays.asList(
                MovementTypeEnum.BUY_DOLLARS,
                MovementTypeEnum.SELL_DOLLARS,
                MovementTypeEnum.TRANSFER_DOLLARS
        );

        if (specialMovementTypes.contains(movement.getMovementType().getName())) {
            CurrencyValue currencyValue = CurrencyService.getCurrencyValueByCurrencyTypeName("USD");

            switch (movement.getMovementType().getName()) {
                case BUY_DOLLARS: {
                    // ToDo: Logic to USD
                    break;
                }
                case SELL_DOLLARS: {
                    // ToDo: Logic from USD
                    break;
                }
                case TRANSFER_DOLLARS: {
                    // ToDo: From account To account must be the same type.
                    break;
                }
                default: {
                    throw new Exception("Acción no soportada");
                }
            }
        }
    }

    @PostPersist
    @PostUpdate
    public void postSaveMovement(final Movement movement) {
        // ToDo: Make account the movement.
    }
}
