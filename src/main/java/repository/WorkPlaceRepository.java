package repository;

import model.WorkPlace;
import util.exception.NotFoundException;

import java.util.List;

public interface WorkPlaceRepository {
    WorkPlace save(WorkPlace workPlace);

    boolean delete(int id) throws NotFoundException;

    WorkPlace get(int id) throws NotFoundException;

    List<WorkPlace> getAll();
}
