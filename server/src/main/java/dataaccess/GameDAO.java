package dataaccess;

import model.ListGamesData;

public interface GameDAO {
    public ListGamesData getAllGames();
    public int createGame(String gameName);
    public void clearGames();
}