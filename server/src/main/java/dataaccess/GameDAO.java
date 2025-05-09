package dataaccess;

public interface GameDAO {
    public int createGame(String gameName);
    public void clearGames();
}