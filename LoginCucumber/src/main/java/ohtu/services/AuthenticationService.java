package ohtu.services;

import ohtu.domain.IUser;
import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;


import org.springframework.stereotype.Component;

@Component
public class AuthenticationService implements IAuthenticationService {

    private UserDao userDao;

    public AuthenticationService() { }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (IUser user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {

        if (username.length() < 3)
            return true;

        if (password.length() < 8)
            return true;

        int alpha = 0; int num_or_special = 0;
        for (char c: password.toCharArray()) {
            if (Character.isLetter(c)) {
                alpha++;
            }
            else {
                num_or_special++;
            }
        }
        return alpha == 0 || num_or_special == 0;
    }
}
