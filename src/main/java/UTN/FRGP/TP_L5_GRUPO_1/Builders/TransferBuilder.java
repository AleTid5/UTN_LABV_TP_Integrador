package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.TransferEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.TransferException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public abstract class TransferBuilder {
    public static Map.Entry<Account, Account> build(HttpServletRequest request) throws AccountException, TransferException {
        Map<Account, Account> accounts = new HashMap<>();

        Map.Entry<TransferEnum, String> transferEnumStringMap = buildKey(request.getParameter("destinationAccount")).entrySet().iterator().next();
        Account originAccount = AccountService.getAccount(request.getParameter("originAccount"));
        Account destinationAccount;

        destinationAccount = transferEnumStringMap.getKey().equals(TransferEnum.ALIAS)
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
        accounts.put(originAccount,destinationAccount);

        return accounts.entrySet().iterator().next();
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
