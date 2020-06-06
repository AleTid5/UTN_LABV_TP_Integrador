package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Integer feesToPay;

    @Column(nullable = false)
    private Integer feeValue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
