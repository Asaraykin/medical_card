package service.examination;

import model.Examination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Examination create(Examination examination, int referralId) {
        Assert.notNull(examination, "examination should not be null");
        return repository.save(examination, referralId);
    }

    @Override
    public void update(Examination examination, int referralId) throws NotFoundException {
        checkNotFoundWithId(repository.save(examination, referralId), examination.getId());
    }

    @Override
    public void delete(int id, int referralId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, referralId), id);
    }

    @Override
    public Examination get(int id, int referralId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, referralId), id);
    }

    @Override
    public List<Examination> getAll(int referralId) {
        return repository.getAll(referralId);
    }
}
