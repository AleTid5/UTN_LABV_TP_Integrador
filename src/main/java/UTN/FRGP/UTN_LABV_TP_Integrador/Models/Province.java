package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;

@Entity
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="province_id")
    private Integer id;
    private String name;
    @OneToOne
    @JoinColumn(referencedColumnName = "country_id")
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
