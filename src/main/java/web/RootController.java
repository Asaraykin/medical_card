package web;



import model.User;
import model.UserRoleEnum;
import model.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.patient.PatientService;
import service.surgery.SurgeryService;
import service.user.UserService;
import service.workPlace.WorkPlaceService;

import java.util.Arrays;

@Controller
public class RootController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private WorkPlaceService workPlaceService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (!currentPrincipalName.equals("anonymousUser")){
            SecurityUtil.setAuthUserId(userService.getByLogin(currentPrincipalName).getId());
            model.addAttribute("userId", SecurityUtil.authUserId());
        }
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/rest/userList", method = RequestMethod.GET)
    public String userList(Model model, @RequestParam("id") String id){
        int userId = Integer.parseInt(id);
        SecurityUtil.setAuthUserId(userId);
        User authorizedUser = userService.get(userId);
        if(authorizedUser.getRole().equals(UserRoleEnum.DOCTOR.name())){
            model.addAttribute("userList", patientService.getAll() );
            return "userList";
        }
        else {
            if (authorizedUser.getRole().equals(UserRoleEnum.ADMIN.name())) {
               model.addAttribute("userList", userService.getAll());
                return "userListForAdmin";
            }
            if (authorizedUser.getRole().equals(UserRoleEnum.PATIENT.name())) {
                model.addAttribute("userList", Arrays.asList(patientService.get(userId)));

            }
            return "userList";
        }
    }

    @GetMapping("/patientCard")
    public String patientCard(Model model, @RequestParam("id") String id){
        int patientId = Integer.parseInt(id);
        model.addAttribute("patient", patientService.get(patientId));
        model.addAttribute("surgery", surgeryService.getAll(patientId));
        return "patientCard";
    }

    @GetMapping("/delete")
    public String deletePatient(Model model, @RequestParam("id") String id){
        int patientId = Integer.parseInt(id);
        patientService.delete(patientId);
        model.addAttribute("userList", patientService.getAll() );
        return "userList";
    }

    @GetMapping("/insertWork")
    public String insertWork(Model model, @RequestParam("id") String id) {
        int patientId = Integer.parseInt(id);
        WorkPlace workPlace = new WorkPlace("qwerqweqwde");
        workPlace = workPlaceService.create(workPlace);
        int Wid = workPlace.getId();
        patientService.addWorkPlace(workPlaceService.create(workPlaceService.get(Wid)), patientId);
        model.addAttribute("userList", patientService.getAll());
        return "userList";
    }



    @GetMapping("/test")
    public String test(Model model) {
        User user = userService.get(100000);

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String rawPassword = "1";
        user.setPassword(passwordEncoder.encode(rawPassword));
        String passwordFromDB = user.getPassword();
        boolean isMatched = passwordEncoder.matches(rawPassword, user.getPassword());
        model.addAttribute("hashedPassword", rawPassword);
        model.addAttribute("passwordFromDB", passwordFromDB);
        model.addAttribute("mathed", isMatched);
        userService.create(user);
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            // user password is correct
            System.out.println("correct");
        }
        else{
            //user password incorrect
            System.out.println("incorrect");
        }
        return "test";
    }



}
