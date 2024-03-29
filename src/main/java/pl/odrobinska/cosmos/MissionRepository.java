package pl.odrobinska.cosmos;

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
        LocalDate date = LocalDate.now(); // TODO optimize date creating
        Session session = null; // TODO optimize creation of session and transaction
        Transaction transaction = null;

        if(!mission.getFinished()) { // TODO !!! when mission is finished error appears: transaction is null -> move session/transaction creation/commit
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
                    Satellite newSatellite = new Satellite(satelliteName, true, mission.getCelestialBodyCorrelation(), LocalDate.now());
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
        if (becomeSatellite){
            if (mission.isCorrelatedToCelestialBody()){
                SatelliteRepository satelliteRepository = new SatelliteRepository();
                Satellite satellite = new Satellite(mission.getName(), false, mission.getCelestialBodyCorrelation(), LocalDate.now());
                satelliteRepository.addSatellite(satellite);
            } else {
                logger.warn("Cannot create satellite when celestialBodyCorrelation is null. " +
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
