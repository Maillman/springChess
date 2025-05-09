package dataaccess;

import model.AuthData;

public interface AuthDAO {
    public AuthData getAuth(String authToken);
    public void addAuth(AuthData authData);
    public void deleteAuth(String authToken);
    public void clearAuths();
}