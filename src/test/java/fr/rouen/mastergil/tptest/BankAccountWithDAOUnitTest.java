package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

public class BankAccountWithDAOUnitTest {

    private JdbcDAO bankDAO;

    private Connection connection;

    private Money money;

    @BeforeEach
    public void init(){
        bankDAO = mock(JdbcDAO.class);
        connection = mock(Connection.class);
        try{
            when(connection.isReadOnly()).thenReturn(false);
        }
        catch(SQLException e){
            //
        }
        try{
            when(connection.isClosed()).thenReturn(false);
        }
        catch(SQLException e){
            //
        }
        when(bankDAO.setUpConnection()).thenReturn(connection);
        money = new Money();
        when(bankDAO.getSolde()).thenReturn(money);
    }

    @Test
    public void shouldThrowConnectionExceptionBecauseReadOnly() {
        // GIVEN
        try{
            when(connection.isReadOnly()).thenReturn(true);
        }
        catch(SQLException e){
            //
        }
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        // THEN
        assertThatThrownBy(
                // WHEN
                account::creerCompte
        ).isInstanceOf(ConnectException.class);
    }

    @Test
    public void shouldThrowConnectionExceptionBecauseClosed() {
        // GIVEN
        try{
            when(connection.isClosed()).thenReturn(true);
        }
        catch(SQLException e){
            //
        }
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        // THEN
        assertThatThrownBy(
                // WHEN
                account::creerCompte
        ).isInstanceOf(ConnectException.class);
    }

    @Test
    public void shouldCreateEuroBankAccount(){
        // GIVEN
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        // WHEN
        try {
            account.creerCompte();
        } catch (SQLException | ConnectException e) {
            throw new RuntimeException(e);
        }
        // THEN
        assertThat(account).isNotNull();
    }

    @Test
    public void shouldCreateAnyBankAccount(){
        // GIVEN
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        int amount = 12;
        Devise devise = Devise.DOLLAR;
        money.setDevise(devise);
        money.setMontant(amount);
        // WHEN
        try {
            account.creerCompte(amount, devise);
        } catch (SQLException | ConnectException e) {
            throw new RuntimeException(e);
        }
        // THEN
        assertThat(account).isNotNull();
        assertThat(account.bankDao.getSolde().getMontant()).isEqualTo(amount);
        assertThat(account.bankDao.getSolde().getDevise()).isEqualTo(devise);
    }

    @Test
    public void shouldReturnPositiveAccount() throws SQLException, ConnectException {
        // GIVEN
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        int amount = 12;
        Devise devise = Devise.DOLLAR;
        money.setDevise(devise);
        money.setMontant(amount);
        // WHEN
        boolean result = account.isSoldePositif();
        // THEN
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnNegativeAccount() throws SQLException, ConnectException {
        // GIVEN
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        int amount = -12;
        Devise devise = Devise.DOLLAR;
        money.setDevise(devise);
        money.setMontant(amount);
        // WHEN
        boolean result = account.isSoldePositif();
        // THEN
        assertThat(result).isFalse();
    }

    @Test
    public void shouldRetrieveMoney() throws SQLException, ConnectException {
        // GIVEN
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        int amount = 12;
        Devise devise = Devise.DOLLAR;
        money.setDevise(devise);
        money.setMontant(amount);
        int retrieved = 3;
        // WHEN
        account.retirerArgent(retrieved);
        // THEN
        assertThat(account.bankDao.getSolde().getMontant()).isEqualTo(amount - retrieved);
    }

    @Test
    public void shouldAddMoney() throws SQLException, ConnectException {
        // GIVEN
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        int amount = 12;
        Devise devise = Devise.DOLLAR;
        money.setDevise(devise);
        money.setMontant(amount);
        int added = 3;
        // WHEN
        account.deposerArgent(added);
        // THEN
        assertThat(account.bankDao.getSolde().getMontant()).isEqualTo(amount + added);
    }

    @Test
    public void shouldReturnCorrectlyFormattedAccount() throws SQLException, ConnectException {
        // GIVEN
        BankAccountWithDAO account = new BankAccountWithDAO();
        account.bankDao = this.bankDAO;
        int amount = 12;
        Devise devise = Devise.DOLLAR;
        money.setDevise(devise);
        money.setMontant(amount);
        int added = 3;
        // WHEN
        String result = account.consulterSolde();
        // THEN
        assertThat(result).matches("Votre solde actuel est de [0-9]+ [a-zA-Z]+");
    }
}
