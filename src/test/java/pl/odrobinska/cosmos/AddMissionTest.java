package pl.odrobinska.cosmos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AddMissionTest {
    private static final Integer MISSION_CELESTIAL_BODY_CORRELATION = 1;
    private static final Integer MISSION_SATELLITE_CORRELATION = 1;
    private MissionRepository missionRepositoryTest;
    private Mission testMission;
    private static LocalDate missionStartDate = LocalDate.now();
    private static  String missionName;


    @BeforeEach
    void setUp() {
        missionRepositoryTest = new MissionRepository();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Add Mission with Celestial Body Correlation = 1 and Satellite Correlation = null")
    void test_addMissionWithCelestialBodyCorrelation() throws IOException {
            missionName = "TestMission, CBcor = 1, Scor = null";
            testMission = new Mission(missionName, false, MISSION_CELESTIAL_BODY_CORRELATION, null, missionStartDate, false);

        var result = missionRepositoryTest.addMission(testMission);
        assertEquals(result.getName(),missionName);
    }

    @Test
    @DisplayName("Add Mission with Celestial Body Correlation = null and Satellite Correlation = 1")
    void test_addMissionWithSatelliteBodyCorrelation() throws IOException {
        missionName = "TestMission, CBcor = null, Scor = 1";
        testMission = new Mission(missionName, false, null, MISSION_SATELLITE_CORRELATION, missionStartDate, false);

        var result = missionRepositoryTest.addMission(testMission);
        assertEquals(result.getName(),missionName);
    }

    @Test
    @DisplayName("Add Mission with Celestial Body Correlation = 1 and Satellite Correlation = 1")
    void test_addMissionWithBothCorrelations() throws IOException {
        missionName = "TestMission, CBcor = 1, Scor = 1";
        testMission = new Mission(missionName, false, MISSION_CELESTIAL_BODY_CORRELATION, MISSION_SATELLITE_CORRELATION, missionStartDate, false);

        var result = missionRepositoryTest.addMission(testMission);
        assertEquals(result.getName(),missionName);
    }

    @Test
    @DisplayName("Add Mission with Celestial Body Correlation = null and Satellite Correlation = null")
    void test_addMissionWithNullCorrelations() {
        missionName = "TestMission, CBcor = null, Scor = null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Mission(missionName, false, null, null, missionStartDate, false)
        );
        assertEquals("Satellite by id not found", exception.getMessage());
    }

    @Test
    @DisplayName("Add Mission with Celestial Body Correlation = 666 (not exist in database) and Satellite Correlation = null")
    void test_addMissionWithNotExistingCelestialBodyCorrelations() {
        missionName = "TestMission, CBcor = 666, Scor = null";

        IOException exception = assertThrows(
                IOException.class,
                () -> new Mission(missionName, false, 666, null, missionStartDate, false)
        );
        assertEquals("Wrong Celestial Body Correlation!", exception.getMessage());
    }

    @Test
    @DisplayName("Add Mission with Celestial Body Correlation = null and Satellite Correlation = 666 (not exist in database)")
    void test_addMissionWithNotExistingSatelliteCorrelations() {
        missionName = "TestMission, CBcor = null, Scor = 666";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Mission(missionName, false, null, 666, missionStartDate, false)
        );
        assertEquals("Wrong Satellite Correlation!", exception.getMessage());
    }
}