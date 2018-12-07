package service.surgery;

import model.Surgery;
import util.exception.NotFoundException;

import java.util.List;

public interface SurgeryService {

     Surgery create(Surgery surgery, int patientId);

     void update(Surgery surgery, int patientId) throws NotFoundException;

     void delete(int id, int patientId) throws NotFoundException;

     Surgery get(int id, int patientId) throws NotFoundException;

     List<Surgery> getAll(int patientId);
}
