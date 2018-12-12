package repository;

import model.Visit;
import util.exception.NotFoundException;

import java.util.List;

public interface VisitRepository {
    Visit save(Visit visit, int patientId);

    // false if visit do not belong to userId
    boolean delete(int id, int patientId) throws NotFoundException;

    Visit get(int id, int patientId) throws NotFoundException;

    // ORDERED dateTime desc
    List<Visit> getAll(int patientId);
}
