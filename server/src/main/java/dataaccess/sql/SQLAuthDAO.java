package dataaccess.sql;

import dataaccess.AuthDAO;
import model.AuthData;

public class SQLAuthDAO implements AuthDAO {

    @Override
    public AuthData getAuth(String authToken) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addAuth(AuthData authData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAuth(String authToken) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearAuths() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}