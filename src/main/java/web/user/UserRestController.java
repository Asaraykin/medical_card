package web.user;

import model.Patient;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.user.UserService;
import web.patient.PatientRestController;

import java.util.List;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@Controller
public class UserRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private UserService userService;
    private PatientRestController patientRestController;


    @Autowired
    public UserRestController(UserService userService, PatientRestController patientRestController) {
        this.userService = userService;
        this.patientRestController = patientRestController;
    }

    public List<User> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    public User get(int id){
        log.info(String.valueOf(id));
        return userService.get(id);
    }

    public List<User> getByRole(String role){
        log.info("getByRole");
        return userService.getByRole(role);
    }

    public User create(User user){
        return userService.create(user);
    }

    public User create(User user, Patient patient){
        log.info("create {}", user);
        checkNew(user);
        User newUser = userService.create(user);
        patientRestController.create(patient);
        return newUser;
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        userService.update(user);
    }
}
