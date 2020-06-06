package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CurrencyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private Integer purchaseValue;

    @Column(nullable = false, updatable = false)
    private Integer saleValue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(nullable = true)
    private Date endDate;
}
