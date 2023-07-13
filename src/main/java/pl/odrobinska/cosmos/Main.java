package pl.odrobinska.cosmos;

import java.time.LocalDate;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        LocalDate localDate = LocalDate.of(2014, 9, 11);
        Date date = Date.valueOf(localDate);
        CelestialBody wenus = new CelestialBody("Wenus", true, 737L, 6051.0, date);
        CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
        celestialBodyRepository.addCelestialBody(wenus);
        System.out.println(celestialBodyRepository.findById(2).get().getName());
        System.out.println(celestialBodyRepository.findAll().get(0).getName());

        MissionRepository missionRepository = new MissionRepository();
        try {
            Mission magellan = new Mission("Magellan", false, 1, null, "1989-05-04", false);
            missionRepository.addMission(magellan);
        } catch (Exception e){
            System.out.println("Wrong data");
        }

        Satellite moon = new Satellite("Moon", true, 2, date);
        SatelliteRepository satelliteRepository = new SatelliteRepository();
        satelliteRepository.addSatellite(moon);
        missionRepository.updateMission(1, true, "TestSatellite");
        System.out.println(missionRepository.findById(2).get().getCelestialBodyCorrelation());
        missionRepository.finishMission(1, false);

        HibernateUtil.close();
    }
}