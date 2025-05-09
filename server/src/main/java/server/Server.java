package server;

import dataaccess.DataAccessException;
import spark.Spark;

public class Server {
    private Handler handler;

    public Server() {
        this.handler = new Handler();
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", (req, res) -> handler.register(req, res));
        Spark.post("/session", (req, res) -> handler.login(req, res));
        Spark.delete("/session", (req, res) -> handler.logout(req, res));
        Spark.get("/game", (req, res) -> handler.listGames(req, res));
        Spark.post("/game", (req, res) -> handler.createGame(req, res));
        Spark.put("/game", (req, res) -> handler.joinGame(req, res));
        Spark.delete("/db", (req, res) -> handler.clear(req, res));
        Spark.exception(DataAccessException.class, (ex, req, res) -> handler.handleException(ex, req, res));

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
