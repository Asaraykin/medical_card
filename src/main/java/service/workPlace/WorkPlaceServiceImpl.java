package service.workPlace;

import model.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public WorkPlace create(WorkPlace workPlace) {
        Assert.notNull(workPlace, "work place should not be null");
        return repository.save(workPlace);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public WorkPlace get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void update(WorkPlace workPlace) {
        checkNotFoundWithId(repository.save(workPlace), workPlace.getId());
    }

    @Override
    public List<WorkPlace> getAll() {
        return repository.getAll();
    }
}
