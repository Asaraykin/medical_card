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
import org.springframework.web.bind.annotation.*;
import service.examination.ExaminationService;
import service.patient.PatientService;
import service.referral.ReferralService;
import service.surgery.SurgeryService;
import service.user.UserService;
import service.visit.VisitService;
import service.workPlace.WorkPlaceService;
import util.exception.NotFoundException;

@Controller
public class RootController {

    private PatientService patientService;
    private UserService userService;
    private SurgeryService surgeryService;
    private VisitService visitService;
    private WorkPlaceService workPlaceService;
    private ReferralService referralService;
    private ExaminationService examinationService;

    @Autowired
    public RootController(PatientService patientService, UserService userService, SurgeryService surgeryService, VisitService visitService, WorkPlaceService workPlaceService, ReferralService referralService, ExaminationService examinationService) {
        this.patientService = patientService;
        this.userService = userService;
        this.surgeryService = surgeryService;
        this.visitService = visitService;
        this.workPlaceService = workPlaceService;
        this.referralService = referralService;
        this.examinationService = examinationService;
    }

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

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String userList(Model model, @RequestParam("id") int userId){
        SecurityUtil.setAuthUserId(userId);
        User authorizedUser = userService.get(userId);
        if(authorizedUser.getRole().equals(UserRoleEnum.ADMIN.name())){
           return "userListForAdmin";
        }
        else {
            model.addAttribute("id", userId);
            return "userList";
        }
    }

    @GetMapping("/rest/patient")
    public String patientCard(Model model, @RequestParam("id") int id){
        model.addAttribute("id", id);
        return "patientCard";
    }

    @GetMapping(value = "/rest/surgery")
    public String getSurgery(@RequestParam(value = "id", required = false) Integer id, @RequestParam("patientId") int userId, Model model) throws NotFoundException {
       if (id != null) {
           model.addAttribute("surgery", surgeryService.get(id, userId));
       }
        model.addAttribute("patientId", userId);
        return "surgery";
    }

    @GetMapping(value = "/rest/visit")
    public String getVisit(@RequestParam(value = "id", required = false) Integer id, @RequestParam("patientId") int patientId, Model model) throws NotFoundException {
        if (id != null) {
            model.addAttribute("visit", visitService.get(id, patientId));
        }
        model.addAttribute("patientId", patientId);
        return "visit";
    }

    @GetMapping(value = "/rest/referral")
    public String getReferral(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam("visitId") int visitId,
                              Model model) throws NotFoundException {
        if (id != null) {
            model.addAttribute("referral", referralService.get(id, visitId));
            model.addAttribute("examination", examinationService.getAll(id));
        }
        model.addAttribute("visitId", visitId);
        return "referral";
    }

    @GetMapping(value = "/rest/examination")
    public String getExamination(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam("referralId") int referralId,
                              Model model) throws NotFoundException {
        if (id != null) {
            model.addAttribute("examination", examinationService.get(id, referralId));
        }
        model.addAttribute("referralId", referralId);
        return "examination";
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
