package pl.odrobinska.cosmos;

public class SatelliteRepository {
    Satellite addSatellite(Satellite newSatellite){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newSatellite);
        transaction.commit();
        session.close();
        return newSatellite;
    }
}
