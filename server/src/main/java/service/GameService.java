package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

public class GameService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;
    private final GameDAO gameDAO;
    public GameService(UserDAO userDAO, AuthDAO authDAO, GameDAO gameDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }

    public int createGame(String authToken, String gameName) throws DataAccessException {
        verifyUser(authToken);
        if(gameName==null){
            throw new DataAccessException("Error: Game name not provided", 400);
        }
        return this.gameDAO.createGame(gameName);
    }

    public void verifyUser(String authToken) throws DataAccessException {
        if(this.authDAO.getAuth(authToken)==null){
            throw new DataAccessException("Error: Invalid authtoken", 401);
        }
    }
}