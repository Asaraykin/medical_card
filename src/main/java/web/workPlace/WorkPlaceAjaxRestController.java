package web.workPlace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.workPlace.WorkPlaceService;

@RestController
@RequestMapping("/rest/workPlace/")
public class WorkPlaceAjaxRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private WorkPlaceService workPlaceService;

    @Autowired
    public WorkPlaceAjaxRestController(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
    }




/*
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
    }*/
}
