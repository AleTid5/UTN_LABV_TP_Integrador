package UTN.FRGP.TP_L5_GRUPO_1.Services.Repository;

import UTN.FRGP.TP_L5_GRUPO_1.Models.Country;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Locality;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Province;
import UTN.FRGP.TP_L5_GRUPO_1.Services.SessionService;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class LocationService {

    @Autowired
    private static Session session;

    @Autowired
    private static List<Country> countries;

    @Autowired
    private static List<Province> provinces;

    @Autowired
    private static List<Locality> localities;

    public static List<Country> getCountries() {
        try {
            session = SessionService.getSession();
            countries = session.createCriteria(Country.class).list();
        } finally {
            SessionService.commitSession(session);
        }
        
        return countries;
    }

    public static List<Province> getProvinces(Locality locality) {
        return getProvinces(locality.getProvince().getCountry().getId());
    }

    public static List<Province> getProvinces(Integer countryId) {
        try {
            session = SessionService.getSession();
            provinces = session.createCriteria(Province.class)
                    .add(Restrictions.eq("country.id", countryId))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return provinces;
    }

    public static List<Locality> getLocalities(Locality locality) {
        return getLocalities(locality.getProvince().getId());
    }

    public static List<Locality> getLocalities(Integer provinceId) {
        try {
            session = SessionService.getSession();
            localities = session.createCriteria(Locality.class)
                    .add(Restrictions.eq("province.id", provinceId))
                    .list();
        } finally {
            SessionService.commitSession(session);
        }

        return localities;
    }
}
