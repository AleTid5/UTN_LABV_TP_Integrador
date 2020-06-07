package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import UTN.FRGP.UTN_LABV_TP_Integrador.Enums.UserEnum;
import UTN.FRGP.UTN_LABV_TP_Integrador.Exceptions.UserException;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Customer extends User {
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

    public void setUserName(String userName) throws UserException {
        if (userName == null || userName.length() < 5 || userName.length() > 20) {
            throw new UserException(UserEnum.ADDRESS);
        }

        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setGender(String gender) throws UserException {
        if (gender == null || gender.length() > 1) {
            throw new UserException(UserEnum.GENDER);
        }

        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setBornDate(String bornDate) throws ParseException, UserException {
        if (bornDate == null) {
            throw new UserException(UserEnum.BORN_DATE);
        }

        this.bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(bornDate);
    }

    public Date getBornDate() {
        return this.bornDate;
    }

    public void setAddress(String address) throws UserException {
        if (address == null || address.length() < 5 || address.length() > 50) {
            throw new UserException(UserEnum.ADDRESS);
        }

        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setLocality(Locality locality) throws UserException {
        if (locality == null) {
            throw new UserException(UserEnum.LOCALITY);
        }

        this.locality = locality;
    }

    public Locality getLocality() {
        return this.locality;
    }

    public void setMaxLoanAmount(Integer maxLoanAmount) throws UserException {
        if (maxLoanAmount == null || maxLoanAmount < 0) {
            throw new UserException(UserEnum.MAX_LOAN_AMOUNT);
        }

        this.maxLoanAmount = maxLoanAmount;
    }

    public Integer getMaxLoanAmount() {
        return this.maxLoanAmount;
    }
}
