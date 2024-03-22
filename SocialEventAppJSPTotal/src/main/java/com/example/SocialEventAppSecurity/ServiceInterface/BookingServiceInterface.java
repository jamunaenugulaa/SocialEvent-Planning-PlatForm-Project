package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Entity.Events;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;

import java.util.List;

public interface BookingServiceInterface {
    public BookingEventModel saveBooking(BookingEventModel booking);

    BookingEventModel checkingTheHall(BookingEventModel bookingEventModel, Customer customer, Events events);

    List<BookingEventModel> getAllEventsOfCustomer(Customer customer);

    BookingEventModel getBookingEventById(int bookingId);

    void cancelBookingEventByCustomer(BookingEventModel bookingEventModel, Customer customer);
}
