package dataaccess;

import model.GameData;
import model.ListGamesData;

public interface GameDAO {
    public GameData getGame(int gameID);
    public ListGamesData getAllGames();
    public int createGame(String gameName);
    public void updateGame(GameData updatedGame);
    public void clearGames();
}