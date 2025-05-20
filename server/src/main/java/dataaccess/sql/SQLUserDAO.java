package dataaccess.sql;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.UserDAO;
import model.UserData;

public class SQLUserDAO implements UserDAO {

    public SQLUserDAO() throws DataAccessException {
        configureDatabase();
    }

    @Override
    public UserData getUser(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addUser(UserData user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void configureDatabase() throws DataAccessException {
        DatabaseManager.createDatabase();
    }
    
}