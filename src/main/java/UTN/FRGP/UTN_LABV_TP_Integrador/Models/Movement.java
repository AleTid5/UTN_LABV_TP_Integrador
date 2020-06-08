package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Account originAccount;

    @OneToOne
    @JoinColumn
    private Account destinationAccount;

    @OneToOne
    @JoinColumn
    private CurrencyType currencyType;

    @OneToOne
    @JoinColumn
    private MovementType movementType;

    @Column(nullable = false)
    private Integer amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
