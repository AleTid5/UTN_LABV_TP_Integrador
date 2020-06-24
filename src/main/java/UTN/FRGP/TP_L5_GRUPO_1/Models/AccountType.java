package UTN.FRGP.TP_L5_GRUPO_1.Models;

import javax.persistence.*;

@Entity
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToOne
    @JoinColumn(updatable = false)
    private CurrencyType currencyType;

    public AccountType() {}

    public AccountType(String name, CurrencyType currencyType) {
        this.name = name;
        this.currencyType = currencyType;
    }
    public AccountType(int accountTypeId) {
    	this.id=accountTypeId;
    	this.name = null;
        this.currencyType = null;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
