package service;

import java.util.UUID;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;

public class UserService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;
    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public AuthData register(UserData user) throws DataAccessException {
        //Make sure username isn't taken
        UserData existingUser = this.userDAO.getUser(user.username());
        if(existingUser!=null){
            throw new DataAccessException("User already exists", 403);
        }
        //Register and authenticate them
        this.userDAO.addUser(user);
        AuthData authData = generateAuthData(user.username());
        this.authDAO.addAuth(authData);
        return authData;
    }

    private AuthData generateAuthData(String username) {
        String authToken = UUID.randomUUID().toString();
        return new AuthData(authToken, username);
    }
}