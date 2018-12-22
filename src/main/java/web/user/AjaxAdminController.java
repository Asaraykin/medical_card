package web.user;

import model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.user.UserService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/rest/admin/")
public class AjaxAdminController {

    private static final Logger log = getLogger(AjaxAdminController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        log.info("getAll");
       return userService.getAll();
    }
}
