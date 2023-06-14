package pl.odrobinska.cosmos;

import java.sql.Date;
import java.time.LocalDate;
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

    // TODO editMission - to change mission parameters and lastMeasurementDate on CelestialBody or add new natural Satellite (and set discoveryDate)

    Mission finishMission(int missionID, boolean becomeSatellite){
        Mission mission = findById(missionID).get();
        mission.setFinished(true);
        if(becomeSatellite){ // TODO !!!  && mission.getSatelliteCorrelation() != null
            SatelliteRepository satelliteRepository = new SatelliteRepository();
            Date date = Date.valueOf(LocalDate.now()); // TODO optimize
            Satellite satellite = new Satellite(mission.getName(), false, mission.getCelestialBodyCorrelation(), date);
            satelliteRepository.addSatellite(satellite);
        }
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.merge(mission);
        transaction.commit();
        session.close();
        return mission;
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
