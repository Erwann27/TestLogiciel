package fr.rouen.mastergil.tptest.meteo;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
class StationMeteoUnitTest {

    private IWeatherProvider weatherProvider;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void init() {
        weatherProvider = mock(IWeatherProvider.class);
        when(weatherProvider.getForecastByCity("")).thenReturn(new ArrayList<Prevision>());
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    public void ShouldCreateWeatherStation() {
        // WHEN
        StationMeteo stationMeteo = new StationMeteo(weatherProvider);
        // THEN
        assertThat(stationMeteo).isNotNull();
    }

    @Test
    public void shouldGivePrevision() {
        // GIVEN
        StationMeteo stationMeteo = new StationMeteo(weatherProvider);
        String city = "Rouen";
        // WHEN
        List<Prevision> lst = stationMeteo.majPrevision(city);
        // THEN
        assertThat(lst).isNotNull();
    }

    @Test
    public void shouldThrowExceptionWhenCityIsNullOnMajPrevision() throws IllegalArgumentException{
        // GIVEN
        String city = null;
        StationMeteo stationMeteo = new StationMeteo(weatherProvider);
        // THEN
        assertThatThrownBy(() ->
                // WHEN
                stationMeteo.majPrevision(city)
        ).isInstanceOf(IllegalArgumentException.class);
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