package dataaccess.sql;

import dataaccess.DatabaseManager;
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
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "TRUNCATE auths";
            try (var ps = conn.prepareStatement(statement)) {
                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}