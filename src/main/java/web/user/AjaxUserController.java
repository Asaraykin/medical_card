package web.user;

import model.Patient;
import model.User;
import model.UserRoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.user.UserService;
import web.SecurityUtil;
import web.patient.PatientAjaxRestController;

import java.util.Arrays;
import java.util.List;

import static util.ValidationUtil.assureIdConsistent;
import static util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/rest/")
public class AjaxUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private UserService userService;
    private PatientAjaxRestController patientAjaxRestController;


    @Autowired
    public AjaxUserController(UserService userService, PatientAjaxRestController patientAjaxRestController) {
        this.userService = userService;
        this.patientAjaxRestController = patientAjaxRestController;
    }

    @GetMapping(value = "/userList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getUsers() {
        log.info("getUsers");
        int id = SecurityUtil.authUserId();
        if(userService.get(id).getRole().equals(UserRoleEnum.DOCTOR.name()) || userService.get(id).getRole().equals(UserRoleEnum.ADMIN.name())){
            return patientAjaxRestController.getAll();
        }
       else {
           return Arrays.asList(patientAjaxRestController.get(id));
        }
    }



}
