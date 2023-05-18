package pl.odrobinska.cosmos;

import java.util.List;
import java.util.Optional;

public class MissionRepository {
    Mission addMission(Mission newMission){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newMission);
        transaction.commit();
        session.close();
        return newMission;
    }

    List<Mission> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Mission", Mission.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    Optional<Mission> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Mission.class, id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
