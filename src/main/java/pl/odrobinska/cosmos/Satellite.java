package pl.odrobinska.cosmos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "SATELLITES")
public class Satellite {
    private static final Logger LOGGER = LoggerFactory.getLogger(Satellite.class);
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer ID;
    private String name;
    private boolean isNatural;
    private Integer celestialBodyCorrelation;
    private Date discoveryDate;
    private Date lastMeasurementDate;


    public Satellite(String name, boolean isNatural, Integer celestialBodyCorrelation, LocalDate discoveryDate) throws IllegalArgumentException{
        this.name = name;
        this.isNatural = isNatural;
        this.discoveryDate = Date.valueOf(discoveryDate);
        this.lastMeasurementDate = Date.valueOf(discoveryDate);

        CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository(); // TODO czy w tym miejscu twprzyc obiekt repository?
        if (celestialBodyRepository.findById(celestialBodyCorrelation).isPresent()) {
            this.celestialBodyCorrelation = celestialBodyCorrelation;
        } else LOGGER.error("Wrong Celestial Body Correlation during Satellite creation!");
    }

    public Satellite() {
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean isNatural() {
        return isNatural;
    }

    public Integer getCelestialBodyCorrelation() {
        return celestialBodyCorrelation;
    }

    public Date getDiscoveryDate() {
        return discoveryDate;
    }

    public Date getLastMeasurementDate() {
        return lastMeasurementDate;
    }

    public void setLastMeasurementDate(LocalDate lastMeasurementDate) {
        this.lastMeasurementDate = Date.valueOf(lastMeasurementDate);
    }
}
