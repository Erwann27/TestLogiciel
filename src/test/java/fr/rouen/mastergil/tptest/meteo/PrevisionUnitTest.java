package fr.rouen.mastergil.tptest.meteo;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class PrevisionUnitTest {

    @Test
    public void shouldSetDate() {
        // GIVEN
        Prevision prevision = new Prevision();
        Date date = new Date();
        // WHEN
        prevision = prevision.setDate(date);
        // THEN
        assertThat(prevision.getDate()).isEqualTo(date);
    }

    @Test
    public void shouldSetTempMin() {
        // GIVEN
        Prevision prevision = new Prevision();
        double tempMin = 2;
        // WHEN
        prevision = prevision.setTempMin(tempMin);
        // THEN
        assertThat(prevision.getTempMin()).isEqualTo(tempMin);
    }

    @Test
    public void shouldSetTempMax() {
        // GIVEN
        Prevision prevision = new Prevision();
        double tempMax = 2;
        // WHEN
        prevision = prevision.setTempMax(tempMax);
        // THEN
        assertThat(prevision.getTempMax()).isEqualTo(tempMax);
    }

    @Test
    public void shouldSetTempDay() {
        // GIVEN
        Prevision prevision = new Prevision();
        double tempDay = 2;
        // WHEN
        prevision = prevision.setTempDay(tempDay);
        // THEN
        assertThat(prevision.getTempDay()).isEqualTo(tempDay);
    }

    @Test
    public void shouldSetTempNight() {
        // GIVEN
        Prevision prevision = new Prevision();
        double tempNight = 2;
        // WHEN
        prevision = prevision.setTempNight(tempNight);
        // THEN
        assertThat(prevision.getTempNight()).isEqualTo(tempNight);
    }

    @Test
    public void shouldSetDescription() {
        // GIVEN
        Prevision prevision = new Prevision();
        String desc = "test";
        // WHEN
        prevision = prevision.setDescription(desc);
        // THEN
        assertThat(prevision.getDescription()).isEqualTo(desc);
    }

    @Test
    public void shouldSetToString() {
        // GIVEN
        Prevision prevision = new Prevision();
        Date date = new Date();
        double tempMin = 0;
        double tempMax = 20;
        double tempDay = 15;
        double tempNight = 5;
        String desc = "TEST";
        prevision.setDate(date).setTempMin(tempMin).setTempMax(tempMax).setTempDay(tempDay).
                setTempNight(tempNight).setDescription(desc);
        // WHEN
        String result = prevision.toString();
        // THEN
        assertThat(result).isEqualTo("Prevision{" + "date=" + date + ", tempMin=" + tempMin +
                ", tempMax=" + tempMax + ", tempDay=" + tempDay + ", tempNight=" + tempNight +
                ", description='" + desc + "'" + '}');
    }
}