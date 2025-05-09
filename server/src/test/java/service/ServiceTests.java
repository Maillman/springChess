package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import dataaccess.memory.MemoryUserDAO;
import model.AuthData;
import model.UserData;

public class ServiceTests {
    private final UserService userService;
    private final GameService gameService;
    private final ClearService clearService;

    private static final UserData existingUser = new UserData("existingUser", "existingPass", "existingEmail");
    private static final UserData newUser = new UserData("newUser", "newPass", "newEmail");
    private AuthData authData;

    public ServiceTests() {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        this.userService = new UserService(userDAO, authDAO);
        this.gameService = new GameService(userDAO, authDAO, gameDAO);
        this.clearService = new ClearService(userDAO, authDAO, gameDAO);
    }

    @BeforeEach
    void clear() throws DataAccessException {
        clearService.clear();
        this.authData = userService.register(existingUser);
    }

    @Test
    void registerSuccess() {
        Assertions.assertDoesNotThrow(() -> userService.register(newUser));
    }

    @Test
    void registerFailure() {
        Assertions.assertThrows(DataAccessException.class, () -> userService.register(existingUser));
    }

    @Test
    void loginSuccess() {
        Assertions.assertDoesNotThrow(() -> userService.login(existingUser));
    }

    @Test
    void loginFailure() {
        Assertions.assertThrows(DataAccessException.class, () -> userService.login(newUser));
    }

    @Test
    void logoutSuccess() {
        Assertions.assertDoesNotThrow(() -> userService.logout(this.authData.authToken()));
    }

    @Test
    void logoutFailure() {
        Assertions.assertThrows(DataAccessException.class, () -> userService.logout("badAuthtoken"));
    }
}