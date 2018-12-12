package service.workPlace;

import model.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.WorkPlaceRepository;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class WorkPlaceServiceImpl implements WorkPlaceService {

    @Autowired
    private WorkPlaceRepository repository;

    @Override
    public WorkPlace create(WorkPlace workPlace) {
        Assert.notNull(workPlace, "work place should not be null");
        return repository.save(workPlace);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public WorkPlace get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(WorkPlace workPlace) {
        checkNotFoundWithId(repository.save(workPlace), workPlace.getId());
    }

    @Override
    public List<WorkPlace> getAll() {
        return repository.getAll();
    }
}
