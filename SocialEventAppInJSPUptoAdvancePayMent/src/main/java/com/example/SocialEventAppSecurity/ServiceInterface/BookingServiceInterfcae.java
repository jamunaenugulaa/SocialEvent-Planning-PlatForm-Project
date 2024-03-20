package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Entity.Events;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;

import java.util.List;

public interface BookingServiceInterfcae {
    public BookingEventModel saveBooking(BookingEventModel booking) ;
    public BookingEventModel BookTheHall(BookingEventModel bookingEventModel, Customer customer, Events events) ;
    public List<BookingEventModel> getAllEventsOfCustomer(Customer customer) ;


    BookingEventModel getBookingEventById(int bookingId);
}
