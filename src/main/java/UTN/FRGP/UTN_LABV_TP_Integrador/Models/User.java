package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import UTN.FRGP.UTN_LABV_TP_Integrador.Services.LoggerService;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer dni;
    private String name;
    private String lastName;
    private String email;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public User(Integer dni, String name, String lastName, String email, String password) {
        try {
            this.dni = dni;
            this.name = name;
            this.lastName = lastName;
            this.email = email;
            BigInteger number = new BigInteger(1, MessageDigest.getInstance("MD5").digest(password.getBytes()));
            this.password = number.toString(16);
            this.creationDate = new Date();
        } catch (NoSuchAlgorithmException e) {
            LoggerService.log(e.getMessage());
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
