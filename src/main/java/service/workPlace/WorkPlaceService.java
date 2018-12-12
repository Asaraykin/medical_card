package service.workPlace;

import model.WorkPlace;
import util.exception.NotFoundException;

import java.util.List;

public interface WorkPlaceService {
    WorkPlace create(WorkPlace workPlace);

    void delete(int id) throws NotFoundException;

    WorkPlace get(int id) throws NotFoundException;

    void update(WorkPlace workPlace);

    List<WorkPlace> getAll();
}
