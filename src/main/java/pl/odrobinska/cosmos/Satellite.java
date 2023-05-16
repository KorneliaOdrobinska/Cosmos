package pl.odrobinska.cosmos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

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
    private String discoveryDate; //TODO change to Date type


    public Satellite(String name, boolean isNatural, Integer celestialBodyCorrelation, String discoveryDate) {
        this.name = name;
        this.isNatural = isNatural;
        this.celestialBodyCorrelation = celestialBodyCorrelation;
        this.discoveryDate = discoveryDate;
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

    public String getDiscoveryDate() {
        return discoveryDate;
    }

}
