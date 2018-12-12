package service.visit;

import model.Visit;
import util.exception.NotFoundException;

import java.util.List;

public interface VisitService {
    Visit create(Visit visit, int patientId);

    void update(Visit visit, int patientId) throws NotFoundException;

    void delete(int id, int patientId) throws NotFoundException;

    Visit get(int id, int patientId) throws NotFoundException;

    List<Visit> getAll(int patientId);
}
