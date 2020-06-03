package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Country;
import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Locality;
import UTN.FRGP.UTN_LABV_TP_Integrador.Models.Province;
import org.hibernate.Session;

import java.util.ArrayList;

public class LocationSeeder extends Seeder {
    public static final ArrayList<Country> countries = new ArrayList<Country>(){
        {
            add(new Country("Argentina"));
            add(new Country("Brasil"));
            add(new Country("Chile"));
        }
    };

    public static final ArrayList<Province> provinces = new ArrayList<Province>(){
        {
            add(new Province("Buenos Aires", countries.get(0)));
            add(new Province("San Pablo", countries.get(1)));
            add(new Province("Santiago", countries.get(2)));
        }
    };

    public static final ArrayList<Locality> localities = new ArrayList<Locality>(){
        {
            add(new Locality("Vicente Lopez", provinces.get(0)));
            add(new Locality("Rio de Janeiro", provinces.get(1)));
            add(new Locality("Viña del mar", provinces.get(2)));
        }
    };

    public static void hydrate(Session session) {
        countries.forEach(session::save); // Automatically injects each Country
        provinces.forEach(session::save); // Automatically injects each Province
        localities.forEach(session::save); // Automatically injects each Locality
    }
}
