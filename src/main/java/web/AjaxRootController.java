package web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.user.UserService;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/rest/admin/")
public class AjaxRootController {

    private static final Logger log = getLogger(MainServlet.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userList")
    public String getAll(Model model){
        log.info("getAll");
        model.addAttribute("userList", userService.getAll());
        return "userListForAdmin";
    }
}
