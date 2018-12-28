package service.patient;

import model.Patient;
import model.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repository.PatientRepository;
import util.exception.NotFoundException;
import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class PatientServiceImpl implements PatientService {


    private PatientRepository repository;

    @Autowired
    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public Patient create(Patient patient) {
        Assert.notNull(patient, "patient should be not null");
        return repository.save(patient);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @Transactional
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Patient get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void update(Patient patient) {
        Assert.notNull(patient, "patient should be not null");
        checkNotFoundWithId(repository.save(patient), patient.getId());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void removeWorkPlace(WorkPlace workPlace, int patientId) {
        repository.removeWorkPlace(workPlace, patientId);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void addWorkPlace(WorkPlace workPlace, int patientId) {
        repository.addWorkPlace(workPlace, patientId);
    }

    @Override
    public List<Patient> getAll() {
        return repository.getAll();
    }
}
