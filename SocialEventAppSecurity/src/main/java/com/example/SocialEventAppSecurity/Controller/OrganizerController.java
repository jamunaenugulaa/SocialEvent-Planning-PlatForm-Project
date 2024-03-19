package com.example.SocialEventAppSecurity.Controller;


import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import com.example.SocialEventAppSecurity.Entity.Events;
import com.example.SocialEventAppSecurity.Model.*;
import com.example.SocialEventAppSecurity.ServiceInterface.EventServicesInterface;
import com.example.SocialEventAppSecurity.ServiceInterface.LocationServicesInterface;
import com.example.SocialEventAppSecurity.ServiceInterface.OrganizerServicesInterface;
import com.example.SocialEventAppSecurity.Validation.OrganizerValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrganizerController {

    @Autowired
    private EntityToModelConversion entityToModelConversion;
    @Autowired
    private OrganizerServicesInterface organizerServices;
    @Autowired
    private EventServicesInterface eventServices;
    @Autowired
    private LocationServicesInterface locationServices;
    int OId;
    @Autowired
    private OrganizerValidation organizerValidation;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/Organizer")
    public String Organizer(ModelMap mp) {
        mp.addAttribute("msg", true);
        return "Log";
    }



    @RequestMapping("/OrganizerLogin")
    public String OrganizerLogin(ModelMap map) {
        map.addAttribute("loginForm", new LoginForm());
        return "OrganizerLogin";
    }

    @RequestMapping("organizerLoginCheck")
    public String organizerLoginCheck(@Valid LoginForm loginForm, BindingResult result, ModelMap map) {
       organizerValidation.validate(loginForm,result);
        if (result.hasErrors()) {

            return "OrganizerLogin";
        }
        EventOrganizer eventOrganizerModel=organizerServices.fibdByUsernameAndPassword(loginForm.getUsername(),loginForm.getPassword());
        if(eventOrganizerModel!=null){
            OId= eventOrganizerModel.getId();
            map.addAttribute("name",eventOrganizerModel.getName());
            return  "OrganizerHomePage";
        }
        map.addAttribute("msg", "USERNAME OR PASSWORD INCORRECT");
        return "OrganizerLogin";
    }
    @RequestMapping("/OrganizerHomePage")
    public String OrganizerHomePage(ModelMap map){
        if(OId==0){
            return  "OrganizerHomePage";

        }
        EventOrganizerModel  eventOrganizerModel=organizerServices.getOrganizerById(OId);
        map.addAttribute("name",eventOrganizerModel.getName());
        return  "OrganizerHomePage";
    }

    @RequestMapping("/addEvents")
    public String addEvents(ModelMap map){
        map.addAttribute("event",new EventsModel());
        return "addEventForm";
    }
    @RequestMapping("/addEventCheck")
    public String addEventCheck(@Valid @ModelAttribute("event") EventsModel eventModel,BindingResult  result, ModelMap map){
        if(result.hasErrors()){
            return "addEventForm";
        }
        eventServices.addEvent(eventModel);
        return "redirect:viewEvents";

    }
    @RequestMapping("/viewEvents")
    public String viewEvents(ModelMap map){
        List<EventsModel> eventsModelList =eventServices.getAllEvents();
        map.addAttribute("eventList",eventsModelList);
        return "OrganizerViewEvents";
    }
    @RequestMapping("/addLocations")
    public String addLocations(ModelMap map){
        map.addAttribute("location",new LocationModel());
        return "addLocationForm";
    }
    @RequestMapping("/addLocationCheck")
    public String addLocationCheck(@Valid  @ModelAttribute("location") LocationModel locationModel,BindingResult result,ModelMap map){
        if(result.hasErrors()){
            return "addLocationForm";
        }
        locationServices.addLocation(locationModel);
        return "redirect:viewLocations";
    }
    @RequestMapping("/viewLocations")
    public String viewLocations(ModelMap map){
        List<LocationModel> locationModels=locationServices.getAllLocations();
        map.addAttribute("locationList",locationModels);
        return "OrganizerViewLocations";
    }
    @RequestMapping("/upcomingEvents")
    public String upcomingEvents(ModelMap map){
        List<BookingEventModel> bookingEventModels=organizerServices.upcomingBookings();
        System.out.println(bookingEventModels);
        map.addAttribute("msg","LIST OF ONGOING AND UPCOMING BOOKING EVENTS");
        map.addAttribute("bookingEvents",bookingEventModels);
        return "ViewUpcomingEvents";
    }
    @RequestMapping("/PastEvents")
    public String pastEvents(ModelMap map){
        List<BookingEventModel> bookingEventModels=organizerServices.pastBooking();
        System.out.println(bookingEventModels);
        map.addAttribute("msg","LIST OF PAST BOOKING EVENTS");
        map.addAttribute("bookingEvents",bookingEventModels);
        return "ViewUpcomingEvents";
    }
    @RequestMapping("/updateEvents")
    public String updateEvents( @RequestParam("eventId") int eventId,ModelMap map){
        System.out.println(eventId);
        Events event=eventServices.getEventById(eventId);
        EventsModel eventModel=entityToModelConversion.entitytoEventsModel(event);
        map.addAttribute("eventModel",eventModel);
        return "updateEventsForm";
    }
    @RequestMapping("/updateEventCheck")
    public String updateEventsForm(@Valid @ModelAttribute("eventModel") EventsModel eventModel,BindingResult  result, ModelMap map){
        System.out.println(eventModel);
        if(result.hasErrors()){
            return "updateEventsForm";
        }
        eventServices.updateEvent(eventModel);
        return "redirect:viewEvents";

    }
}
