package pl.odrobinska.cosmos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SOLAR_SYSTEM")
public class CelestialBody {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer ID;
    private String name;
    private boolean isPlanet;

    public CelestialBody(String name, boolean isPlanet) {
        this.name = name;
        this.isPlanet = isPlanet;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean isPlanet() {
        return isPlanet;
    }

    CelestialBody addCelestialBody(CelestialBody newCelestialBody){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newCelestialBody);
        transaction.commit();
        session.close();
        return newCelestialBody;
    }
}
