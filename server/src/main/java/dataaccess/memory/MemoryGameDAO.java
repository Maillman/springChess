package dataaccess.memory;

import java.util.HashMap;

import chess.ChessGame;
import dataaccess.GameDAO;
import model.GameData;

public class MemoryGameDAO implements GameDAO {
    
    private final HashMap<Integer, GameData> games = new HashMap<>();
    private int id = 0;

    @Override
    public int createGame(String gameName) {
        id++;
        GameData game = new GameData(id, null, null, gameName, new ChessGame());
        games.put(id, game);
        return id;
    }

    @Override
    public void clearGames() {
        this.games.clear();
    }    
}