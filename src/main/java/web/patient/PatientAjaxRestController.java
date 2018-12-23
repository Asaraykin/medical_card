package web.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.patient.PatientService;
import service.surgery.SurgeryService;
import service.visit.VisitService;
import util.exception.NotFoundException;
import web.json.JacksonObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/rest/patient/")
public class PatientAjaxRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private PatientService patientService;
    private SurgeryService surgeryService;
    private VisitService visitService;

    public PatientAjaxRestController(PatientService patientService, SurgeryService surgeryService, VisitService visitService) {
        this.patientService = patientService;
        this.surgeryService = surgeryService;
        this.visitService = visitService;
    }

    public Patient create(Patient patient) {
        log.info("create {}", patient);
        checkNew(patient);
        return patientService.create(patient);
    }


    public void delete(int id) throws NotFoundException {
        log.info("delete {}", id);
        patientService.delete(id);
    }


    public Patient get(@RequestParam("id") int id) throws NotFoundException {
        log.info("get {}", id);
        return patientService.get(id);
    }


    @GetMapping(value = "/patientCard", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPatientCard(@RequestParam("id") int id) throws NotFoundException {
        log.info("get {}", id);
        ObjectMapper mapper = JacksonObjectMapper.getMapper();
        Map<Object, Object> map = new HashMap<>();
        map.put("patient", patientService.get(id));
        map.put("surgeries", surgeryService.getAll(id));
        map.put("visits", visitService.getAll(id));
        String JSONmapOfObjects = "";
        try {
            JSONmapOfObjects = mapper.writeValueAsString(map);
            System.out.println(JSONmapOfObjects);
        } catch (JsonProcessingException t) {
        }
        return JSONmapOfObjects;
    }

    public void update(Patient patient, int id) {
        log.info("update {} with id={}", patient, id);
        assureIdConsistent(patient, id);
        patientService.update(patient);
    }

    public List<Patient> getAll() {
        log.info("getAll");
        return patientService.getAll();
    }
}

