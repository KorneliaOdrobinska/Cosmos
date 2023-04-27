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
    private Integer surfaceTemperature; // unit: Kelvin TODO change to Long type
    private Integer radius; // unit: kilometers TODO change to Double Type
    private String lastMeasurementDate; //TODO change to Date type


    public CelestialBody(String name, boolean isPlanet, Integer surfaceTemperature, Integer radius, String lastMesaurementDate) {
        this.name = name;
        this.isPlanet = isPlanet;
        this.surfaceTemperature = surfaceTemperature;
        this.radius = radius;
        this.lastMeasurementDate = lastMesaurementDate;
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

    public Integer getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public Integer getRadius() {
        return radius;
    }

    public String getLastMeasurementDate() {
        return lastMeasurementDate;
    }

    public void setLastMeasurementDate(String lastMeasurementDate) {
        this.lastMeasurementDate = lastMeasurementDate;
    }

}
