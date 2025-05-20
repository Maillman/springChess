package dataaccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.sql.SQLAuthDAO;
import dataaccess.sql.SQLGameDAO;
import dataaccess.sql.SQLUserDAO;
import model.AuthData;
import model.UserData;

public class DataAccessObjectTests {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;
    private final GameDAO gameDAO;
    private final UserData existingUser = new UserData("existingUser", "existingPass", "existingEmail");
    private final AuthData authData = new AuthData("R4ND0M-UU1D-STRING", "existingUser");

    public DataAccessObjectTests() {
        this.userDAO = new SQLUserDAO();
        this.authDAO = new SQLAuthDAO();
        this.gameDAO = new SQLGameDAO();
    }

    @BeforeEach
    void clear() throws DataAccessException {
        userDAO.clearUsers();
        authDAO.clearAuths();
        gameDAO.clearGames();
        userDAO.addUser(existingUser);
        authDAO.addAuth(authData);
    }

    @Test
    void addUserSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> userDAO.addUser(new UserData("newUser", "newPass", "newEmail")));
    }

    @Test
    void addUserFailure() throws DataAccessException {
        Assertions.assertThrows(DataAccessException.class, () -> userDAO.addUser(null));
    }

    @Test
    void getUserSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> userDAO.getUser("existingUser"));
    }

    @Test
    void getUserFailure() throws DataAccessException {
        UserData foundUser = userDAO.getUser("newUser");
        Assertions.assertEquals(null, foundUser);
    }

    @Test
    void clearUserSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> userDAO.clearUsers());
    }

    @Test
    void addAuthSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> authDAO.addAuth(new AuthData("4N0TH3R-R4ND0M-UU1D-STRING", "existingUser")));
    }

    @Test
    void addAuthFailure() throws DataAccessException {
        Assertions.assertThrows(DataAccessException.class, () -> authDAO.addAuth(null));
    }

    @Test
    void getAuthSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> authDAO.getAuth("R4ND0M-UU1D-STRING"));
    }

    @Test
    void getAuthFailure() throws DataAccessException {
        AuthData authData = authDAO.getAuth("F4K3-UU1D-STRING");
        Assertions.assertEquals(null, authData);
    }

    @Test
    void clearAuthSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> authDAO.clearAuths());
    }

    @Test
    void createGameSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> gameDAO.createGame("newGame"));
    }

    @Test
    void createGameFailure() throws DataAccessException {
        Assertions.assertThrows(DataAccessException.class, () -> gameDAO.createGame(null));
    }
}