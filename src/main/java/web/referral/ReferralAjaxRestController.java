package web.referral;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Referral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.referral.ReferralService;
import service.visit.VisitService;
import util.exception.NotFoundException;
import web.json.JacksonObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/rest/referral/")
public class ReferralAjaxRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private VisitService visitService;

    private ReferralService referralService;

    @Autowired
    public ReferralAjaxRestController(VisitService visitService, ReferralService referralService) {
        this.visitService = visitService;
        this.referralService = referralService;
    }

    @GetMapping(value = "/{id}/{visitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable("id") int id,
                     @PathVariable("visitId") int visitId) throws NotFoundException {
        log.info("get referral {} for visit {}", id, visitId);
        ObjectMapper mapper = JacksonObjectMapper.getMapper();
        Map<Object, Object> map = new HashMap<>();
       // map.put("visits", visitService.get(id, visitId));
        map.put("referrals", referralService.getAll(id));
        String JSONmapOfObjects = "";
        try {
            JSONmapOfObjects = mapper.writeValueAsString(map);
            System.out.println(JSONmapOfObjects);
        } catch (JsonProcessingException t) {
        }
        System.out.println(JSONmapOfObjects);
        return JSONmapOfObjects;
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Referral createOrUpdate(Referral referral, int visitId) throws NotFoundException {
        if (referral.isNew()) {
            checkNew(referral);
            log.info("create {} for user {}", referral, visitId);
            referralService.create(referral, visitId);
        } else {
            assureIdConsistent(referral, referral.getId());
            log.info("update {} for user {}", referral, visitId);
            referralService.update(referral, visitId);
        }
        return referralService.get(referral.getId(), visitId);
    }


    @DeleteMapping("/{id}/{visitId}")
    public void delete(@PathVariable("id") int id, @PathVariable("visitId") int visitId) throws NotFoundException {
        log.info("delete referral {} for visit {}", id, visitId);
        referralService.delete(id, visitId);
    }

    public List<Referral> getAll(int visitId) {
        log.info("get referral for visit {}", visitId);
        return referralService.getAll(visitId);
    }
}


