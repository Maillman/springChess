package client;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.AuthData;
import model.UserData;
import network.ServerFacade;
import server.Server;


public class ServerFacadeTests {

    private static Server server;
    static ServerFacade serverFacade;
    private static final UserData SAMPLE_USER = new UserData("newUser","newPass","new@email.com");

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        String url = "http://localhost:" + port;
        serverFacade = new ServerFacade(url);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void registerSuccess() throws Exception {
        AuthData registerResponse = serverFacade.register(SAMPLE_USER);
        System.out.println(registerResponse);
        Assertions.assertNotNull(registerResponse, "Register Response is null");
        Assertions.assertNotNull(registerResponse.username(), "Username is null");
        Assertions.assertNotNull(registerResponse.authToken(), "AuthToken is null");
    }

}
