package com.example.SocialEventAppSecurity.Services;

import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.Events;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Model.EventsModel;
import com.example.SocialEventAppSecurity.Repository.EventRepository;
import com.example.SocialEventAppSecurity.Repository.LocationRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.EventServicesInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServices implements EventServicesInterface {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ModelToEntityConversion modelToEntityConversion;
    @Autowired
    private EntityToModelConversion entityToModelConversion;

    public EventsModel addEvent(EventsModel eventsModel) {
        Events event=eventRepository.findByEventName(eventsModel.getEventName());
        if(event!=null){
            return null;
        }
        Events event2 =modelToEntityConversion.modelToEventsEntity(eventsModel);
        eventRepository.save(event2);
        eventsModel.setEventId(event.getEventId());
            return eventsModel;

    }
    public List<EventsModel> getAllEvents() {
        List<EventsModel>  eventsModelList=new ArrayList<>();
        List<Events> events=eventRepository.findAll();
        events.forEach(event->{
            EventsModel eventsModel=entityToModelConversion.entityToEventsModel(event);
            eventsModelList.add(eventsModel);
        });

        return eventsModelList;
    }


    public Events getEventById(int eid) {
        return eventRepository.findById(eid).orElse(null);
    }




    public List<EventsModel> getEventsByCategory(Location location) {
        List<Events> eventsList=eventRepository.findAll();
        List<Events> events=eventsList.stream()
                .filter(events1 -> location.getCategoryList().contains(events1.getEventCategory())).collect(Collectors.toList());
        List<EventsModel> eventsModelList=new ArrayList<>();
        events.forEach(events1 -> {
            EventsModel eventsModel=entityToModelConversion.entityToEventsModel(events1);
            eventsModelList.add(eventsModel);
        });
        return eventsModelList;
    }

    public Events getEventByEventName(String eventName) {
        return eventRepository.findByEventName(eventName);
    }

    public void updateEvent(EventsModel eventModel) {
        Events event=modelToEntityConversion.modelToEventsEntity(eventModel);
        eventRepository.save(event);
    }
}
