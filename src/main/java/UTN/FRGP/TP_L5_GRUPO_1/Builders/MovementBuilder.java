package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.TransferEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.TransferException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.MovementTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public abstract class MovementBuilder {
    public static Movement build(HttpServletRequest request) throws AccountException, TransferException {
        Movement movement = new Movement();
        Map.Entry<TransferEnum, String> transferEnumStringMap = buildKey(request.getParameter("destinationAccount")).entrySet().iterator().next();

        Account originAccount = AccountService.getAccount(request.getParameter("originAccount"));
        Account destinationAccount = transferEnumStringMap.getKey().equals(TransferEnum.ALIAS)
                ? AccountService.getAccountByAlias(transferEnumStringMap.getValue())
                : AccountService.getAccount(transferEnumStringMap.getValue());

        if (isNull(destinationAccount)) {
            throw new TransferException(AccountEnum.valueOf(transferEnumStringMap.getKey().name()));
        }

        if (originAccount.getCustomer().getId().equals(destinationAccount.getCustomer().getId())) {
            throw new TransferException(AccountEnum.CUSTOMER);
        }

        if (! originAccount.getAccountType().getCurrencyType().getId().equals(destinationAccount.getAccountType().getCurrencyType().getId())) {
            throw new TransferException(AccountEnum.ACCOUNT_TYPE);
        }

        if (originAccount.getBalance() < Double.parseDouble(request.getParameter("amount"))) {
            throw new TransferException(AccountEnum.BALANCE);
        }

        destinationAccount.setBalance(destinationAccount.getBalance() + Double.parseDouble(request.getParameter("amount")));
        originAccount.setBalance(originAccount.getBalance() - Double.parseDouble(request.getParameter("amount")));
        movement.setOriginAccount(originAccount);
        movement.setDestinationAccount(destinationAccount);
        movement.setAmount(Double.parseDouble(request.getParameter("amount")));
        movement.setConcept(request.getParameter("concept"));
        movement.setMovementType(MovementTypeService.getMovementType(MovementTypeEnum.TRANSFER_EXTERNAL_ACCOUNT));

        return movement;
    }

    public static Movement buildMovement(HttpServletRequest request) {
        Movement movement = new Movement();
        movement.setOriginAccount(AccountService.getAccount(request.getParameter("originAccount")));
        movement.setDestinationAccount(AccountService.getAccount(request.getParameter("destinationAccount")));
        movement.setAmount(Double.parseDouble(request.getParameter("amount")));
        movement.setMovementType(MovementTypeService.getMovementType(MovementTypeEnum.TRANSFER_OWN_ACCOUNT));

        movement.getOriginAccount().setBalance(movement.getOriginAccount().getBalance() - movement.getAmount());
        movement.getDestinationAccount().setBalance(movement.getDestinationAccount().getBalance() + movement.getAmount());

        return movement;
    }

    private static Map<TransferEnum, String> buildKey(String keyToBuild) throws AccountException {
        Map<TransferEnum, String> transferEnumStringMap = new HashMap<>();

        if (keyToBuild.matches("[0-9]+")) {
            if (keyToBuild.length() != 22) {
                throw new AccountException(AccountEnum.CBU);
            }

            transferEnumStringMap.put(TransferEnum.CBU, keyToBuild);
        } else {
            if (keyToBuild.length() < 5 || keyToBuild.length() > 30) {
                throw new AccountException(AccountEnum.ALIAS);
            }

            transferEnumStringMap.put(TransferEnum.ALIAS, keyToBuild);
        }

        return transferEnumStringMap;
    }
}
