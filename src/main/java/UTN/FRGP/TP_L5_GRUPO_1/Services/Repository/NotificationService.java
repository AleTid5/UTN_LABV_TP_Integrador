package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.NotificationEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Notification;
import UTN.FRGP.TP_L5_GRUPO_1.Models.User;
import UTN.FRGP.TP_L5_GRUPO_1.Services.EmailService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class NotificationService {

    @Autowired
    private static Session session;

    @Autowired
    private static List<Notification> notifications;

    public static void addNotification(User user, NotificationEnum message) {
        try {
            session = SessionService.getSession();
            session.save(new Notification(user, message));
            SessionService.commitSession(session);
            EmailService.sendEmail(user.getEmail(), message);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
            e.printStackTrace();
        }
    }

    public static String getNotifications(Integer userId) {
        StringBuilder explodedNotifications = new StringBuilder();

        try {
            session = SessionService.getSession();
            notifications = session.createCriteria(Notification.class)
                    .add(Restrictions.eq("user.id", userId))
                    .add(Restrictions.eq("hasBeenSeen", false))
                    .list();

            notifications.forEach((Notification notification) -> {
                explodedNotifications.append(notification.getMessage().name()).append(",");
                notification.setHasBeenSeen(true);
                session.update(notification);
                session.flush();
            });
        } finally {
            SessionService.commitSession(session);
        }

        return explodedNotifications.toString().length() > 0 ?
                explodedNotifications.toString().substring(0, explodedNotifications.toString().length() - 1) : // Borra la ultima coma
                explodedNotifications.toString();
    }
}
