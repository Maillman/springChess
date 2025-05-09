package dataaccess.memory;

import java.util.HashMap;

import dataaccess.AuthDAO;
import model.AuthData;

public class MemoryAuthDAO implements AuthDAO {

    private final HashMap<String, AuthData> auths = new HashMap<>();

    @Override
    public void addAuth(AuthData authData) {
        auths.put(authData.authToken(), authData);
    }

    @Override
    public void clearAuths() {
        this.auths.clear();
    }
    
}