package pl.odrobinska.cosmos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "SOLAR_SYSTEM")
public class CelestialBody {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer ID;
    private String name;
    private boolean isPlanet;
    private Long surfaceTemperature; // unit: Kelvin
    private Double radius; // unit: kilometers
    private Date lastMeasurementDate;


    public CelestialBody(String name, boolean isPlanet, Long surfaceTemperature, Double radius, LocalDate lastMesaurementDate) {
        this.name = name;
        this.isPlanet = isPlanet;
        this.surfaceTemperature = surfaceTemperature;
        this.radius = radius;
        this.lastMeasurementDate = Date.valueOf(lastMesaurementDate);
    }

    public CelestialBody() {
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

    public Long getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public Double getRadius() {
        return radius;
    }

    public Date getLastMeasurementDate() {
        return lastMeasurementDate;
    }

    public void setLastMeasurementDate(LocalDate lastMeasurementDate) {
        this.lastMeasurementDate = Date.valueOf(lastMeasurementDate);
    }

}
