package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer extends User {
    private Integer maxLoanAmount;
    @OneToOne
    @JoinColumn
    private Locality locality;
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date bornDate;
    private String gender;

    public Customer(Integer dni, String name, String lastName, String email, String password, Integer maxLoanAmount,
                    Locality locality, String address, Date bornDate, String gender) {
        super(dni, name, lastName, email, password);
        this.maxLoanAmount = maxLoanAmount;
        this.locality = locality;
        this.address = address;
        this.bornDate = bornDate;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public String getAddress() {
        return address;
    }

    public Locality getLocality() {
        return locality;
    }

    public Integer getMaxLoan() {
        return maxLoanAmount;
    }
}
