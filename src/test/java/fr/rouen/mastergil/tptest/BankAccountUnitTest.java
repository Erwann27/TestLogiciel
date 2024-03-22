package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BankAccountUnitTest {

    @Test
    public void shouldCreateAccountWithEuro(){
        // GIVEN
        BankAccount account = new BankAccount();
        // WHEN
        account.creerCompte();
        // THEN
        assertThat(account.solde).isNotNull();
        assertThat(account.solde.getDevise()).isEqualTo(Devise.EURO);
        assertThat(account.solde.getMontant()).isZero();
    }

    @Test
    public void shouldCreateAnyBankAccount(){
        // GIVEN
        BankAccount account = new BankAccount();
        Devise currency = Devise.LIVRE;
        int amount = 12;
        // WHEN
        account.creerCompte(amount, currency);
        // THEN
        assertThat(account.solde).isNotNull();
        assertThat(account.solde.getDevise()).isEqualTo(currency);
        assertThat(account.solde.getMontant()).isEqualTo(amount);
    }

    @Test
    public void shouldAddMoney(){
        // GIVEN
        BankAccount account = new BankAccount();
        account.creerCompte();
        int amount = 12;
        // WHEN
        account.deposerArgent(amount);
        // THEN
        assertThat(account.solde.getMontant()).isEqualTo(12);
    }

    @Test
    public void shouldRetrieveMoney(){
        // GIVEN
        BankAccount account = new BankAccount();
        account.creerCompte();
        int amount = 12;
        account.deposerArgent(amount);
        // WHEN
        account.retirerArgent(amount);
        // THEN
        assertThat(account.solde.getMontant()).isEqualTo(0);
    }

    @Test
    public void shouldDisplayAccount(){
        // GIVEN
        BankAccount account = new BankAccount();
        int amount = 100;
        Devise currency = Devise.DOLLAR;
        account.creerCompte(amount, currency);
        String result;
        // WHEN
        result = account.consulterSolde();
        // THEN
        assertThat(result).isEqualTo("Votre solde actuel est de " + amount + " " + currency.name());
    }

    @Test
    public void shouldBePositive(){
        // GIVEN
        BankAccount account = new BankAccount();
        int amount = 100;
        Devise currency = Devise.DOLLAR;
        account.creerCompte(amount, currency);
        boolean result;
        // WHEN
        result = account.isSoldePositif();
        // THEN
        assertThat(result).isTrue();
    }

    @Test
    public void shouldBeNegative(){
        // GIVEN
        BankAccount account = new BankAccount();
        int amount = -10000000;
        Devise currency = Devise.DOLLAR;
        account.creerCompte(amount, currency);
        boolean result;
        // WHEN
        result = account.isSoldePositif();
        // THEN
        assertThat(result).isFalse();
    }
}
