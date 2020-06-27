package UTN.FRGP.TP_L5_GRUPO_1.Seeders;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Country;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Locality;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Province;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class LocationSeeder extends Seeder {
    public static final List<Country> countries = Arrays.asList(
            new Country("Argentina"),
            new Country("Brasil"),
            new Country("Chile"),
            new Country("Uruguay"),
            new Country("Bolivia"),
            new Country("Paraguay"),
            new Country("Perú"),
            new Country("Colombia"),
            new Country("México"),
            new Country("Venezuela")
    );

    public static final List<Province> provinces = Arrays.asList(
            new Province("Buenos Aires", countries.get(0)),
            new Province("Santa fé", countries.get(0)),
            new Province("San Pablo", countries.get(1)),
            new Province("Florianópolis", countries.get(1)),
            new Province("Santiago", countries.get(2)),
            new Province("Viña del mar", countries.get(2)),
            new Province("Montevideo", countries.get(3)),
            new Province("Punta del este", countries.get(3)),
            new Province("La paz", countries.get(4)),
            new Province("Cochabamba", countries.get(4)),
            new Province("Asunción", countries.get(5)),
            new Province("Areguá", countries.get(5)),
            new Province("Lima", countries.get(6)),
            new Province("Cusco", countries.get(6)),
            new Province("Bogotá", countries.get(7)),
            new Province("Cartagena", countries.get(7)),
            new Province("México D.F.", countries.get(8)),
            new Province("Monterrey", countries.get(8)),
            new Province("Caracas", countries.get(9)),
            new Province("Maracaibo", countries.get(9))
    );

    public static final List<Locality> localities = Arrays.asList(
            new Locality("Vicente Lopez", provinces.get(0)),
            new Locality("General Pacheco", provinces.get(1)),
            new Locality("Campo grandhe", provinces.get(2)),
            new Locality("Guaratiba", provinces.get(3)),
            new Locality("Las condes", provinces.get(4)),
            new Locality("Puente-alto", provinces.get(5)),
            new Locality("Las piedras", provinces.get(6)),
            new Locality("Ciudad de la costa", provinces.get(7)),
            new Locality("Arbieto", provinces.get(8)),
            new Locality("Aiquile", provinces.get(9)),
            new Locality("Lambaré", provinces.get(10)),
            new Locality("Luque", provinces.get(11)),
            new Locality("Aguas calientes", provinces.get(12)),
            new Locality("Andahuaylillas", provinces.get(13)),
            new Locality("Capinero", provinces.get(14)),
            new Locality("Soacha", provinces.get(15)),
            new Locality("Nuevo León", provinces.get(16)),
            new Locality("San nicolás de los Garza", provinces.get(17)),
            new Locality("Santa Rita", provinces.get(18)),
            new Locality("Concepción", provinces.get(19))
    );

    public void hydrate(Session session) {
        countries.forEach(session::save); // Automatically injects each Country
        provinces.forEach(session::save); // Automatically injects each Province
        localities.forEach(session::save); // Automatically injects each Locality
    }
}
