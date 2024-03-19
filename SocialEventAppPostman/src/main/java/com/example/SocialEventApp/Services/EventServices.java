package com.example.SocialEventApp.Services;

import com.example.SocialEventApp.Entity.Events;
import com.example.SocialEventApp.Entity.Location;
import com.example.SocialEventApp.Model.EventsModel;
import com.example.SocialEventApp.Model.LocationModel;
import com.example.SocialEventApp.Repository.EventRepository;
import com.example.SocialEventApp.Repository.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServices {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    public EventsModel addEvent(EventsModel eventsModel) {
        Events events = eventRepository.findById(eventsModel.getEventId()).orElse(null);
        if (events == null) {
            Events event = new Events();
            BeanUtils.copyProperties(eventsModel, event);
            eventRepository.save(event);
            eventsModel.setEventId(event.getEventId());
            return eventsModel;

        }
        return null;
    }
    public List<EventsModel> getAllEvents() {
        List<EventsModel>  eventsModelList=new ArrayList<>();
        List<Events> events=eventRepository.findAll();
        events.forEach(event->{
            EventsModel eventsModel=new EventsModel();
            BeanUtils.copyProperties(event,eventsModel);
            eventsModelList.add(eventsModel);
        });

        return eventsModelList;
    }

    public String addLocationToEvents(int eid, int lid) {
        Events events=eventRepository.findById(eid).orElse(null);
        Location location=locationRepository.findById(lid).orElse(null);
        if(events==null && location==null){
            return "Both are Invalid";
        }
        else if(events==null){
            return "Provide valid Event ID ";
        }
        else if(location==null){
            return "Provide valid Location ID ";
        }
        List<Location> locationList=events.getLocationList();
        int index=locationList.indexOf(location);
        if(index==-1){
            locationList.add(location);
            events.setLocationList(locationList);
            eventRepository.save(events);
            List<Events> eventsList=location.getEventsList();
            int i=eventsList.indexOf(events);

            return location.getEventsList().get(i).getEventCategory()+" is added to "+location.getVenueName();
        }
        return events.getEventCategory()+" is already added to "+location.getVenueName();
    }

    public List<Location> getLocation(int eid, int count) {
        Events e = eventRepository.findById(eid).orElse(null);
        if (e == null) {
            return null;
        }
        System.out.println(e);
        List<Location> locationList = e.getLocationList();
        List<Location > locationList1=locationList.stream()
                .sorted(Comparator.comparingInt(location -> Math.abs(location.getCapacity() - count)))
                .filter(location -> location.getCapacity()>=count)
                .collect(Collectors.toList());
        return locationList1;
    }

    public Events getEventById(int eid) {
        return eventRepository.findById(eid).orElse(null);
    }
}
