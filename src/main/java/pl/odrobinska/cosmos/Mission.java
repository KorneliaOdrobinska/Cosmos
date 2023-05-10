package pl.odrobinska.cosmos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MISSIONS")
public class Mission {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer ID;
    private String name;
    private Boolean manned;
    private Integer celestialBodyCorrelation;
    private Integer satelliteCorrelation;
    private String missionStartdate; // TODO change to date type
    private Boolean isFinished;

    public Mission(String name, Boolean manned, Integer celestialBodyCorrelation, Integer satelliteCorrelation, String date, Boolean isFinished) {
        this.name = name;
        this.manned = manned;
        this.celestialBodyCorrelation = celestialBodyCorrelation;
        this.satelliteCorrelation = satelliteCorrelation;
        this.missionStartdate = date;
        this.isFinished = isFinished;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Boolean isManned() {
        return manned;
    }

    public Integer getCelestialBodyCorrelation() {
        return celestialBodyCorrelation;
    }

    public Integer getSatelliteCorrelation() {
        return satelliteCorrelation;
    }

    public String getMissionStartdate() {
        return missionStartdate;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
