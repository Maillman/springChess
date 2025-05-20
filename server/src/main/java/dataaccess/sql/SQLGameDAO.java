package dataaccess.sql;

import dataaccess.DatabaseManager;
import dataaccess.GameDAO;
import model.GameData;
import model.ListGamesData;

public class SQLGameDAO implements GameDAO {

    @Override
    public GameData getGame(int gameID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListGamesData getAllGames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int createGame(String gameName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateGame(GameData updatedGame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearGames() {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "TRUNCATE games";
            try (var ps = conn.prepareStatement(statement)) {
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}