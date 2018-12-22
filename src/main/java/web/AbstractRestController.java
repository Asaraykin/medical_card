package web;

import model.AbstractBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.exception.NotFoundException;

import static util.ValidationUtil.checkNew;

public class AbstractRestController {
 /*   private final Logger log = LoggerFactory.getLogger(getClass());

    public <T extends AbstractBaseEntity> T createOrUpdate(T object, <S extends > S, int userId) throws NotFoundException {
        if (object.isNew()) {
            checkNew(object);
            log.info("create {} for user {}", visit, userId);
            visitService.create(visit, userId);
        } else {
            assureIdConsistent(visit, visit.getId());
            log.info("update {} for user {}", visit, userId);
            visitService.update(visit, userId);
        }
        return ;
    }*/
}
