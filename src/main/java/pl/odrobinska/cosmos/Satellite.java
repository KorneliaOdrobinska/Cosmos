package pl.odrobinska.cosmos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Entity
@Table(name = "SATELLITES")
public class Satellite {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer ID;
    private String name;
    private boolean isNatural;
    private Integer celestialBodyCorrelation;
    private Date discoveryDate; // TODO change to LocalDate type


    public Satellite(String name, boolean isNatural, Integer celestialBodyCorrelation, Date discoveryDate) throws IllegalArgumentException{
        this.name = name;
        this.isNatural = isNatural;
        this.discoveryDate = discoveryDate;

        CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository(); // TODO czy w tym miejscu twprzyc obiekt repository?
        if (celestialBodyRepository.findById(celestialBodyCorrelation).isPresent()) {
            this.celestialBodyCorrelation = celestialBodyCorrelation;
        } else throw new IllegalArgumentException("Wrong Celestial Body Correlation!");
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

}
