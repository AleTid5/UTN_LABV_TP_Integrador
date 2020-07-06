package UTN.FRGP.TP_L5_GRUPO_1.Services;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.NotificationEnum;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public abstract class EmailService {
    private static final String PASSWORD = "bank123bank";
    private static final String EMAIL_FROM = "bankhello2020@gmail.com";
    private static final Message message;
    private static final Session session;

    static {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL_FROM,PASSWORD);
                    }
                });
        message = new MimeMessage(session);
    }

    public static void sendEmail(String userEmail, NotificationEnum notification) {
        try {
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmail, false));
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse("aleetidele@gmail.com", false));
            message.setSubject(notification.equals(NotificationEnum.LOAN_APPROVED) ?
                    "Se ha aprobado un prestamo!" :
                    "Un prestamo ha sido rechazado");
            message.setText(notification.equals(NotificationEnum.LOAN_APPROVED) ?
                    "Nos ponemos en contacto para informarle que se le ha aprobado un prestamo!" :
                    "Nos ponemos en contacto para informarle que un prestamo ha sido rechazado.");

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
