package com.example.SocialEventAppSecurity.Controller;

import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Entity.Events;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Model.*;
import com.example.SocialEventAppSecurity.ServiceInterface.BookingServiceInterfcae;
import com.example.SocialEventAppSecurity.ServiceInterface.CustomerServiceInterface;
import com.example.SocialEventAppSecurity.ServiceInterface.EventServicesInterface;
import com.example.SocialEventAppSecurity.ServiceInterface.LocationServicesInterface;
import com.example.SocialEventAppSecurity.Services.BookingServices;
import com.example.SocialEventAppSecurity.Services.CustomerServices;
import com.example.SocialEventAppSecurity.Services.EventServices;
import com.example.SocialEventAppSecurity.Services.LocationServices;
import com.example.SocialEventAppSecurity.Validation.CustomerValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private EventServicesInterface eventServices;
    @Autowired
    private LocationServicesInterface locationServices;
    @Autowired
    private CustomerServiceInterface customerServices;
    @Autowired
    private BookingServiceInterfcae bookingServices;
    @Autowired
    private CustomerValidation customerValidation;
    int cid;


    @RequestMapping("/Customer")
    public String Customer(ModelMap mp) {
        mp.addAttribute("msg", false);
        return "Log";
    }

    @RequestMapping("/CustomerRegister")
    public String CustomerRegister(ModelMap map) {
        map.addAttribute("customer", new CustomerModel());
        return "customerRegistration";

    }

    @RequestMapping("/customerOrganizerCheck")
    public String customerOrganizerCheck(@Valid @ModelAttribute("customer") CustomerModel customerModel, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            return "customerRegistration";
        }
        try {
            CustomerModel cust = customerServices.registerCustomer(customerModel);
            if (cust == null) {
                map.addAttribute("customer", new CustomerModel());

                map.addAttribute("msg", "The Mobile Number Already Registered Try With Different Number");

                return "customerRegistration";

            }
        }
        catch (CustomerServices.DuplicateCustomerException ex) {
            map.addAttribute("msg", "A customer with the same phone number already exists.");
            return "customerRegistration";
        }
        map.addAttribute("msg", "You Have SuccessFully Registered");
        map.addAttribute("loginForm", new LoginForm());
        return "CustomerLoginForm";
    }


    @RequestMapping("/CustomerLogin")
    public String CustomerLogin(ModelMap map) {
        map.addAttribute("loginForm", new LoginForm());
        return "CustomerLoginForm";
    }
    @RequestMapping("/customerLoginCheck")
    public String customerLoginCheck(@Valid LoginForm loginForm, BindingResult result, ModelMap map) {
        customerValidation.validate(loginForm, result);
        if (result.hasErrors()) {

            return "CustomerLoginForm";
        }
        Customer customer = customerServices.findByUsernameAndPassowrd(loginForm.getUsername(), loginForm.getPassword());
        if(customer==null){
            map.addAttribute("msg", "USERNAME OR PASSWORD INCORRECT");
            return "CustomerLoginForm";


        }
        cid=customer.getUserId();
        System.out.println(cid);
        System.out.println(customer.getName());
        map.addAttribute("name",customer.getName());
        return "CustomerHomePage";

    }
    @RequestMapping("/ViewLocationC")
    public String viewLocations(ModelMap map){
        List<LocationModel> locationModels=locationServices.getAllLocations();
        map.addAttribute("locationList",locationModels);
        return "CustomerViewLocations";
    }
    @RequestMapping("/CustomerHomePage")
    public String CustomerHomePage(ModelMap map){
        if(cid==0){
            return  "CustomerHomePage";

        }
        Customer customer=customerServices.findById(cid);
        map.addAttribute("name",customer.getName());
        return  "CustomerHomePage";
    }
    @RequestMapping("/viewLocationForCustomer")
    public String viewLocationOfCustomer(){
       // map.addAttribute("eventCategory", EventCategory.values());
        return "viewLocationForCustomer";
    }
    @RequestMapping("/getLocationsBasedOnCategory")
    public String getLocationsBasedOnCategory(@RequestParam("eventCategory") EventCategory category, ModelMap map){
        List<LocationModel> locationModels=locationServices.getAllLocationsByCategory(category);
        if(locationModels.isEmpty()){
            map.addAttribute("msg","No Locations are Available");
        }
        System.out.println(locationModels);
        map.addAttribute("locationList",locationModels);
        return "CustomerViewLocations";
    }
    @RequestMapping("/getLocationsBasedOnCapacity")
    public String getLocationsBasedOnCapacity(@RequestParam("capacity") int capacity,ModelMap map){
        List<LocationModel> locationModels=locationServices.getAllLocationsByCapacity(capacity);
        if(locationModels.isEmpty()){
            map.addAttribute("msg","No Locations are Available");
        }
        map.addAttribute("locationList",locationModels);
        return "CustomerViewLocations";
    }
    @RequestMapping("/getLocationsBasedOnDate")
    public String getLocationsBasedOnDate(@RequestParam("dateInput") LocalDate date, ModelMap map){
        List<LocationModel> locationModels=locationServices.getAllLocationsByDate(date);
        System.out.println(locationModels);
        if(locationModels.isEmpty()){
            map.addAttribute("msg","No Locations are Available");
        }
        map.addAttribute("locationList",locationModels);
        return "CustomerViewLocations";
    }
    @RequestMapping("/getLocationsBasedOnForm")
    public String getLocationsBasedOnForm(@RequestParam("eventCategory") EventCategory category, @RequestParam("capacity") int capacity, @RequestParam("dateInput") LocalDate date, ModelMap map){
        List<LocationModel> locationModels=locationServices.getAllLocationsByForm(date,category,capacity);
        if(locationModels.isEmpty()){
            map.addAttribute("msg","No Locations are Available");
        }
        map.addAttribute("locationList",locationModels);
        return "CustomerViewLocations";
    }
    @RequestMapping("/BookingTheHall")
    public String bookingTheHall(@RequestParam("location") Location location, ModelMap map){
        System.out.println(location);
        System.out.println(cid);
      map.addAttribute("location",location);
      map.addAttribute("bookingForm",new BookingEventModel());
        List<EventsModel> eventModels=eventServices.getEventsByCategory(location);
        map.addAttribute("eventModel",eventModels);

        return "BookingHallForm";
    }
    @RequestMapping("/bookingHallDone")
    public  String bookingHallDone(@Valid @ModelAttribute("bookingForm") BookingEventModel bookingEventModel, BindingResult result,ModelMap map){
        System.out.println(bookingEventModel);
        List<EventsModel> eventModels=eventServices.getEventsByCategory(bookingEventModel.getLocation());
        map.addAttribute("eventModel",eventModels);
        map.addAttribute("location",bookingEventModel.getLocation());

        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "BookingHallForm";
        }
        Customer customer=customerServices.findById(cid);
        if(customer==null){
            map.addAttribute("msg","Customer Not Found");
            map.addAttribute("bookingForm",new BookingEventModel());
            map.addAttribute("location",bookingEventModel.getLocation());

            return "BookingHallForm";
        }
        System.out.println(bookingEventModel);
        Events events= eventServices.getEventByEventName(bookingEventModel.getEventName());
       String message=bookingServices.BookTheHall(bookingEventModel,customer,events);
        System.out.println(message);

        map.addAttribute("msg",message);
        return "BookingHallForm";

    }
    @RequestMapping("/viewEventsOfCustomer")
    public String viewEventsOfCustomer(ModelMap map){
        Customer customer=customerServices.findById(cid);
        System.out.println(cid);
        if(customer==null){
            map.addAttribute("msg","Customer Not Found");
            return "CustomerHomePage";
        }
        List<BookingEventModel> bookingEventModels=bookingServices.getAllEventsOfCustomer(customer);
        if(bookingEventModels.isEmpty()){
            map.addAttribute("msg","NO BOOKING EVENTS FOUND");
        }
        map.addAttribute("bookingList",bookingEventModels);
        return "viewEventsOfCustomer";

    }
}
