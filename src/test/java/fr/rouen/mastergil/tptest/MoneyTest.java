package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    public void shouldCreateEuroMoney() {
        // GIVEN
        // WHEN
        Money money = new Money();
        // THEN
        assertEquals(0, money.getMontant());
        assertEquals(Devise.EURO, money.getDevise());
    }

    @Test
    public void shouldCreateAnyMoney() {
        // GIVEN
        int amount = 14298;
        Devise currency = Devise.DOLLAR;
        // WHEN
        Money money = new Money(amount, currency);
        // THEN
        assertEquals(14298, money.getMontant());
        assertEquals(Devise.DOLLAR, money.getDevise());
    }

    @Test
    public void shouldNotCreateAnyMoney() {
        // GIVEN
        int currency = 14298;
        Devise devise = null;
        // THEN
        assertThrows(IllegalArgumentException.class, () ->
                // WHEN
                new Money(currency, devise));
    }

    @Test
    public void shouldBePositive() {
        // GIVEN
        Money money = new Money();
        // WHEN
        // THEN
        assertTrue(money.isPositif());
    }

    @Test
    public void shouldNotBePositive() {
        // GIVEN
        Money money = new Money();
        int amount = -3;
        // WHEN
        money.setMontant(amount);
        // THEN
        assertFalse(money.isPositif());
    }

    @Test
    public void shouldSetDevise() {
        // GIVEN
        Devise currency = Devise.PESO;
        Money money = new Money();
        // WHEN
        money.setDevise(currency);
        // THEN
        assertEquals(Devise.PESO, money.getDevise());
    }

    @Test
    public void shouldThrowExceptionWhenDeviseIsNull() {
        // GIVEN
        Devise currency = null;
        Money money = new Money();
        // THEN
        assertThrows(IllegalArgumentException.class, () ->
                // WHEN
                money.setDevise(currency));
    }
}