package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

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
    @JoinColumn(nullable = true)
    private BankAdministrator bankAdministrator;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Integer payedFees;

    @Column(nullable = false)
    private Integer feesToPay;

    @Column(nullable = false)
    private Integer feeValue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private Boolean isApproved;

    private Date endDate;

    public Loan(Account account, Integer amount, Integer feesToPay, Integer feeValue, String endDate) throws ParseException {
        this.setAccount(account);
        this.setAmount(amount);
        this.setFeesToPay(feesToPay);
        this.setFeeValue(feeValue);
        this.setPayedFees(0);
        this.setBankAdministrator(null);
        this.setEndDate(endDate);
        this.setIsApproved(false);
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

    public Integer getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(Integer feeValue) {
        this.feeValue = feeValue;
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
}
