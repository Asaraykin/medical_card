package repository;

import model.Patient;
import util.exception.NotFoundException;

import java.util.List;

public interface PatientRepository {
    Patient save(Patient patient);

    boolean delete(int id) throws NotFoundException;

    Patient get(int id) throws NotFoundException;

    List<Patient> getAll();

}
