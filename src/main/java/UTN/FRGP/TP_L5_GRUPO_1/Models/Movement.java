package UTN.FRGP.TP_L5_GRUPO_1.Models;

import UTN.FRGP.TP_L5_GRUPO_1.Listeners.MovementListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(MovementListener.class)
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
    private MovementType movementType;

    @Column(nullable = false)
    private Double amount;

    @Column(length = 50)
    private String concept;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    public Movement() {}

    public Movement(Account originAccount, Account destinationAccount, MovementType movementType, Double amount) {
        this.setOriginAccount(originAccount);
        this.setDestinationAccount(destinationAccount);
        this.setMovementType(movementType);
        this.setAmount(amount);
    }

    public Movement(Account originAccount, Account destinationAccount, MovementType movementType, Integer amount, String concept) {
        this(originAccount, destinationAccount, movementType, amount);
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

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}
