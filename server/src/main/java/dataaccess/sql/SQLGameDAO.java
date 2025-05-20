package dataaccess.sql;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import com.google.gson.Gson;

import chess.ChessGame;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.GameDAO;
import model.GameData;
import model.ListGamesData;

public class SQLGameDAO implements GameDAO {

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM games WHERE game_id=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setInt(1, gameID);
                try (var rs = ps.executeQuery()) {
                    if(rs.next()) {
                        String whiteUsername = rs.getString("white_username");
                        String blackUsername = rs.getString("black_username");
                        String gameName = rs.getString("game_name");
                        String chessGameJSON = rs.getString("game");
                        ChessGame game = new Gson().fromJson(chessGameJSON, ChessGame.class);
                        GameData foundGame = new GameData(gameID, whiteUsername, blackUsername, gameName, game);
                        return foundGame;
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
            throw new DataAccessException("Error getting user: " + ex.getMessage(), 500);
        }
        return null;
    }

    @Override
    public ListGamesData getAllGames() throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int createGame(String gameName) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "INSERT INTO games (white_username, black_username, game_name, game) VALUES (?, ?, ?)";
            try (var ps = conn.prepareStatement(statement, RETURN_GENERATED_KEYS)) {
                ps.setString(1, null);
                ps.setString(2, null);
                ps.setString(3, gameName);
                String jsonGame = new Gson().toJson(new ChessGame());
                ps.setString(4, jsonGame);
                ps.executeUpdate();
                
                var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return -1;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            throw new DataAccessException("Error creating user: " + ex.getMessage(), 500);
        }
    }

    @Override
    public void updateGame(GameData updatedGame) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearGames() throws DataAccessException {
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