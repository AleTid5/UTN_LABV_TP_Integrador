package UTN.FRGP.TP_L5_GRUPO_1.Models;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.AccountEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Entity
public class Account {
    @Id
    @Column(unique = true, nullable = false)
    private String CBU;

    @Column(unique = true, nullable = false, length = 30)
    private String alias;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToOne
    @JoinColumn
    private AccountType accountType;

    @Column(nullable = false)
    private Double balance;

    @Column(unique = true, nullable = false)
    private Integer accountNumber;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

	@Column(nullable = false)
    private Boolean isActive;

    public Account() {
		this.createCBU();
		this.setBalance(10000.0);
		this.setIsActive(true);
		this.creationDate = new Date();
	}

	public Account(String alias, Customer customer, AccountType accountType, Integer accountNumber) {
		this.createCBU();
		this.setAlias(alias);
		this.setCustomer(customer);
		this.setAccountType(accountType);
		this.setBalance(10000.0);
		this.setAccountNumber(accountNumber);
		this.setIsActive(true);
		this.creationDate = new Date();
	}

	private void createCBU() {
		String availableNumbers = "1234567890";
        StringBuilder CBU = new StringBuilder();
        Random rnd = new Random();

        while (CBU.length() < 22) {
            CBU.append(availableNumbers.charAt((int) (rnd.nextFloat() * availableNumbers.length())));
        }

        this.CBU = CBU.toString();
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
		this.alias = alias.toUpperCase();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setCustomer(Integer customerId) throws AccountException {
	    if (customerId == null) {
            throw new AccountException(AccountEnum.CUSTOMER);
        }

        this.customer = new Customer(customerId);
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public void setAccountType(Integer accountTypeId) throws AccountException {
	    if (accountTypeId == null) {
            throw new AccountException(AccountEnum.ACCOUNT_TYPE);
        }

        this.accountType = new AccountType(accountTypeId);
	}
	
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreationDate() {
		return creationDate;
	}
}
