package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyAssertJTest {
    @Test
    public void shouldCreateEuroMoney() {
        // GIVEN
        // WHEN
        Money money = new Money();
        // THEN
        assertThat(money.getMontant()).isEqualTo(0);
        assertThat(money.getDevise()).isEqualTo(Devise.EURO);
    }

    @Test
    public void shouldCreateAnyMoney() {
        // GIVEN
        int amount = 14298;
        Devise currency = Devise.DINAR;
        // WHEN
        Money money = new Money(amount, currency);
        // THEN
        assertThat(money.getMontant()).isEqualTo(amount);
        assertThat(money.getDevise()).isEqualTo(currency);
    }

    @Test
    public void shouldNotCreateAnyMoney() {
        // GIVEN
        int currency = 16;
        Devise devise = null;
        // THEN
        assertThatThrownBy(() ->
                // THEN
                new Money(currency, devise)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldBePositive() {
        // GIVEN
        Money money = new Money();
        // WHEN
        // THEN
        assertThat(money.isPositif()).isTrue();
    }

    @Test
    public void shouldNotBePositive() {
        // GIVEN
        Money money = new Money();
        int amount = -3;
        // WHEN
        money.setMontant(amount);
        // THEN
        assertThat(money.isPositif()).isFalse();
    }

    @Test
    public void shouldSetDevise() {
        // GIVEN
        Devise currency = Devise.LIVRE;
        Money money = new Money();
        // WHEN
        money.setDevise(currency);
        // THEN
        assertThat(money.getDevise()).isEqualTo(currency);
    }

    @Test
    public void shouldThrowExceptionWhenDeviseIsNull() {
        // GIVEN
        Devise currency = null;
        Money money = new Money();
        // THEN
        assertThatThrownBy(() ->
                // THEN
                money.setDevise(currency)).isInstanceOf(IllegalArgumentException.class);
    }
}