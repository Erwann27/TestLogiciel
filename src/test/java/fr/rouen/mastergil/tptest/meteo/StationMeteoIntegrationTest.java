package fr.rouen.mastergil.tptest.meteo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class StationMeteoIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintProvision() {
        // GIVEN
        String expected = new StationMeteo(new OpenWeatherMapProvider())
                .majPrevision("Paris,FR").toString().trim();
        // WHEN
        StationMeteo.main(null);
        // THEN
        assertThat(outContent.toString().trim()).isEqualTo(expected);
    }
}
