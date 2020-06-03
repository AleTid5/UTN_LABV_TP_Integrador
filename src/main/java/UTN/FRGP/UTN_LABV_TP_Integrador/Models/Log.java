package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public Log(String message) {
        this.message = message;
    }
}
