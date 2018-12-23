package service.visit;

import model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repository.VisitRepository;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class VisitServiceImpl implements VisitService{

    @Autowired
    private VisitRepository visitRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    @Override
    @Transactional
    public Visit create(Visit visit, int patientId) {
        Assert.notNull(visit, "visit should not be null");
        return visitRepository.save(visit, patientId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    @Override
    @Transactional
    public void update(Visit visit, int patientId) throws NotFoundException {
        checkNotFoundWithId(visitRepository.save(visit, patientId), visit.getId());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    @Override
    @Transactional
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
