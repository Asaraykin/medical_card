package web.visit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Visit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.referral.ReferralService;
import service.visit.VisitService;
import util.exception.NotFoundException;
import web.SecurityUtil;
import web.json.JacksonObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/rest/visit/")
public class VisitAjaxRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private VisitService visitService;

    private ReferralService referralService;

    @Autowired
    public VisitAjaxRestController(VisitService visitService, ReferralService referralService) {
        this.visitService = visitService;
        this.referralService = referralService;
    }

    @GetMapping(value = "/{id}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable("id") int id,
                     @PathVariable("userId") int userId) throws NotFoundException {
        log.info("get visit {} for user {}", id, userId);
        ObjectMapper mapper = JacksonObjectMapper.getMapper();
        Map<Object, Object> map = new HashMap<>();
        map.put("visits", visitService.get(id, userId));
        map.put("referrals", referralService.getAll(id));
        String JSONmapOfObjects = "";
        try {
            JSONmapOfObjects = mapper.writeValueAsString(map);
            System.out.println(JSONmapOfObjects);
        } catch (JsonProcessingException t) {
            System.out.println(t.getMessage());
        }
        System.out.println(JSONmapOfObjects);
        return JSONmapOfObjects;
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Visit createOrUpdate(Visit visit, int patientId) throws NotFoundException {
        if (visit.isNew()) {
            checkNew(visit);
            log.info("create {} for user {}", visit, patientId);
            visitService.create(visit, patientId);
        } else {
            assureIdConsistent(visit, visit.getId());
            log.info("update {} for user {}", visit, patientId);
            visitService.update(visit, patientId);
        }
        return visitService.get(visit.getId(), patientId);
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


