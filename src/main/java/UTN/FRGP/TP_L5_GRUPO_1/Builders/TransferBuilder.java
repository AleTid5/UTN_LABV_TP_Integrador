package UTN.FRGP.TP_L5_GRUPO_1.Builders;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.TransferEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class TransferBuilder {
    public static Map<Account, Account> build(HttpServletRequest request) throws AccountException {
        Map<Account, Account> accounts = new HashMap<>();
        String destinationAccountStr = request.getParameter("destinationAccount");

        try {
            Map.Entry<TransferEnum, String> transferEnumStringMap = buildKey(destinationAccountStr).entrySet().iterator().next();
            Account originAccount = AccountService.getAccount(request.getParameter("originCBU"));
            Account destinationAccount;

            if (transferEnumStringMap.getKey().equals(TransferEnum.ALIAS)) {
                destinationAccount=AccountService.getAccountByAlias(destinationAccountStr);
            } else {
                destinationAccount = AccountService.getAccount(destinationAccountStr);
            }

            if (originAccount.getBalance() < Double.parseDouble(request.getParameter("amount"))){
                throw new AccountException(AccountEnum.BALANCE);
            }
            if (! originAccount.getAccountType().getCurrencyType().getId().equals(destinationAccount.getAccountType().getCurrencyType().getId())){
                throw new AccountException(AccountEnum.ACCOUNT_TYPE);
            }
            if (originAccount.getCBU().equals(destinationAccount.getCBU())){
                throw new AccountException(AccountEnum.CBU);
            }

            destinationAccount.setBalance(destinationAccount.getBalance()+Double.parseDouble(request.getParameter("amount")));
            accounts.put(originAccount,destinationAccount);
        } catch (AccountException exception) {
            throw exception;
        }

        return accounts;
    }

    private static Map<TransferEnum, String> buildKey(String keyToBuild) throws AccountException {
        Map<TransferEnum, String> transferEnumStringMap = new HashMap<>();

        if (keyToBuild.contains(".")) {
            if (keyToBuild.length() < 5 || keyToBuild.length() > 20) {
                throw new AccountException(AccountEnum.ALIAS);
            }

            transferEnumStringMap.put(TransferEnum.ALIAS, keyToBuild);
        } else {
            if (keyToBuild.length() != 22) {
                throw new AccountException(AccountEnum.CBU);
            }

            transferEnumStringMap.put(TransferEnum.CBU, keyToBuild);
        }

        return transferEnumStringMap;
    }

}
