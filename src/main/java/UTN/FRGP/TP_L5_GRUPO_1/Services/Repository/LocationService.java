package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Country;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Locality;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Province;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class LocationService {
    public static List<Country> getCountries() {
        List<Country> countries = null;
        Session session = null;

        try {
            session = SessionService.getSession();
            countries = session.createCriteria(Country.class).list();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }
        
        return countries;
    }

    public static List<Province> getProvinces(Locality locality) {
        return getProvinces(locality.getProvince().getCountry().getId());
    }

    public static List<Province> getProvinces(Integer countryId) {
        List<Province> provinces = null;
        Session session = null;

        try {
            session = SessionService.getSession();
            provinces = session.createCriteria(Province.class).add(Restrictions.eq("country.id", countryId)).list();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }

        return provinces;
    }

    public static List<Locality> getLocalities(Locality locality) {
        return getLocalities(locality.getProvince().getId());
    }

    public static List<Locality> getLocalities(Integer provinceId) {
        List<Locality> localities = null;
        Session session = null;

        try {
            session = SessionService.getSession();
            localities = session.createCriteria(Locality.class).add(Restrictions.eq("province.id", provinceId)).list();
            SessionService.commitSession(session);
        } catch (Exception e) {
            SessionService.rollbackSession(session);
        }

        return localities;
    }
}
