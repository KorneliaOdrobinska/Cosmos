package pl.odrobinska.cosmos;

public class CelestialBodyRepository {
    CelestialBody addCelestialBody(CelestialBody newCelestialBody){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newCelestialBody);
        transaction.commit();
        session.close();
        return newCelestialBody;
    }
}
