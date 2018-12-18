package service.user;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.UserRepository;
import util.exception.NotFoundException;

import java.util.List;
import java.util.Set;

import static util.ValidationUtil.checkNotFound;
import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {

        private final UserRepository repository;


    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public List<User> getByRole(String role) {
        return repository.getByRole(role);
    }

    @Override
    public User getByLogin(String login) {
        return checkNotFound(repository.getByLogin(login), "user not found");
    }
}
