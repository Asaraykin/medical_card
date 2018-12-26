package repository.jpa;

import model.Patient;
import model.Surgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.SurgeryRepository;
import util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaSurgeryRepositoryImpl implements SurgeryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Surgery save(Surgery surgery, int patientId) {
        if(surgery.isNew()){
            Patient ref = em.getReference(Patient.class, patientId);
            surgery.setPatient(ref);
            em.persist(surgery);
            return surgery;
        }
        else {
            Surgery surgeryFromDB = get(surgery.getId(), patientId);
            if (surgeryFromDB == null){
                return null;
            }
            else {
                surgery.setPatient(surgeryFromDB.getPatient());
                return em.merge(surgery);
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int patientId) throws NotFoundException {
        return em.createNamedQuery(Surgery.DELETE)
                .setParameter("id", id)
                .setParameter("patient_id", patientId)
                .executeUpdate() != 0;

    }

    @Override
    public Surgery get(int id, int patientId) throws NotFoundException {
        Surgery surgery = em.find(Surgery.class, id);
        if (surgery == null || surgery.getPatient().getId() != patientId){
            return null;
        }
        else {
            return surgery;
        }
    }

    @Override
    public List<Surgery> getAll(int patientId) {
        return em.createNamedQuery(Surgery.GET_ALL)
                .setParameter("patient_id", patientId)
                .getResultList();
    }
}
