package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import com.mysql.cj.xdevapi.Client;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class Account {
    @Id
    @Column(unique = true, nullable = false)
    private Integer CBU;

    @Column(nullable = false)
    private String alias;

    @ManyToOne
    @JoinColumn
    private Client client;

    @OneToMany
    @JoinColumn
    private AccountType accountType;

    @Column(nullable = false)
    private BigInteger balance;

    @Column(nullable = false)
    private Integer accountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
