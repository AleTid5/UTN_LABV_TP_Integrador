package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.User;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public abstract class LoginService {

    @Autowired
    private static Session session;

    public static User authenticateUser(String email, String password) throws LoginException {
        User user;

        try {
            session = SessionService.getSession();
            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("email", email))
                    .add(Restrictions.eq("password", User.getHashedPassword(password)))
                    .add(Restrictions.eq("isActive", true))
                    .uniqueResult();

            if (user == null) {
                throw new LoginException();
            }
        } finally {
            SessionService.commitSession(session);
        }

        return user;
    }
}
