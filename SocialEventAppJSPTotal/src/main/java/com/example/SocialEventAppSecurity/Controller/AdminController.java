package com.example.SocialEventAppSecurity.Controller;

import com.example.SocialEventAppSecurity.Entity.Admin;
import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Model.AdminModel;
import com.example.SocialEventAppSecurity.Model.EventOrganizerModel;
import com.example.SocialEventAppSecurity.Model.LoginForm;
import com.example.SocialEventAppSecurity.ServiceInterface.AdminServiceInterface;
import com.example.SocialEventAppSecurity.ServiceInterface.OrganizerServicesInterface;
import com.example.SocialEventAppSecurity.Validation.LoginFormValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminServiceInterface adminServices;
    @Autowired
    private OrganizerServicesInterface organizerServices;
    @Autowired
    private LoginFormValidation loginFormValidation;
    private int adminId;
    private String message;
    @RequestMapping("/Admin")
    public String admin(){
        return "Admin";
    }
    @RequestMapping("/AdminRegister")
    public String  adminRegister(ModelMap map){
        map.addAttribute("adminModel",new AdminModel());
        return "AdminRegisterForm";
    }
    @RequestMapping("/adminRegistrationCheck")
    public String adminRegistrationCheck(@Valid @ModelAttribute("adminModel") AdminModel adminModel, BindingResult result,ModelMap map){
        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "AdminRegisterForm";
        }
        AdminModel admin=adminServices.registerAnAdmin(adminModel);
        if(admin==null){
            map.addAttribute("msg","The Username Already exists Try With Another One.");
            return "AdminRegisterForm";
        }
        adminId=admin.getAdminId();
        return "redirect:AdminLogin";
    }


    @RequestMapping("/AdminLogin")
    public String adminLogin(ModelMap map) {
        map.addAttribute("loginForm", new LoginForm());
        return "AdminLoginForm";
    }
    @RequestMapping("/AdminLoginCheck")
    public String customerLoginCheck(@Valid LoginForm loginForm, BindingResult result, ModelMap map) {
        loginFormValidation.validate(loginForm, result);
        if (result.hasErrors()) {

            return "AdminLoginForm";
        }
    Admin admin = adminServices.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
        if(admin==null){
            map.addAttribute("msg", "USERNAME OR PASSWORD INCORRECT");
            return "AdminLoginForm";


        }
        adminId=admin.getAdminId();
        System.out.println(adminId);
        System.out.println(admin.getAdminName());
        map.addAttribute("name",admin.getAdminName());
        return "AdminHomePage";

    }

    @RequestMapping("/OrganizerRegister")
    public String organizerRegister(ModelMap map) {
        map.addAttribute("eventOrganizer", new EventOrganizerModel());
        return "RegisterOrganizer";
    }



    @RequestMapping("/registerOrganizerCheck")
    public String registerOrganizerCheck(@Valid @ModelAttribute("eventOrganizer") EventOrganizerModel eventOrganizerModel, BindingResult result, ModelMap map) {
        //System.out.println("id"+eventOrganizerModel.getId());
        if (result.hasErrors()) {
            return "RegisterOrganizer";
        }
        EventOrganizerModel event= organizerServices.saveOrganizer(eventOrganizerModel);
        if(event==null){
            map.addAttribute("msg", "The ID or Username Already Exist");
            return "RegisterOrganizer";
        }
        message=event.getName()+" SuccessFully Registered";
        return "redirect:ViewOrganizers";
    }
    @RequestMapping("/AdminHomePage")
    public String adminHomePage(ModelMap map){
        if(adminId==0){
                return "AdminHomePage";
            }
        Admin admin=adminServices.findById(adminId);
        System.out.println(admin.getAdminName());
        map.addAttribute("name",admin.getAdminName());
        return "AdminHomePage";
    }
    @RequestMapping("/ViewOrganizers")
    public String viewOrganizers(ModelMap map){
        if(message!=null){
           map.addAttribute("msg",message);
        }
        List<EventOrganizerModel> eventOrganizerModelList=organizerServices.getAllOrganizers();
        message=null;
        map.addAttribute("eventsOrganizers",eventOrganizerModelList);
        return "viewOrganizers";
    }
    @RequestMapping("/DeleteOrganizer")
    public String deleteOrganizer(@RequestParam("organizer") int id, ModelMap map){
        System.out.println(id);
        Boolean temp=organizerServices.deleteOrganizer(id);
        if(temp){
            message="Deleted SuccessFully Registered";

        }
        else{
            message="Not Deleted";
        }
        return "redirect:ViewOrganizers";

    }
}
