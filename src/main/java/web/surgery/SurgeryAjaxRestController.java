package web.surgery;

import model.Surgery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
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

    private SurgeryService service;

    @Autowired
    public SurgeryAjaxRestController(SurgeryService service) {
        this.service = service;
    }


    @GetMapping(value = "/{id}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Surgery get(@PathVariable("id") int id,
                       @PathVariable("userId") int userId) throws NotFoundException {
        log.info("get surgery {} for user {}", id, userId);
        return service.get(id, userId);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Surgery createOrUpdate(Surgery surgery, int userId, Model model) throws NotFoundException {
        if (surgery.isNew()) {
            checkNew(surgery);
            log.info("create {} for user {}", surgery, userId);
            service.create(surgery, userId);
        } else {
            assureIdConsistent(surgery, surgery.getId());
            log.info("update {} for user {}", surgery, userId);
            service.update(surgery, userId);
        }
       return service.get(surgery.getId(), userId);
    }


    @DeleteMapping("/{id}/{userId}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int patientId) throws NotFoundException {
        log.info("delete surgery {} for user {}", id, patientId);
        service.delete(id, patientId);
    }

    public List<Surgery> getAll(int patientId) {
        int userId = SecurityUtil.authUserId();
        log.info("get surgery for user {}", userId);
        return service.getAll(patientId);
    }
}
