package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Models.MovementType;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class MovementSeeder extends Seeder {
    public static final List<MovementType> movementTypes = Arrays.asList(
            new MovementType(MovementTypeEnum.TRANSFER_OWN_ACCOUNT, CurrencySeeder.currencyTypes.get(0)),
            new MovementType(MovementTypeEnum.TRANSFER_EXTERNAL_ACCOUNT, CurrencySeeder.currencyTypes.get(0)),
            new MovementType(MovementTypeEnum.BUY_DOLLARS, CurrencySeeder.currencyTypes.get(1)),
            new MovementType(MovementTypeEnum.SELL_DOLLARS, CurrencySeeder.currencyTypes.get(1)),
            new MovementType(MovementTypeEnum.TRANSFER_DOLLARS, CurrencySeeder.currencyTypes.get(1))
    );

    public static final List<Movement> movements = Arrays.asList(
            new Movement(AccountSeeder.accounts.get(0), AccountSeeder.accounts.get(2), movementTypes.get(0), 10000, "Arreglo chapista"),
            new Movement(AccountSeeder.accounts.get(0), AccountSeeder.accounts.get(1), movementTypes.get(0), 10000, "Mudanza"),
            new Movement(AccountSeeder.accounts.get(0), AccountSeeder.accounts.get(0), movementTypes.get(1), 18000)
    );

    public void hydrate(Session session) {
        movementTypes.forEach(session::save);
        movements.forEach(session::save);
    }
}

