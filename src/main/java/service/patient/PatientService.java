package service.patient;

import model.Patient;
import util.exception.NotFoundException;

import java.util.List;

public interface PatientService {

    Patient create(Patient patient);

    void delete(int id) throws NotFoundException;

    Patient get(int id) throws NotFoundException;

    void update(Patient patient);

    List<Patient> getAll();


}


