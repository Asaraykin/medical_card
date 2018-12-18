package repository.jpa;

import model.User;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public User save(User user) {
        if(user.isNew()){
            em.persist(user);
            return user;
        }
        else {
            return em.merge(user);
        }

    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

    @Override
    public List<User> getByRole(String role) {
        return em.createNamedQuery(User.GET_BY_ROLE, User.class)
                .setParameter("role", role)
                .getResultList();
    }

    @Override
    public User getByLogin(String login) {
        List<User> users = em.createNamedQuery(User.GET_BY_LOGIN, User.class)
                .setParameter(1, login)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }
}
