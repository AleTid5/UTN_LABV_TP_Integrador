package UTN.FRGP.UTN_LABV_TP_Integrador.Seeders;

import UTN.FRGP.UTN_LABV_TP_Integrador.Services.SessionService;
import org.burningwave.core.assembler.ComponentContainer;
import org.burningwave.core.assembler.ComponentSupplier;
import org.burningwave.core.classes.CacheableSearchConfig;
import org.burningwave.core.classes.ClassCriteria;
import org.burningwave.core.classes.ClassHunter;
import org.burningwave.core.classes.SearchConfig;
import org.hibernate.Session;

import java.lang.reflect.InvocationTargetException;

public abstract class Seeder {
    public static void hydrate(Session session) {
        System.out.println("La base de datos se está populando...");
    }

    /**
     * This method hydrates the database using the Reflection Pattern provided by "burningwave" library.
     */
    public static void plant() {
        Session session = SessionService.getSession();
        ComponentSupplier componentSupplier = ComponentContainer.getInstance();

        CacheableSearchConfig searchConfig = SearchConfig.forPaths(componentSupplier.getPathHelper().getMainClassPaths())
                .by(ClassCriteria.create()
                        .byClasses((uploadedClasses, currentScannedClass) -> uploadedClasses
                                .get(Seeder.class)
                                .isAssignableFrom(currentScannedClass))
                .useClasses(Seeder.class));

        ClassHunter.SearchResult searchResult = componentSupplier.getClassHunter().loadInCache(searchConfig).find();

        searchResult.getClasses().forEach((Class<?> T) -> {
            try {
                T.getMethod("hydrate", Session.class).invoke(Seeder.class, session);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        SessionService.commitSession(session);
        SessionService.closeSessionFactory();
    }
}
