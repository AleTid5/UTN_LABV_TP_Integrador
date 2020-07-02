package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.BankAdministrator;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Loan;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

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
            loans = nonNull(isLoanApproved) ?
                    session.createCriteria(Loan.class)
                    .add(Restrictions.isNotNull("bankAdministrator"))
                    .add(Restrictions.eq("isApproved", isLoanApproved))
                    .list() :
                    session.createCriteria(Loan.class).add(Restrictions.isNull("bankAdministrator")).list();
        } finally {
            SessionService.commitSession(session);
        }

        return loans;
    }

    public static Loan getLoan(Integer loanId) {
        Loan loan;

        try {
            session = SessionService.getSession();
            loan = (Loan) session.createCriteria(Loan.class).add(Restrictions.eq("id", loanId)).uniqueResult();
        } finally {
            SessionService.commitSession(session);
        }

        return loan;
    }

    public static JsonResponse approveLoan(Loan loan, Boolean isApproved) {
        try {
            loan.setIsApproved(isApproved);
            loan.setBankAdministrator(new BankAdministrator(11));
            session = SessionService.getSession();
            session.update(loan);
            session.flush();
            SessionService.commitSession(session);
            return new JsonResponse();
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            return new JsonResponse(false);
        }
    }
}
