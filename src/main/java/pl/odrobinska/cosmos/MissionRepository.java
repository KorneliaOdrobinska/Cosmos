package pl.odrobinska.cosmos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MissionRepository {
    Logger logger = LoggerFactory.getLogger(MissionRepository.class);

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
        if(becomeSatellite){
            SatelliteRepository satelliteRepository = new SatelliteRepository();
            Date date = Date.valueOf(LocalDate.now()); // TODO optimize date creating
            try { // TODO optimize try catch block
                mission.getCelestialBodyCorrelation().equals(null);
                Satellite satellite = new Satellite(mission.getName(), false, mission.getCelestialBodyCorrelation(), date);
                satelliteRepository.addSatellite(satellite);
            } catch (NullPointerException e){
                logger.error("Cannot create satellite when celestialBodyCorrelation is null. " +
                        "The mission will be finished without creating a new satellite.");
            }
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
