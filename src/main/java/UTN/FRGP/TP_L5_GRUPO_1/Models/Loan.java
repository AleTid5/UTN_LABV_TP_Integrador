package UTN.FRGP.TP_L5_GRUPO_1.Models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Account account;

    @OneToOne
    @JoinColumn
    private BankAdministrator bankAdministrator;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer payedFees;

    @Column(nullable = false)
    private Integer feesToPay;

    @Column(nullable = false)
    private Double feeValue;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date creationDate;

    private Boolean isApproved;

    @Formula(value = "payedFees >= feesToPay")
    private Boolean isPayed;

    private Date endDate;

    public Loan() {
        this.setBankAdministrator(null);
        this.setIsApproved(false);
        this.setPayedFees(0);
        this.creationDate = new Date();
    }

    public Loan(Account account, Double amount, Integer feesToPay) {
        this();
        this.setAccount(account);
        this.setAmount(amount);
        this.setFeesToPay(feesToPay);
        this.setFeeValue();
    }

    public Integer getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getFeesToPay() {
        return feesToPay;
    }

    public void setFeesToPay(Integer feesToPay) {
        this.endDate = java.sql.Date.valueOf(LocalDate.now().plusMonths(feesToPay));

        this.feesToPay = feesToPay;
    }

    public Double getFeeValue() {
        return Math.floor(this.feeValue * 100) / 100;
    }

    public void setFeeValue() {
        this.feeValue = this.amount / this.feesToPay;
    }

    public BankAdministrator getBankAdministrator() {
        return bankAdministrator;
    }

    public void setBankAdministrator(BankAdministrator bankAdministrator) {
        this.bankAdministrator = bankAdministrator;
    }

    public Integer getPayedFees() {
        return payedFees;
    }

    public void setPayedFees(Integer payedFees) {
        this.payedFees = payedFees;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsApproved() {
        return this.isApproved;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Boolean isPayed() {
        return this.isPayed;
    }
}
