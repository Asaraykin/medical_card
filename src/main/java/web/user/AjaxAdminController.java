package web.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.user.UserService;
import web.json.JacksonObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/rest/admin/")
public class AjaxAdminController {

    private static final Logger log = getLogger(AjaxAdminController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userList")
    @ResponseBody
    public Object getAll(@RequestParam("search[value]") String search,
                         @RequestParam("start") int start,
                         @RequestParam("length") int length,
                         @RequestParam("draw") int draw) {
        log.info("getAll");
        Map<Object, Object> map = new HashMap<>();
        map.put("draw", ++draw);
        List<User> userList = null;
        if(search.equals("")){
            userList = userService.getByPage(start, length);
            map.put("recordsFiltered", userService.getNumberOfUsers());
            map.put("recordsTotal", userService.getNumberOfUsers());
        }
        else {
            userList = userService.search(search, start, length);
            map.put("recordsFiltered", userService.getNumberOfUsersFound());
            map.put("recordsTotal", userService.getNumberOfUsersFound());
        }
        map.put("data", userList);
        return map;
    }

    @DeleteMapping(value = "/userList/{id}/{notUsed}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> deleteUser(@PathVariable("id") int id, @PathVariable("notUsed") int notUsed) {
        log.info("delete user {}", id);
        userService.delete(id);
        return userService.getAll();
    }
}
