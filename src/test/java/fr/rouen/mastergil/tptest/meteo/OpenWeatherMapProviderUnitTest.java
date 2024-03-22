package fr.rouen.mastergil.tptest.meteo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

class OpenWeatherMapProviderUnitTest {

    @Test
    public void shouldCreateOpenWeatherMapProvider() {
        // WHEN
        OpenWeatherMapProvider mapProvider = new OpenWeatherMapProvider();
        // THEN
        assertThat(mapProvider).isNotNull();
    }

    @Test
    public void shouldThrowExceptionWhenGetForecastBecauseUndefinedCity() {
        // GIVEN
        OpenWeatherMapProvider mapProvider = new OpenWeatherMapProvider();
        String undefinedCity = "yuiveg_yubvgrtyuvbgugfyfgygfuirfy";
        // THEN
        assertThatThrownBy(()->
                //WHEN
                mapProvider.getForecastByCity(undefinedCity)
        );
    }

    @Test
    public void shouldReturnPrevisionListWithAValidCity() {
        // GIVEN
        OpenWeatherMapProvider mapProvider = new OpenWeatherMapProvider();
        String city = "Rouen,FR";
        // WHEN
        List<Prevision> lst = mapProvider.getForecastByCity(city);
        // THEN
        assertThat(lst).isNotNull().isNotEmpty();
    }

}