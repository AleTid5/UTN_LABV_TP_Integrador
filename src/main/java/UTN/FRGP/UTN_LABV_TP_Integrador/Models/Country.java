package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="country_id")
    private Integer id;
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
