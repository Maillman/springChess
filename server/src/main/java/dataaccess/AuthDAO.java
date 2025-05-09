package dataaccess;

import model.AuthData;

public interface AuthDAO {
    public void addAuth(AuthData authData);
    public void clearAuths();
}