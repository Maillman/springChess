package dataaccess.sql;

import dataaccess.DatabaseManager;
import dataaccess.UserDAO;
import model.UserData;

public class SQLUserDAO implements UserDAO {

    @Override
    public UserData getUser(String username) {
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM users WHERE username=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setString(1, username);
                try (var rs = ps.executeQuery()) {
                    if(rs.next()) {
                        String password = rs.getString("password");
                        String email = rs.getString("email");
                        UserData foundUser = new UserData(username, password, email);
                        return foundUser;
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void addUser(UserData user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}