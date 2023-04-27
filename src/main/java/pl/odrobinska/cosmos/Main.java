package pl.odrobinska.cosmos;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CelestialBody wenus = new CelestialBody("Wenus", true, 737, 6051, "00");
        CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
        celestialBodyRepository.addCelestialBody(wenus);
        HibernateUtil.close();
    }
}