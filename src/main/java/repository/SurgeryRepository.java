package repository;

import model.Surgery;
import util.exception.NotFoundException;

import java.util.List;

public interface SurgeryRepository {
    Surgery save(Surgery surgery, int patientId);

    // false if surgery do not belong to userId
    boolean delete(int id, int patientId) throws NotFoundException;

    Surgery get(int id, int patientId) throws NotFoundException;

    // ORDERED dateTime desc
    List<Surgery> getAll(int patientId);
}
