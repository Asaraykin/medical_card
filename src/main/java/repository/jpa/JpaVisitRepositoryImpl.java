package repository.jpa;

import model.Patient;
import model.Visit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.VisitRepository;
import util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaVisitRepositoryImpl implements VisitRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Visit save(Visit visit, int patientId) {
        if (!visit.isNew() && get(visit.getId(), patientId) == null) {
            return null;
        }
        Patient patient = em.getReference(Patient.class, patientId);
        if (visit.isNew()) {
            visit.setPatient(patient);
            em.persist(visit);
            return visit;
        } else {
            visit.setPatient(patient);
            return em.merge(visit);
        }
    }

    @Override
    public boolean delete(int id, int patientId) throws NotFoundException {
        return em.createNamedQuery(Visit.DELETE)
                .setParameter("id", id)
                .setParameter("patient_id", patientId)
                .executeUpdate() != 0;
    }

    @Override
    public Visit get(int id, int patientId) throws NotFoundException {
        Visit visit = em.find(Visit.class, id);
        return visit != null && visit.getPatient().getId() == patientId ? visit : null;
    }

    @Override
    public List<Visit> getAll(int patientId) {
        return em.createNamedQuery(Visit.GET_ALL)
                .setParameter("patient_id", patientId)
                .getResultList();
    }
}
