package dataaccess.memory;

import dataaccess.UserDAO;
import model.UserData;

public class MemoryUserDAO implements UserDAO {

    @Override
    public UserData getUser(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addUser(UserData user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}