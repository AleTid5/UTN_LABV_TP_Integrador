package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CurrencyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private Double purchaseValue;

    @Column(nullable = false, updatable = false)
    private Double saleValue;

    @OneToOne
    @JoinColumn(updatable = false)
    private CurrencyType currencyType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(nullable = true)
    private Date endDate;

    public CurrencyValue(CurrencyType currencyType, Double purchaseValue, Double saleValue, Date endDate) {
        this.currencyType = currencyType;
        this.purchaseValue = purchaseValue;
        this.saleValue = saleValue;
        this.endDate = endDate;
    }
}
