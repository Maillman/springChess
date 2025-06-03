package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;

import model.AuthData;
import model.GameData;
import model.JoinData;
import model.ListGamesData;
import model.UserData;

public class ServerFacade {
    private final String serverUrl;
    private String authToken;
    public ServerFacade(String serverUrl) {
        this.serverUrl = serverUrl;
        this.authToken = null;
    }

    public AuthData register(UserData userData) throws Exception {
        return handleAuthorization("/user", userData);
    }

    public AuthData login(UserData userData) throws Exception {
        return handleAuthorization("/session", userData);
    }

    public void logout() throws Exception {
        makeRequest("DELETE", "/session", null, null);
        removeAuthToken();
    }

    public ListGamesData list() throws Exception {
        return makeRequest("GET", "/game", null, ListGamesData.class);
    }

    public JoinData create(GameData newGame) throws Exception {
        return makeRequest("POST", "/game", newGame, JoinData.class);
    }

    public void join(JoinData join) throws Exception {
        makeRequest("PUT", "/game", join, null);
    }

    public void clear() throws Exception {
        makeRequest("DELETE", "/db", null, null);
    }

    public void removeAuthToken() {
        this.authToken = null;
    }

    private AuthData handleAuthorization(String path, UserData userData) throws Exception {
        AuthData authData = makeRequest("POST", path, userData, AuthData.class);
        this.authToken = authData.authToken();
        return authData;
    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws Exception {
        URL url = (new URI(serverUrl + path)).toURL();
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod(method);
        http.setDoOutput(true);

        if(this.authToken!=null){
            //Write out a header
            http.addRequestProperty("authorization", this.authToken);
        }

        writeBody(request, http);
        http.connect();
        throwIfNotSuccessful(http);
        return readBody(http, responseClass);
    }


    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, Exception {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            try (InputStream respErr = http.getErrorStream()) {
                if (respErr != null) {
                    HashMap map = new Gson().fromJson(new InputStreamReader(respErr), HashMap.class);
                    String message = map.get("message").toString();
                    throw new Exception(message);
                }
            }

            throw new Exception("other failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }
}