package com.example.SocialEventApp.Controller;

import com.example.SocialEventApp.Entity.BookingEvent;
import com.example.SocialEventApp.Model.EventOrganizerModel;
import com.example.SocialEventApp.Model.EventsModel;
import com.example.SocialEventApp.Model.LocationModel;
import com.example.SocialEventApp.Repository.LocationRepository;
import com.example.SocialEventApp.Services.EventServices;
import com.example.SocialEventApp.Services.LocationServices;
import com.example.SocialEventApp.Services.OrganizerServices;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Organizer")
public class OrganizerRestController {
    @Autowired
    private OrganizerServices organizerServices;
    @Autowired
    private EventServices eventServices;
    @Autowired
    private LocationServices locationServices;
    @PostMapping("/AddOrganizer")
    public ResponseEntity<EventOrganizerModel> addOrganizer(@Valid @RequestBody EventOrganizerModel organizerModel){
        EventOrganizerModel event=  organizerServices.saveOrganizer(organizerModel);

        return new ResponseEntity<EventOrganizerModel>(event, HttpStatus.CREATED);

    }
    @PostMapping("/addEvent")
    public ResponseEntity<EventsModel> addOrganizer(@Valid @RequestBody EventsModel eventsModel){
        System.out.println(eventsModel);
        EventsModel event=eventServices.addEvent(eventsModel);

        return new ResponseEntity<EventsModel>(event, HttpStatus.CREATED);

    }
    @PostMapping("/addLocation")
    public ResponseEntity< LocationModel> addOrganizer(@Valid @RequestBody LocationModel locationModel){

        System.out.println(locationModel);
        LocationModel location=locationServices.addLocation(locationModel);
        return new ResponseEntity< LocationModel>(location, HttpStatus.CREATED);

    }
    @GetMapping("/getEvents")
    public List<EventsModel> getEvents(){
        return eventServices.getAllEvents();
    }
    @GetMapping("getLocations")
    public List<LocationModel> getLocation(){
        return locationServices.getAllLocations();
    }
    @DeleteMapping("/deleteLocation")
    public String deleteLocation(@RequestBody LocationModel locationModel){
        return locationServices.deleteLocation(locationModel);
    }
    @PostMapping("/addLocationToEvents")
    public String addLocationToEvents(@RequestParam("event") int eid,@RequestParam("location") int lid){
        return eventServices.addLocationToEvents(eid,lid);
    }


}
