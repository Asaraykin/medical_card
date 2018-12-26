package repository.jpa;

import model.Patient;

import model.WorkPlace;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.PatientRepository;
import util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaPatientRepositoryImpl implements PatientRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Patient save(Patient patient) {
        if(patient.isNew()){
            em.persist(patient);
            return patient;
        }
        else {
            return em.merge(patient);
        }
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return em.createNamedQuery(Patient.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public void removeWorkPlace(WorkPlace workPlace, int patientId) {
        Patient patient = em.find(Patient.class, patientId);
        patient.removeWorkPlace(workPlace);
    }

   @Override
    public void addWorkPlace(WorkPlace workPlace, int patientId){
        Patient patient = em.find(Patient.class, patientId);
        patient.addWorkPlace(workPlace);
        em.merge(patient);
    }
    @Override
    public Patient get(int id) throws NotFoundException {
        return em.find(Patient.class, id);
    }

    @Override
    public List<Patient> getAll() {
        return em.createNamedQuery(Patient.GET_ALL, Patient.class).getResultList();
    }
}
