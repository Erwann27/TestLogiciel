package fr.rouen.mastergil.tptest.meteo;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
class StationMeteoUnitTest {

    private IWeatherProvider weatherProvider;

    @BeforeEach
    public void init() {
        weatherProvider = mock(IWeatherProvider.class);
        when(weatherProvider.getForecastByCity("")).thenReturn(new ArrayList<Prevision>());
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
}