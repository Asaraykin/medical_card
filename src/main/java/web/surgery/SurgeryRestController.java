package web.surgery;

import model.Surgery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.surgery.SurgeryService;
import util.exception.NotFoundException;
import web.SecurityUtil;

import java.util.List;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@Controller
public class SurgeryRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private SurgeryService service;

    @Autowired
    public SurgeryRestController(SurgeryService service) {
        this.service = service;
    }

    public Surgery create(Surgery surgery){
        int userId = SecurityUtil.authUserId();
        checkNew(surgery);
        log.info("create {} for user {}", surgery, userId);
        return service.create(surgery, userId);
    }

    public  void update(Surgery surgery, int id) throws NotFoundException{
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(surgery, id);
        log.info("update {} for user {}", surgery, userId);
        service.update(surgery, userId);
    }

    public  void delete(int id, int patientId) throws NotFoundException{
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public Surgery get(int id) throws NotFoundException{
        int userId = SecurityUtil.authUserId();
        log.info("get surgery {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public List<Surgery> getAll(int patientId){
        int userId = SecurityUtil.authUserId();
        log.info("get surgery for user {}",  userId);
        return service.getAll(patientId);
    }
}
