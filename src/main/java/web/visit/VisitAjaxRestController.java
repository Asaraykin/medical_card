package web.visit;

import model.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.visit.VisitService;
import util.exception.NotFoundException;
import web.SecurityUtil;

import java.util.List;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/rest/surgery/")
public class VisitAjaxRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private VisitService visitService;

    @Autowired
    public VisitAjaxRestController(VisitService visitService) {
        this.visitService = visitService;
    }


    @GetMapping(value = "/{id}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Visit get(@PathVariable("id") int id,
                     @PathVariable("userId") int userId) throws NotFoundException {
        log.info("get visit {} for user {}", id, userId);
        return visitService.get(id, userId);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Visit createOrUpdate(Visit visit, int userId) throws NotFoundException {
        if (visit.isNew()) {
            checkNew(visit);
            log.info("create {} for user {}", visit, userId);
            visitService.create(visit, userId);
        } else {
            assureIdConsistent(visit, visit.getId());
            log.info("update {} for user {}", visit, userId);
            visitService.update(visit, userId);
        }
        return visitService.get(visit.getId(), userId);
    }


    @DeleteMapping("/{id}/{userId}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int patientId) throws NotFoundException {
        log.info("delete visit {} for user {}", id, patientId);
        visitService.delete(id, patientId);
    }

    public List<Visit> getAll(int patientId) {
        int userId = SecurityUtil.authUserId();
        log.info("get visit for user {}", userId);
        return visitService.getAll(patientId);
    }
}


