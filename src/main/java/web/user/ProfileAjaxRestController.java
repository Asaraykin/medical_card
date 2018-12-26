package web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.user.UserService;

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
        //System.out.println("from DB: " + userService.get(id).getPassword());
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

}
