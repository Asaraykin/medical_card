package service.patient;

import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Patient create(Patient patient) {
        Assert.notNull(patient, "patient should be not null");
        return repository.save(patient);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Patient get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(Patient patient) {
        Assert.notNull(patient, "patient should be not null");
        checkNotFoundWithId(repository.save(patient), patient.getId());
    }

    @Override
    public List<Patient> getAll() {
        return repository.getAll();
    }
}
