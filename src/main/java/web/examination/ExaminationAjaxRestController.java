package web.examination;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Examination;
import model.Referral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.examination.ExaminationService;
import service.referral.ReferralService;
import util.exception.NotFoundException;
import web.json.JacksonObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/rest/examination/")
public class ExaminationAjaxRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ReferralService referralService;
    private ExaminationService examinationService;

    @Autowired
    public ExaminationAjaxRestController(ReferralService referralService, ExaminationService examinationService) {
        this.referralService = referralService;
        this.examinationService = examinationService;
    }

    @GetMapping(value = "/{id}/{referralId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable("id") int id,
                      @PathVariable("referralId") int referralId) throws NotFoundException {
        log.info("get examination {} for examination {}", id, referralId);
        ObjectMapper mapper = JacksonObjectMapper.getMapper();
        Map<Object, Object> map = new HashMap<>();
        // map.put("visits", visitService.get(id, referralId));
        map.put("examination", examinationService.get(id, referralId));
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
    public Examination createOrUpdate(Examination examination, int referralId) throws NotFoundException {
        if (examination.isNew()) {
            checkNew(examination);
            log.info("create {} for referral {}", examination, referralId);
            examinationService.create(examination, referralId);
        } else {
            assureIdConsistent(examination, examination.getId());
            log.info("update {} for user {}", examination, referralId);
            examinationService.update(examination, referralId);
        }
        return examinationService.get(examination.getId(), referralId);
    }


    @DeleteMapping("/{id}/{referralId}")
    public void delete(@PathVariable("id") int id, @PathVariable("referralId") int referralId) throws NotFoundException {
        log.info("delete examination {} for referral {}", id, referralId);
        examinationService.delete(id, referralId);
    }

    public List<Referral> getAll(int visitId) {
        log.info("get examination for visit {}", visitId);
        return referralService.getAll(visitId);
    }
}
