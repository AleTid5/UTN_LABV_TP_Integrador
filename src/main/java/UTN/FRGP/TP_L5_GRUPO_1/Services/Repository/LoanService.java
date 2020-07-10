package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.MovementTypeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.NotificationEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.ErrorCodeException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.BankAdministrator;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Movement;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class LoanService {

    @Autowired
    private static Session session;

    @Autowired
    private static List<Loan> loans;

    public static List<Loan> getLoans(Boolean isLoanApproved) {
        try {
            session = SessionService.getSession();
            loans = nonNull(isLoanApproved)
                    ? session.createCriteria(Loan.class)
                    .add(Restrictions.isNotNull("bankAdministrator"))
                    .add(Restrictions.eq("isApproved", isLoanApproved))
                    .list()
                    : session.createCriteria(Loan.class)
                    .add(Restrictions.isNull("bankAdministrator"))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return loans;
    }

    public static List<Loan> getLoansByCustomerId(Boolean isLoanApproved, Integer customerId) {
        List<Account> accounts = AccountService.getAccounts(customerId);

        if (accounts.size() == 0) return new ArrayList<>();

        try {
            session = SessionService.getSession();
            loans = nonNull(isLoanApproved)
                    ? session.createCriteria(Loan.class)
                    .add(Restrictions.isNotNull("bankAdministrator"))
                    .add(Restrictions.eq("isApproved", isLoanApproved))
                    .add(Restrictions.eq("isPayed", false))
                    .add(Restrictions.in("account", accounts))
                    .list()
                    : session.createCriteria(Loan.class)
                    .add(Restrictions.isNull("bankAdministrator"))
                    .add(Restrictions.eq("isPayed", false))
                    .add(Restrictions.in("account", accounts))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return loans;
    }

    public static Loan getLoan(Integer loanId) {
        Loan loan;

        try {
            session = SessionService.getSession();
            loan = (Loan) session.createCriteria(Loan.class)
                    .add(Restrictions.eq("id", loanId))
                    .uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return loan;
    }

    public static void saveLoan(Loan loan) {
        try {
            session = SessionService.getSession();
            session.save(loan);
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }
    }

    public static JsonResponse approveLoan(Loan loan, Boolean isApproved, Integer administratorId) {
        try {
            if (isApproved) {
                loan.getAccount().setBalance(loan.getAccount().getBalance() + loan.getAmount());
                AccountService.updateAccount(loan.getAccount());
                MovementService.saveMovement(new Movement(
                        null,
                        loan.getAccount(),
                        MovementTypeService.getMovementType(MovementTypeEnum.APPROVED_LOAN),
                        loan.getAmount()
                ));
            }

            loan.setIsApproved(isApproved);
            loan.setBankAdministrator(new BankAdministrator(administratorId));
            session = SessionService.getSession();
            session.update(loan);
            session.flush();
            SessionService.commitSession(session);
            return new JsonResponse();
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            return new JsonResponse(false);
        } finally {
            NotificationService.addNotification(
                    loan.getAccount().getCustomer(),
                    isApproved ? NotificationEnum.LOAN_APPROVED : NotificationEnum.LOAN_REJECTED);
        }
    }

    public static JsonResponse payLoan(Loan loan, Account account) {
        try {
            LoanService.canLoanBePayed(loan, account);
            loan.setPayedFees(loan.getPayedFees() + 1);
            account.setBalance(account.getBalance() - loan.getFeeValue());
            session = SessionService.getSession();
            session.update(loan);
            session.update(account);
            session.flush();
            SessionService.commitSession(session);
            return new JsonResponse();
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            return new JsonResponse(e.getMessage(),false);
        }
    }

    private static void canLoanBePayed(Loan loan, Account account) throws AccountException, ErrorCodeException {
        if (loan == null || account == null) {
            throw new ErrorCodeException(ErrorCodeEnum.MISSING_DATA.name());
        }

        // Los tipos de cuenta tienen que ser iguales.
        if (!loan.getAccount().getAccountType().getCurrencyType().getId().equals(account.getAccountType().getCurrencyType().getId())) {
            throw new AccountException(AccountEnum.ACCOUNT_TYPE);
        }

        // El valor de la cuota no puede ser mayor que el balance en cuenta.
        if (loan.getFeeValue() > account.getBalance()) {
            throw new AccountException(AccountEnum.BALANCE);
        }
    }
}
