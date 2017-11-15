package ohtu.services;

import ohtu.data_access.UserDao;

public interface IAuthenticationService {

    boolean createUser(String s, String s1);

    boolean logIn(String s, String s1);

    void setUserDao(UserDao userdao);
}
