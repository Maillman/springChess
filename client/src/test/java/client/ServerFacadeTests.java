package client;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AuthData;
import model.GameData;
import model.JoinData;
import model.ListGamesData;
import model.UserData;
import network.ServerFacade;
import server.Server;


public class ServerFacadeTests {

    private static Server server;
    static ServerFacade serverFacade;

    private static final UserData EXISTING_USER = new UserData("existingUser","existingPass","existing@email.com");
    private static final UserData NEW_USER = new UserData("newUser","newPass","new@email.com");
    private static final GameData NEW_GAME = new GameData(-1, null, null, "newGame", null);

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        String url = "http://localhost:" + port;
        serverFacade = new ServerFacade(url);
    }

    @BeforeEach
    public void setup() {
        Assertions.assertDoesNotThrow(() -> serverFacade.clear());
        Assertions.assertDoesNotThrow(() -> serverFacade.register(EXISTING_USER));
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void registerSuccess() throws Exception {
        AuthData registerResponse = serverFacade.register(NEW_USER);
        System.out.println(registerResponse);
        Assertions.assertNotNull(registerResponse, "Register Response is null");
        Assertions.assertNotNull(registerResponse.username(), "Username is null");
        Assertions.assertNotNull(registerResponse.authToken(), "AuthToken is null");
    }

    @Test
    public void registerFailure() {
        Assertions.assertThrows(Exception.class, () -> serverFacade.register(null));
    }

    @Test
    public void registerTwice() throws Exception {
        Assertions.assertThrows(Exception.class, () -> serverFacade.register(null));
    }

    @Test
    public void loginSuccess() throws Exception {
        AuthData registerResponse = serverFacade.login(EXISTING_USER);
        System.out.println(registerResponse);
        Assertions.assertNotNull(registerResponse, "Register Response is null");
        Assertions.assertNotNull(registerResponse.username(), "Username is null");
        Assertions.assertNotNull(registerResponse.authToken(), "AuthToken is null");
    }

    @Test
    public void loginFailure() {
        Assertions.assertThrows(Exception.class, () -> serverFacade.login(new UserData("newUser", null, null)));
    }

    @Test
    public void logoutSuccess() {
        Assertions.assertDoesNotThrow(() -> serverFacade.logout());
    }

    @Test
    public void logoutFailure() {
        serverFacade.removeAuthToken();
        Assertions.assertThrows(Exception.class, () -> serverFacade.logout());
    }

    @Test
    public void listGamesSuccess() {
        Assertions.assertDoesNotThrow(() -> serverFacade.list());
    }

    @Test
    public void listGamesFailure() {
        serverFacade.removeAuthToken();
        Assertions.assertThrows(Exception.class, () -> serverFacade.list());
    }

    @Test
    public void createGameSuccess() {
        Assertions.assertDoesNotThrow(() -> serverFacade.create(NEW_GAME));
    }

    @Test
    public void createGameFailure() {
        Assertions.assertThrows(Exception.class, () -> serverFacade.create(new GameData(0, null, null, null, null)));
    }

    @Test
    public void createAndListGameSuccess() throws Exception {
        Assertions.assertDoesNotThrow(() -> serverFacade.create(NEW_GAME));
        ListGamesData allGames = serverFacade.list();
        Assertions.assertNotEquals(0, allGames.games().size());
    }

    @Test
    public void joinGameSuccess() throws Exception {
        JoinData createdGame = serverFacade.create(NEW_GAME);
        JoinData joiningGame = new JoinData("WHITE", createdGame.gameID());
        Assertions.assertDoesNotThrow(() -> serverFacade.join(joiningGame));
    }

    @Test
    public void joinGameFailure() throws Exception {
        JoinData createdGame = serverFacade.create(NEW_GAME);
        JoinData joiningGameBadColor = new JoinData("GREEN", createdGame.gameID());
        JoinData joiningGameBadId = new JoinData("WHITE", -1);
        JoinData joiningGame = new JoinData("WHITE", createdGame.gameID());
        Assertions.assertThrows(Exception.class, () -> serverFacade.join(joiningGameBadColor));
        Assertions.assertThrows(Exception.class, () -> serverFacade.join(joiningGameBadId));
        serverFacade.removeAuthToken();
        Assertions.assertThrows(Exception.class, () -> serverFacade.join(joiningGame));
    }
}
