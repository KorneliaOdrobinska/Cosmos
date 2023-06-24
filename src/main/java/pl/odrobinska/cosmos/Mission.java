package pl.odrobinska.cosmos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

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
    private String missionStartDate; // TODO change to date type
    private Boolean isFinished;

    public Mission(String name, Boolean manned, @Nullable Integer celestialBodyCorrelation, @Nullable Integer satelliteCorrelation, String date, Boolean isFinished) throws IOException{
        this.name = name;
        this.manned = manned;
        this.celestialBodyCorrelation = celestialBodyCorrelation;
        this.satelliteCorrelation = satelliteCorrelation;
        this.missionStartDate = date;
        this.isFinished = isFinished;

        CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository(); // TODO czy w tym miejscu twprzyc obiekt repository?
        try {
            if (celestialBodyRepository.findById(celestialBodyCorrelation).isPresent()) {
                this.celestialBodyCorrelation = celestialBodyCorrelation;
            } else throw new IOException("Wrong Celestial Body Correlation!"); // TODO !!! change to logger.error
        } catch (IllegalArgumentException e) {
                SatelliteRepository satelliteRepository = new SatelliteRepository(); // TODO czy w tym miejscu twprzyc obiekt repository?
                if (satelliteRepository.findById(satelliteCorrelation).isPresent()) {
                    this.satelliteCorrelation = satelliteCorrelation;
                } else throw new IllegalArgumentException("Wrong Satellite Correlation!"); // TODO !!! change to logger.error
        }
    }

    public Mission() {
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

    public String getMissionStartDate() {
        return missionStartDate;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
