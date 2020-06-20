package UTN.FRGP.TP_L5_GRUPO_1.Models;

import javax.persistence.*;

@Entity
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToOne
    @JoinColumn
    private Province province;

    public Locality() {}

    public Locality(String name, Province province) {
        this.name = name;
        this.province = province;
    }

    public Locality(Integer id) {
        this.id = id;
        this.name = null;
        this.province = new Province();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Province getProvince() {
        return province;
    }
}
