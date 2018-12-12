package repository.jpa;

import model.WorkPlace;
import org.springframework.stereotype.Repository;
import repository.WorkPlaceRepository;
import util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaWorkPlaceRepositoryImpl implements WorkPlaceRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public WorkPlace save(WorkPlace workPlace) {
        if(workPlace.isNew()){
            em.persist(workPlace);
            return workPlace;
        }
        else {
            return em.merge(workPlace);
        }
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return em.createNamedQuery(WorkPlace.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public WorkPlace get(int id) throws NotFoundException {
        return em.find(WorkPlace.class, id);
    }

    @Override
    public List<WorkPlace> getAll() {
        return em.createNamedQuery(WorkPlace.GET_ALL)
                .getResultList();
    }
}
