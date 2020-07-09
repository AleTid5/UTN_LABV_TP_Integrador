package UTN.FRGP.TP_L5_GRUPO_1.Models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Integer amount;

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

    public Loan(Account account, Integer amount, Integer feesToPay, String endDate) throws ParseException {
        this();
        this.setAccount(account);
        this.setAmount(amount);
        this.setFeesToPay(feesToPay);
        this.setFeeValue();
        this.setEndDate(endDate);
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFeesToPay() {
        return feesToPay;
    }

    public void setFeesToPay(Integer feesToPay) {
        this.feesToPay = feesToPay;
    }

    public Double getFeeValue() {
        return feeValue;
    }

    public void setFeeValue() {
        this.feeValue = (double) (this.amount / this.feesToPay);
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

    public void setEndDate(String endDate) throws ParseException {
        this.endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
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
