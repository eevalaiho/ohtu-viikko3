package ohtu.data_access;

import ohtu.domain.IUser;
import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements UserDao {

    private List<IUser> users;

    public InMemoryUserDao() {
        users = new ArrayList<IUser>();
        users.add(new User("pekka", "akkep"));
    }        

    @Override
    public List<IUser> listAll() {
        return users;
    }

    @Override
    public IUser findByName(String name) {
        for (IUser user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void add(IUser user) {
        users.add(user);
    }

    public void setUsers(List<IUser> users) {
        this.users = users;
    }

    public List<IUser> getUsers() {
        return users;
    }
}
