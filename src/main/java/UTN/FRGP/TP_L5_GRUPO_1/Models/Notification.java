package UTN.FRGP.TP_L5_GRUPO_1.Models;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.NotificationEnum;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private User user;

    private NotificationEnum message;

    private Boolean hasBeenSeen;

    public Notification() {
        this.hasBeenSeen = false;
    }

    public Notification(User user, NotificationEnum message) {
        this();
        this.user = user;
        this.message = message;
    }

    public NotificationEnum getMessage() {
        return message;
    }

    public void setMessage(NotificationEnum message) {
        this.message = message;
    }

    public Boolean getHasBeenSeen() {
        return hasBeenSeen;
    }

    public void setHasBeenSeen(Boolean hasBeenSeen) {
        this.hasBeenSeen = hasBeenSeen;
    }
}
