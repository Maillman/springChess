package server;

import com.google.gson.Gson;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import dataaccess.memory.MemoryUserDAO;
import model.AuthData;
import model.UserData;
import service.ClearService;
import service.GameService;
import service.UserService;
import spark.Request;
import spark.Response;

public class Handler {
    private final UserService userService;
    private final GameService gameService;
    private final ClearService clearService;
    private Gson serializer;

    public Handler() {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        this.userService = new UserService(userDAO, authDAO);
        this.gameService = new GameService(userDAO, authDAO, gameDAO);
        this.clearService = new ClearService(userDAO, authDAO, gameDAO);
        this.serializer = new Gson();
    }

    public Object register(Request req, Response res) {
        UserData user = serializer.fromJson(req.body(), UserData.class);
        AuthData auth = userService.register(user);
        return serializer.toJson(auth);
    }

    public Object login(Request req, Response res) {
        Object placeholder = "Remove this line";

        return placeholder;
    }

    public Object logout(Request req, Response res) {
        Object placeholder = "Remove this line";

        return placeholder;
    }

    public Object listGames(Request req, Response res) {
        Object placeholder = "Remove this line";

        return placeholder;
    }

    public Object createGame(Request req, Response res) {
        Object placeholder = "Remove this line";

        return placeholder;
    }

    public Object joinGame(Request req, Response res) {
        Object placeholder = "Remove this line";

        return placeholder;
    }

    public Object clear(Request req, Response res) {
        Object placeholder = "Remove this line";

        return placeholder;
    }
}