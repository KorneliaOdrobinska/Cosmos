package pl.odrobinska.cosmos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
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

    /*
    updateMission() - function which update lastMeasurementDate of correlated CelesiatlBody/Satellite.
    Additionally, if value of createSatellite argument is true - new satellite is created. It is possible only in mission which is correlated to CelestialBody.
        New Satellite has the same CelestialBodyCorrelation as the mission and actual date as discoveryDate
     */
    Mission updateMission(int missionID, boolean createSatellite, String satelliteName){
        Mission mission = findById(missionID).get();
        Date date = Date.valueOf(LocalDate.now()); // TODO optimize date creating
        Session session = null; // TODO optimize creation of session and transaction
        Transaction transaction = null;

        if(!mission.getFinished()) {
            if (mission.isCorrelatedToCelestialBody()) {
                CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
                CelestialBody celestialBody = celestialBodyRepository.findById(mission.getCelestialBodyCorrelation()).get();
                celestialBody.setLastMeasurementDate(date);
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.merge(celestialBody);
                if (createSatellite) {
                    transaction.commit();
                    session.close();
                    SatelliteRepository satelliteRepository = new SatelliteRepository();
                    Satellite newSatellite = new Satellite(satelliteName, true, mission.getCelestialBodyCorrelation(), date);
                    satelliteRepository.addSatellite(newSatellite);
                    session = HibernateUtil.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    session.merge(newSatellite);
                }
            } else {
                SatelliteRepository satelliteRepository = new SatelliteRepository(); // TODO optimize SatelliteRepository creating
                Satellite satellite = satelliteRepository.findById(mission.getSatelliteCorrelation()).get();
                satellite.setLastMeasurementDate(date);
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.merge(satellite);
            }
        }
        transaction.commit();
        session.close();
        return mission;
    }

    Mission finishMission(int missionID, boolean becomeSatellite){
        Mission mission = findById(missionID).get();
        mission.setFinished(true);
        if(becomeSatellite){
            SatelliteRepository satelliteRepository = new SatelliteRepository();
            Date date = Date.valueOf(LocalDate.now()); // TODO optimize date creating
            try { // TODO optimize try catch block
                mission.getCelestialBodyCorrelation().equals(null); // TODO !!! use mission.isCorrelatedToCelestialBody()
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
    // TODO add tests to updateMission() and finishMission()

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
