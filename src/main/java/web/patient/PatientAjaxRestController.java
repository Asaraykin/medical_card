package web.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Patient;
import model.User;
import model.WorkPlace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.patient.PatientService;
import service.surgery.SurgeryService;
import service.user.UserService;
import service.visit.VisitService;
import service.workPlace.WorkPlaceService;
import util.exception.NotFoundException;
import web.json.JacksonObjectMapper;

import java.util.*;

import static util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping("/rest/patient/")
public class PatientAjaxRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private PatientService patientService;
    private SurgeryService surgeryService;
    private VisitService visitService;
    private WorkPlaceService workPlaceService;
    private UserService userService;

    @Autowired
    public PatientAjaxRestController(PatientService patientService, SurgeryService surgeryService, VisitService visitService, WorkPlaceService workPlaceService, UserService userService) {
        this.patientService = patientService;
        this.surgeryService = surgeryService;
        this.visitService = visitService;
        this.workPlaceService = workPlaceService;
        this.userService = userService;
    }

    public Patient get(@RequestParam("id") int id) throws NotFoundException {
        log.info("get {}", id);
        return patientService.get(id);
    }

    @GetMapping(value = "/patientWorkPlaces", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getWorkPlaces(@RequestParam("id") int id) throws NotFoundException {
        log.info("get {}", id);
        ObjectMapper mapper = JacksonObjectMapper.getMapper();
        Map<Object, Object> map = new HashMap<>();
        Set<WorkPlace> set = patientService.get(id).getWorkPlaces();
        map.put("work", set);
        String JSONmapOfObjects = "";
        try {
            JSONmapOfObjects = mapper.writeValueAsString(map);
            System.out.println(JSONmapOfObjects);
        } catch (JsonProcessingException t) {
        }
        return JSONmapOfObjects;
    }

    @PostMapping(value = "/workplaces/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<WorkPlace> getWorkPlaces(@RequestParam("patientId") int patientId,
                                        @RequestParam("previousWorkPlaceId") Integer previousWorkPlaceId,
                                        WorkPlace workPlace) throws NotFoundException {
        if (previousWorkPlaceId == null) {
            log.info("create {} for patient {}", workPlace, patientId);
            patientService.addWorkPlace(workPlace, patientId);

        } else {
            assureIdConsistent(workPlace, workPlace.getId());
            log.info("update {} for patient {}", workPlace, patientId);
            patientService.removeWorkPlace(workPlaceService.get(previousWorkPlaceId), patientId);
            patientService.addWorkPlace(workPlace, patientId);
        }
        return patientService.get(patientId).getWorkPlaces();
    }


    @GetMapping(value = {"/patientCard"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPatientCard(@RequestParam(value = "id", required = false) Integer id) throws NotFoundException {
        log.info("get {}", id);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = JacksonObjectMapper.getMapper();
        if (id != null) {
            map.put("patient", patientService.get(id));
            map.put("surgeries", surgeryService.getAll(id));
            map.put("visits", visitService.getAll(id));
            map.put("allWorks", workPlaceService.getAll());
        }
        map.put("users", userService.getAll());
        String JSONmapOfObjects = "";
        try {
            JSONmapOfObjects = mapper.writeValueAsString(map);
            System.out.println(JSONmapOfObjects);
        } catch (JsonProcessingException t) {
        }
        return JSONmapOfObjects;
    }

    @DeleteMapping(value = "/workPlace/delete/{id}/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<WorkPlace> deleteWorkPlace(@PathVariable("id") int id, @PathVariable("patientId") int patientId) {
        log.info("delete workPlace {} for patient {}", id, patientId);
        patientService.removeWorkPlace(workPlaceService.get(id), patientId);
        return patientService.get(patientId).getWorkPlaces();
    }

    @DeleteMapping(value = "/{patientId}/{notUsed}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> deletePatient(@PathVariable("patientId") int patientId, @PathVariable("notUsed") int notUsed) {
        log.info("delete patient {} ", patientId);
        patientService.delete(patientId);
        return patientService.getAll();
    }

    @PostMapping(value = {"/update/{id}", "/update"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient createOrUpdate(@PathVariable(value = "id", required = false) Integer id, Patient patient) {
        log.info("update {} with id={}", patient, id);
        User user = userService.get(id);
        if (user.getPatient() == null) {
            patient.setId(null);
            patient.setUser(user);
            user.setPatient(patient);
            System.out.println(patient.getId());
            patientService.create(patient);
            userService.update(user);


        } else {
            Set<WorkPlace> setToSave = new HashSet<>(patientService.get(id).getWorkPlaces());
            patient.setWorkPlaces(setToSave);
            patientService.update(patient);
        }
        return patientService.get(id);
    }

    public List<Patient> getAll() {
        log.info("getAll");
        return patientService.getAll();
    }
}

