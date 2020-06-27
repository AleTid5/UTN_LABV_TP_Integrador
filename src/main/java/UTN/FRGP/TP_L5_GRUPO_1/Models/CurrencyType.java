package UTN.FRGP.TP_L5_GRUPO_1.Models;

import javax.persistence.*;

@Entity
public class CurrencyType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private Boolean isForeignCurrency;

    public CurrencyType() {}

    public CurrencyType(String name, Boolean isForeignCurrency) {
        this.name = name;
        this.isForeignCurrency = isForeignCurrency;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
