package by.issoft.server;

import com.sun.net.httpserver.BasicAuthenticator;

public class Authorization extends BasicAuthenticator {
    private static final String USER = "raccoon";
    private static final String PASSWORD = "raccoon";

    public Authorization(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals(USER) && password.equals(PASSWORD);
    }
}
