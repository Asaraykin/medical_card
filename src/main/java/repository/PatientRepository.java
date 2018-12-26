package repository;

import model.Patient;
import model.WorkPlace;
import util.exception.NotFoundException;

import java.util.List;

public interface PatientRepository {
    Patient save(Patient patient);

    boolean delete(int id) throws NotFoundException;

    Patient get(int id) throws NotFoundException;

    List<Patient> getAll();

void addWorkPlace (WorkPlace workPlace, int patientId);

    void removeWorkPlace (WorkPlace workPlace, int patientId);
}
