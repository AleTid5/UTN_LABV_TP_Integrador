package UTN.FRGP.TP_L5_GRUPO_1.Models;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.User;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Customer extends UTN.FRGP.TP_L5_GRUPO_1.Models.User {
    @Column(unique = true, length = 20)
    private String userName;

    private Integer maxLoanAmount;

    @OneToOne
    @JoinColumn
    private Locality locality;

    @Column(length = 50)
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bornDate;

    @Column(length = 1)
    private String gender;

    public Customer() {
        this.setIsActive(true);
    }

    public Customer(Integer dni, String name, String lastName, String email, String password, String userName, Integer maxLoanAmount,
                    Locality locality, String address, String bornDate, String gender) throws ParseException, UserException {
        super(dni, name, lastName, email, password);
        this.setUserName(userName);
        this.setGender(gender);
        this.setBornDate(bornDate);
        this.setMaxLoanAmount(maxLoanAmount);
        this.setLocality(locality);
        this.setAddress(address);
    }
    
    public Customer(Integer id){
    	super(id);
    }

    public void setUserName(String userName) throws UserException {
        if (userName == null || userName.length() < 5 || userName.length() > 20) {
            throw new UserException(User.ADDRESS);
        }

        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setGender(String gender) throws UserException {
        if (gender == null || gender.length() > 1) {
            throw new UserException(User.GENDER);
        }

        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public String parseBornDate(String bornDate) throws ParseException, UserException {
        if (bornDate == null) {
            throw new UserException(User.BORN_DATE);
        }

        Date initDate = new SimpleDateFormat("MM/dd/yyyy").parse(bornDate);
        return new SimpleDateFormat("yyyy-MM-dd").format(initDate);
    }

    public void setBornDate(String bornDate) throws ParseException, UserException {
        if (bornDate == null) {
            throw new UserException(User.BORN_DATE);
        }

        Date formattedBornDate = new SimpleDateFormat("yyyy-MM-dd").parse(bornDate);

        if (formattedBornDate.compareTo(new Date()) > 0) {
            throw new UserException(User.BORN_DATE);
        }

        this.bornDate = formattedBornDate;
    }

    public Date getBornDate() {
        return this.bornDate;
    }

    public String getParsedBornDate() {
        return new SimpleDateFormat("MM/dd/yyyy").format(this.bornDate);
    }

    public void setAddress(String address) throws UserException {
        if (address == null || address.length() < 5 || address.length() > 50) {
            throw new UserException(User.ADDRESS);
        }

        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setLocality(Locality locality) throws UserException {
        if (locality == null) {
            throw new UserException(User.LOCALITY);
        }

        this.locality = locality;
    }

    public void setLocality(Integer localityId) throws UserException {
        if (localityId == null) {
            throw new UserException(User.LOCALITY);
        }

        this.locality = new Locality(localityId);
    }

    public Locality getLocality() {
        return this.locality;
    }

    public void setMaxLoanAmount(Integer maxLoanAmount) throws UserException {
        if (maxLoanAmount == null || maxLoanAmount < 0) {
            throw new UserException(User.MAX_LOAN_AMOUNT);
        }

        this.maxLoanAmount = maxLoanAmount;
    }

    public Integer getMaxLoanAmount() {
        return this.maxLoanAmount;
    }

    @Override
    public String toString() {
        return super.getLastName() + ", " + super.getName();
    }
}
