package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import UTN.FRGP.UTN_LABV_TP_Integrador.Models.AccountType;
import UTN.FRGP.UTN_LABV_TP_Integrador.Models.CurrencyType;
import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Movement;
import UTN.FRGP.UTN_LABV_TP_Integrador.Models.MovementType;
import org.hibernate.Session;

import java.util.ArrayList;

public class MovementSeeder extends Seeder {
    public static final ArrayList<MovementType> movementTypes = new ArrayList<MovementType>() {
        {
            add(new MovementType("Transferencia propia cuenta", CurrencySeeder.currencyTypes.get(0)));
            add(new MovementType("Transferencia cuenta terceros", CurrencySeeder.currencyTypes.get(0)));
            add(new MovementType("Compra dolares", CurrencySeeder.currencyTypes.get(1)));
            add(new MovementType("Venta dolares", CurrencySeeder.currencyTypes.get(1)));
            add(new MovementType("Transfiere dolares", CurrencySeeder.currencyTypes.get(1)));
        }
    };

    public static final ArrayList<Movement> movements = new ArrayList<Movement>() {
        {
            add(new Movement(AccountSeeder.accounts.get(0),AccountSeeder.accounts.get(2), CurrencySeeder.currencyTypes.get(0),
                    movementTypes.get(0),10000, "Pago Servicio"));

        }
    };

    public static void hydrate(Session session) {
        movementTypes.forEach(session::save);
        movements.forEach(session::save);
    }
}

