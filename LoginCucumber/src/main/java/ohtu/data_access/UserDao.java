
package ohtu.data_access;

import java.util.List;

import ohtu.domain.IUser;
import ohtu.domain.User;

public interface UserDao {
    List<IUser> listAll();
    IUser findByName(String name);
    void add(IUser user);
}
