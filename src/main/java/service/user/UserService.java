package service.user;

import model.User;
import util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    List<User> getByRole(String role);


     User getByLogin(String login) ;

}

