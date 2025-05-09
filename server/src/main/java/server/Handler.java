package server;

import com.google.gson.Gson;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;
import dataaccess.memory.MemoryAuthDAO;
import dataaccess.memory.MemoryGameDAO;
import dataaccess.memory.MemoryUserDAO;
import model.AuthData;
import model.GameData;
import model.JoinData;
import model.ListGamesData;
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
    private final Gson serializer;

    public Handler() {
        UserDAO userDAO = new MemoryUserDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        this.userService = new UserService(userDAO, authDAO);
        this.gameService = new GameService(userDAO, authDAO, gameDAO);
        this.clearService = new ClearService(userDAO, authDAO, gameDAO);
        this.serializer = new Gson();
    }

    public Object register(Request req, Response res) throws DataAccessException {
        UserData user = serializer.fromJson(req.body(), UserData.class);
        AuthData auth = userService.register(user);
        return serializer.toJson(auth);
    }

    public Object login(Request req, Response res) throws DataAccessException {
        UserData user = serializer.fromJson(req.body(), UserData.class);
        AuthData auth = userService.login(user);
        return serializer.toJson(auth);
    }

    public Object logout(Request req, Response res) throws DataAccessException {
        String authToken = req.headers("authorization");
        this.userService.logout(authToken);
        return "{}";
    }

    public Object listGames(Request req, Response res) throws DataAccessException {
        String authToken = req.headers("authorization");
        ListGamesData games = gameService.listGames(authToken);
        return serializer.toJson(games);
    }

    public Object createGame(Request req, Response res) throws DataAccessException {
        String authToken = req.headers("authorization");
        GameData game = serializer.fromJson(req.body(), GameData.class);
        int gameID = gameService.createGame(authToken, game.gameName());
        JoinData joinData = new JoinData(null, gameID);
        return serializer.toJson(joinData);
    }

    public Object joinGame(Request req, Response res) {
        String authToken = req.headers("authorization");
        JoinData join = serializer.fromJson(req.body(), JoinData.class);
        this.gameService.joinGame(authToken, join);
        return "{}";
    }

    public Object clear(Request req, Response res) {
        clearService.clear();
        return "{}";
    }

    public void handleException(DataAccessException ex, Request req, Response res) {
        res.status(ex.statusCode());
        res.body(ex.toJson());
    }
}