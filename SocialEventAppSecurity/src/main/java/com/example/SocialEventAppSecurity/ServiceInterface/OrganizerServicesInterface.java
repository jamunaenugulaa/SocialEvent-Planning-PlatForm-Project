package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;
import com.example.SocialEventAppSecurity.Model.EventOrganizerModel;

import java.util.List;

public interface OrganizerServicesInterface {
    public EventOrganizerModel saveOrganizer(EventOrganizerModel organizerModel) ;
    public EventOrganizer fibdByUsernameAndPassword(String username, String password) ;
    public EventOrganizerModel getOrganizerById(int oId) ;
    public List<BookingEventModel> upcomingBookings() ;
    public List<BookingEventModel> pastBooking() ;

    List<EventOrganizerModel> getAllOrganizers();

    Boolean deleteOrganizer(int id);
}
