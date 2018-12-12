package service.visit;

import model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.VisitRepository;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class VisitServiceImpl implements VisitService{

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Visit create(Visit visit, int patientId) {
        Assert.notNull(visit, "visit should not be null");
        return visitRepository.save(visit, patientId);
    }

    @Override
    public void update(Visit visit, int patientId) throws NotFoundException {
        checkNotFoundWithId(visitRepository.save(visit, patientId), visit.getId());
    }

    @Override
    public void delete(int id, int patientId) throws NotFoundException {
        checkNotFoundWithId(visitRepository.delete(id, patientId), id);
    }

    @Override
    public Visit get(int id, int patientId) throws NotFoundException {
        return checkNotFoundWithId(visitRepository.get(id, patientId), id);
    }

    @Override
    public List<Visit> getAll(int patientId) {
        return visitRepository.getAll(patientId);
    }
}
