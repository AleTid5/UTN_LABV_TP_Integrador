package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Models.MovementType;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CurrencyService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementTypeService;

import javax.servlet.http.HttpServletRequest;

public abstract class DollarBuilder {
    public static Movement buildDollarPurchase(HttpServletRequest request) {
        Double purchaseValue = CurrencyService.getCurrencyValueByCurrencyTypeName("USD").getPurchaseValue();

        return buildMovement(request, MovementTypeEnum.BUY_DOLLARS, purchaseValue);
    }

    public static Movement buildDollarSale(HttpServletRequest request) {
        Double saleValue = CurrencyService.getCurrencyValueByCurrencyTypeName("USD").getSaleValue();

        return buildMovement(request, MovementTypeEnum.SELL_DOLLARS, saleValue);
    }

    private static Movement buildMovement(HttpServletRequest request, MovementTypeEnum movementTypeEnum, Double value) {
        Movement movement = new Movement(
                AccountService.getAccount(request.getParameter("originAccount")),
                AccountService.getAccount(request.getParameter("destinationAccount")),
                MovementTypeService.getMovementType(movementTypeEnum),
                Double.parseDouble(request.getParameter("amount"))
        );

        movement.getOriginAccount().setBalance(movement.getOriginAccount().getBalance() - movement.getAmount());

        if (movementTypeEnum.equals(MovementTypeEnum.BUY_DOLLARS)) {
            movement.getDestinationAccount().setBalance(movement.getDestinationAccount().getBalance() + movement.getAmount() / value);
        } else {
            movement.getDestinationAccount().setBalance(movement.getDestinationAccount().getBalance() + movement.getAmount() * value);
        }

        return movement;
    }
}
