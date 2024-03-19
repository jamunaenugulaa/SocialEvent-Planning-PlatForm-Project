package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.Events;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Model.EventsModel;

import java.util.List;

public interface EventServicesInterface {
    public EventsModel addEvent(EventsModel eventsModel) ;
    public List<EventsModel> getAllEvents() ;

    public Events getEventById(int eid) ;

    public List<EventsModel> getEventsByCategory(Location location) ;
    public Events getEventByEventName(String eventName) ;
    public void updateEvent(EventsModel eventModel) ;
    }

