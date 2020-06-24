package UTN.FRGP.TP_L5_GRUPO_1.Models;

import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;
import UTN.FRGP.TP_L5_GRUPO_1.Services.LoggerService;

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

    @Column(unique = true, nullable = false)
    private Integer dni;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(nullable = false)
    private Boolean isActive;

    public User() {
        this.creationDate = new Date();
    }

    public User(Integer dni, String name, String lastName, String email, String password) throws UserException {
        this.setDni(dni);
        this.setName(name);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setIsActive(true);
        this.creationDate = new Date();
    }
    
    public User(Integer id){
    	this.id=id;
    }

    public void setId(Integer id) throws UserException {
        if (id == null) {
            throw new UserException(UTN.FRGP.TP_L5_GRUPO_1.Enums.User.ID);
        }

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDni(Integer dni) throws UserException {
        if (dni == null || dni < 0) {
            throw new UserException(UTN.FRGP.TP_L5_GRUPO_1.Enums.User.DNI);
        }

        this.dni = dni;
    }

    public Integer getDni() {
        return dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        try {
            BigInteger number = new BigInteger(1, MessageDigest.getInstance("MD5").digest(password.getBytes()));
            this.password = number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            LoggerService.log(e.getMessage());
        }
    }

    public String getPassword() {
        return password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
