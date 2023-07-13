package pl.odrobinska.cosmos;

import java.util.List;
import java.util.Optional;

public class CelestialBodyRepository {
    CelestialBody addCelestialBody(CelestialBody newCelestialBody){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newCelestialBody);
        transaction.commit();
        session.close();
        return newCelestialBody;
    }

    // TODO !!!updateCelestialBodyData(celestialID, Map of attributes to update)

    List<CelestialBody> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from CelestialBody", CelestialBody.class).list();
        transaction.commit();
        session.close();
        return result;
    }
    Optional<CelestialBody> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        CelestialBody result;
        try {
            result = session.get(CelestialBody.class, id);
            transaction.commit();
        } catch (IllegalArgumentException e){ // when id is null
            session.close();
            throw new IllegalArgumentException("Celestial Body by id not found");
        }
        session.close();
        return Optional.ofNullable(result);
    }
}