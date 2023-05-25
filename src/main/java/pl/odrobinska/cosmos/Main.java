package pl.odrobinska.cosmos;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CelestialBody wenus = new CelestialBody("Wenus", true, 737, 6051, "00");
        CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
        celestialBodyRepository.addCelestialBody(wenus);
        System.out.println(celestialBodyRepository.findById(2).get().getName());
        System.out.println(celestialBodyRepository.findAll().get(0).getName());

        MissionRepository missionRepository = new MissionRepository();
        try {
            Mission magellan = new Mission("Magellan", false, null, 333, "1989-05-04", true);
            missionRepository.addMission(magellan);
        } catch (Exception e){
            System.out.println("Wrong data");
        }


        Satellite moon = new Satellite("Moon", true, 20, "prehistoric");
        SatelliteRepository satelliteRepository = new SatelliteRepository();
        satelliteRepository.addSatellite(moon);
        HibernateUtil.close();
    }
}