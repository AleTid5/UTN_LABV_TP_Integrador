package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;

@Entity
public class MovementType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Transferencia propia cuenta
     * Transferencia cuenta terceros
     * Compra dolares
     * Venta dolares
     * Transfiere dolares
     */
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @OneToOne
    @JoinColumn
    private CurrencyType currencyType;

    public MovementType(String name, CurrencyType currencyType)
    {
        this.setName(name);
        this.setCurrencyType(currencyType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
