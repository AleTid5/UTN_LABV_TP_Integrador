package UTN.FRGP.TP_L5_GRUPO_1.Services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public abstract class SessionService {
    private static SessionFactory sessionFactory = null;

    static {
        if (SessionService.sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            SessionService.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
    }

    public static Session getSession() {
        Session session = SessionService.sessionFactory.openSession();

        session.beginTransaction();

        return session;
    }

    public static void commitSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    public static void closeSessionFactory() {
        SessionService.sessionFactory.close();
    }
}
