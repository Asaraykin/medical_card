package web.surgery;

import model.Surgery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.surgery.SurgeryService;
import util.exception.NotFoundException;
import web.SecurityUtil;

import java.util.List;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/rest/surgery/")
public class SurgeryAjaxRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private SurgeryService surgeryService;

    @Autowired
    public SurgeryAjaxRestController(SurgeryService surgeryService) {
        this.surgeryService = surgeryService;
    }


    @GetMapping(value = "/{id}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Surgery get(@PathVariable("id") int id,
                       @PathVariable("userId") int userId) throws NotFoundException {
        log.info("get surgery {} for user {}", id, userId);
        return surgeryService.get(id, userId);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Surgery createOrUpdate(Surgery surgery, int patientId) throws NotFoundException {
        if (surgery.isNew()) {
            checkNew(surgery);
            log.info("create {} for user {}", surgery, patientId);
            surgeryService.create(surgery, patientId);
        } else {
            assureIdConsistent(surgery, surgery.getId());
            log.info("update {} for user {}", surgery, patientId);
            surgeryService.update(surgery, patientId);
        }
       return surgeryService.get(surgery.getId(), patientId);
    }


    @DeleteMapping("/{id}/{userId}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int patientId) throws NotFoundException {
        log.info("delete surgery {} for user {}", id, patientId);
        surgeryService.delete(id, patientId);
    }

    public List<Surgery> getAll(int patientId) {
        int userId = SecurityUtil.authUserId();
        log.info("get surgery for user {}", userId);
        return surgeryService.getAll(patientId);
    }
}
