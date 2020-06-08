package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Account account;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Integer feesToPay;

    @Column(nullable = false)
    private Integer feeValue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    

   public Loan(Account account, Integer amount, Integer feesToPay, Integer feeValue) {
		this.setAccount(account);
		this.setAmount(amount);
		this.setFeesToPay(feesToPay);
		this.setFeeValue(feeValue);
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
   
   }
