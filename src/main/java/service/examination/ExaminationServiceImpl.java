package service.examination;

import model.Examination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repository.ExaminationRepository;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationRepository repository;

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public Examination create(Examination examination, int referralId) {
        Assert.notNull(examination, "examination should not be null");
        return repository.save(examination, referralId);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void update(Examination examination, int referralId) throws NotFoundException {
        checkNotFoundWithId(repository.save(examination, referralId), examination.getId());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void delete(int id, int referralId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, referralId), id);
    }

    @Override
    public Examination get(int id, int referralId) throws NotFoundException {
        return repository.get(id, referralId);
    }

    @Override
    public List<Examination> getAll(int referralId) {
        return repository.getAll(referralId);
    }
}
