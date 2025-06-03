package client;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AuthData;
import model.UserData;
import network.ServerFacade;
import server.Server;


public class ServerFacadeTests {

    private static Server server;
    static ServerFacade serverFacade;

    private static final UserData EXISTING_USER = new UserData("existingUser","existingPass","existing@email.com");
    private static final UserData NEW_USER = new UserData("newUser","newPass","new@email.com");

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
        AuthData registerResponse = serverFacade.register(NEW_USER);
        System.out.println(registerResponse);
        Assertions.assertNotNull(registerResponse, "Register Response is null");
        Assertions.assertNotNull(registerResponse.username(), "Username is null");
        Assertions.assertNotNull(registerResponse.authToken(), "AuthToken is null");
        Assertions.assertThrows(Exception.class, () -> serverFacade.register(null));
    }

    @Test
    public void loginSuccess() throws Exception {
        AuthData registerResponse = serverFacade.login(NEW_USER);
        System.out.println(registerResponse);
        Assertions.assertNotNull(registerResponse, "Register Response is null");
        Assertions.assertNotNull(registerResponse.username(), "Username is null");
        Assertions.assertNotNull(registerResponse.authToken(), "AuthToken is null");
    }

    @Test
    public void loginFailure() {
        Assertions.assertThrows(Exception.class, () -> serverFacade.login(new UserData("newUser", null, null)));
    }
}
