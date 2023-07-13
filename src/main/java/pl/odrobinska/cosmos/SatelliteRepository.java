package pl.odrobinska.cosmos;

import java.util.List;
import java.util.Optional;

public class SatelliteRepository {
    Satellite addSatellite(Satellite newSatellite){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newSatellite);
        transaction.commit();
        session.close();
        return newSatellite;
    }

    List<Satellite> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Satellite", Satellite.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    Optional<Satellite> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        Satellite result;
        try {
            result = session.get(Satellite.class, id);
            transaction.commit();
        } catch (IllegalArgumentException e){ // when id is null
            session.close();
            throw new IllegalArgumentException("Satellite by id not found");
        }
        session.close();
        return Optional.ofNullable(result);
    }
}
