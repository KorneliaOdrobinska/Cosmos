package pl.odrobinska.cosmos;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CelestialBody wenus = new CelestialBody("Wenus", true);
        wenus.addCelestialBody(wenus);
        HibernateUtil.close();
    }
}