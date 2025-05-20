package dataaccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.sql.SQLAuthDAO;
import dataaccess.sql.SQLGameDAO;
import dataaccess.sql.SQLUserDAO;
import model.UserData;

public class DataAccessObjectTests {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;
    private final GameDAO gameDAO;
    private final UserData existingUser = new UserData("existingUser", "existingPass", "existingEmail");

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
    }

    @Test
    void addUserSuccess() throws DataAccessException {
        Assertions.assertDoesNotThrow(() -> userDAO.addUser(new UserData("newUser", "newPass", "newEmail")));
    }

    @Test
    void addUserFailure() throws DataAccessException {
        Assertions.assertThrows(DataAccessException.class, () -> userDAO.addUser(null));
    }
}