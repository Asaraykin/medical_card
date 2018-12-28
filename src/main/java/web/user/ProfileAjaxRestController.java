package web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import model.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/profile")
public class ProfileAjaxRestController {

    private UserService userService;

    @Autowired
    public ProfileAjaxRestController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping(value = "/{id}/{oldPassword}/{newPassword}")
    public ResponseEntity editProfile(Model model, @PathVariable("id") int id,
                                      @PathVariable("oldPassword") String oldPassword,
                                      @PathVariable("newPassword") String newPassword){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println("old: " + oldPassword);
        System.out.println("new: " + newPassword);
        System.out.println(passwordEncoder.matches(oldPassword, userService.get(id).getPassword()));
       if(passwordEncoder.matches(oldPassword, userService.get(id).getPassword())){
          String encodedNewPassword = passwordEncoder.encode(newPassword);
           User user = userService.get(id);
           user.setPassword(encodedNewPassword);
           userService.update(user);
           System.out.println("encoded new: " + userService.get(id).getPassword());
           return  new ResponseEntity(HttpStatus.ACCEPTED);
       }
       else {
           model.addAttribute("userId", id);
           return  new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
       }
    }


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void createNewUser(User user){
        user.setId(null);
        if (user.getRole() == null) {
            user.setRole(UserRoleEnum.PATIENT.name());
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
    }


    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllUsers(){
        List<User> userList = userService.getAll();
        System.out.println("getallusers");
        return userList.stream().map(User::getLogin).collect(Collectors.toList());
    }

}
