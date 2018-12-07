package service.surgery;

import model.Surgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.SurgeryRepository;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class SurgeryServiceImpl implements SurgeryService {

    private SurgeryRepository repository;

    @Autowired
    public SurgeryServiceImpl(SurgeryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Surgery create(Surgery surgery, int patientId) {
        Assert.notNull(surgery, "surgery should not be null");
        return repository.save(surgery, patientId);
    }

    @Override
    public void update(Surgery surgery, int patientId) throws NotFoundException {
        Assert.notNull(surgery, "surgery should not be null");
        checkNotFoundWithId(repository.save(surgery, patientId), surgery.getId());
    }

    @Override
    public void delete(int id, int patientId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, patientId), id);
    }

    @Override
    public Surgery get(int id, int patientId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, patientId), id);
    }

    @Override
    public List<Surgery> getAll(int patientId) {
        return repository.getAll(patientId);
    }
}
