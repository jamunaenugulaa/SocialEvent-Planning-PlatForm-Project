package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;
import com.example.SocialEventAppSecurity.Model.EventOrganizerModel;

import java.util.List;

public interface OrganizerServicesInterface {
    public EventOrganizerModel saveOrganizer(EventOrganizerModel organizerModel) ;
    public EventOrganizer findByUsernameAndPassword(String username, String password) ;
    public EventOrganizerModel getOrganizerById(int oId) ;
    public List<BookingEventModel> upcomingBookings(EventOrganizer eventOrganizer) ;
    public List<BookingEventModel> getAllPastBookings(EventOrganizer eventOrganizer) ;

    List<EventOrganizerModel> getAllOrganizers();

    Boolean deleteOrganizer(int id);

    EventOrganizer findById(int organizerId);
}
