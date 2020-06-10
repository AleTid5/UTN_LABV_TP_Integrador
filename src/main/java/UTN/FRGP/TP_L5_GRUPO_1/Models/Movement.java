package UTN.FRGP.TP_L5_GRUPO_1.Models;

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

    @Column(length = 50)
    private String concept;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    public Movement(Account originAccount, Account destinationAccount, CurrencyType currencyType,
                    MovementType movementType, Integer amount, String concept) {
        this.setOriginAccount(originAccount);
        this.setDestinationAccount(destinationAccount);
        this.setCurrencyType(currencyType);
        this.setMovementType(movementType);
        this.setAmount(amount);
        this.setConcept(concept);
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}
