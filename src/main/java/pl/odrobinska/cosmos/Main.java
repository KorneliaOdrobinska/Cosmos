package pl.odrobinska.cosmos;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CelestialBody wenus = new CelestialBody("Wenus", true, 737, 6051, "00");
        CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
        celestialBodyRepository.addCelestialBody(wenus);
        System.out.println(celestialBodyRepository.findById(2).get().getName());
        System.out.println(celestialBodyRepository.findAll().get(0).getName());

        Mission magellan = new Mission("Magellan", false, 2, null, "1989-05-04", true);
        MissionRepository missionRepository = new MissionRepository();
        missionRepository.addMission(magellan);

        Satellite moon = new Satellite("Moon", true, 3, "prehistoric");
        SatelliteRepository satelliteRepository = new SatelliteRepository();
        satelliteRepository.addSatellite(moon);
        HibernateUtil.close();
    }
}