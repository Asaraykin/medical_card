package web.patient;

import model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.patient.PatientService;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@Controller
public class PatientRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private PatientService service;

    @Autowired
    public PatientRestController(PatientService service) {
        this.service = service;
    }

    public Patient create(Patient patient) {
        log.info("create {}", patient);
        checkNew(patient);
        return service.create(patient);
    }


    public void delete(int id) throws NotFoundException {
        log.info("delete {}", id);
        service.delete(id);
    }


    public Patient get(int id) throws NotFoundException {
        log.info("get {}", id);
        return service.get(id);
    }


    public void update(Patient patient, int id) {
        log.info("update {} with id={}", patient, id);
        assureIdConsistent(patient, id);
        service.update(patient);
    }

    public List<Patient> getAll() {
        log.info("getAll");
        return service.getAll();
    }
}

