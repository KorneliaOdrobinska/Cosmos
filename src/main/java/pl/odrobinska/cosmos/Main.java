package pl.odrobinska.cosmos;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;

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
            Mission magellan = new Mission("Magellan", false, 2, null, "1989-05-04", false);
            missionRepository.addMission(magellan);
        } catch (Exception e){
            System.out.println("Wrong data"); // TODO 1 unclosed connections wht celestialBodyCorrelation is null
        }

        Satellite moon = new Satellite("Moon", true, 2, date);
        SatelliteRepository satelliteRepository = new SatelliteRepository();
        satelliteRepository.addSatellite(moon);
        System.out.println(missionRepository.findById(2).get().getCelestialBodyCorrelation());
        missionRepository.finishMission(14, true);

        HibernateUtil.close();
    }
}