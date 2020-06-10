package UTN.FRGP.TP_L5_GRUPO_1.Models;

import javax.persistence.*;

@Entity
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToOne
    @JoinColumn
    private Country country;

    public Province(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
}
