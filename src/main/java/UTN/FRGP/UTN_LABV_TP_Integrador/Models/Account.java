package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {
    @Id
    @Column(unique = true, nullable = false)
    private String CBU;

    @Column(nullable = false)
    private String alias;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToOne
    @JoinColumn
    private AccountType accountType;

    @Column(nullable = false)
    private Integer balance;

    @Column(nullable = false)
    private Integer accountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

	public Account(String CBU, String alias, Customer customer, AccountType accountType, Integer accountNumber) {
		this.setCBU(CBU);
		this.setAlias(alias);
		this.setCustomer(customer);
		this.setAccountType(accountType);
		this.setBalance(10000);
		this.setAccountNumber(accountNumber);
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
}
