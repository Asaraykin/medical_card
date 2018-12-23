package web;

import model.AbstractBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

//TODO delete

public class EntityControllerUtility {

    public static boolean checkIsNew(AbstractBaseEntity model){
        if (model.isNew()) {
            checkNew(model);
            return true;
        } else {
            assureIdConsistent(model, model.getId());
          return false;
        }
    }
}
