package UTN.FRGP.TP_L5_GRUPO_1.Models;

import javax.persistence.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    public Country() {}

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
