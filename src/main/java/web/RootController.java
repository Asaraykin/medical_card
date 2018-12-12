package web;


import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.patient.PatientService;
import service.surgery.SurgeryService;
import service.user.UserService;

import java.util.Arrays;

@Controller
public class RootController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private SurgeryService surgeryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }

    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public String userList(Model model, @RequestParam("userId") String id){
        int userId = Integer.parseInt(id);
        SecurityUtil.setAuthUserId(userId);
        User authorizedUser = userService.get(userId);
        if(authorizedUser.getRole().equals("doctor")){
            model.addAttribute("userList", patientService.getAll() );
            return "userList";
        }
        else {
            if (authorizedUser.getRole().equals("admin")) {
                model.addAttribute("userList", userService.getAll());
                return "userListForAdmin";
            }
            if (authorizedUser.getRole().equals("patient")) {
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

}
