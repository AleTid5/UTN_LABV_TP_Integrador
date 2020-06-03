package UTN.FRGP.UTN_LABV_TP_Integrador.Models;

import javax.persistence.*;

@Entity
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="locality_id")
    private Integer id;
    private String name;
    @OneToOne
    @JoinColumn(referencedColumnName = "province_id")
    private Province province;

    public Locality(String name, Province province) {
        this.name = name;
        this.province = province;
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
