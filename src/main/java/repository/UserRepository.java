package repository;

import model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    List<User> getAll();

    List<User> getByRole(String role);

    User getByLogin(String login);

    List<User> getByPage(int pageNumber, int pageSize);
    long getNumberOfUsers();

    List<User> search(String text, int pageNumber, int pageSize);
    int getNumberOfUsersFound();
}
