package com.example.SocialEventApp.Services;

import com.example.SocialEventApp.Entity.BookingEvent;
import com.example.SocialEventApp.Entity.Customer;
import com.example.SocialEventApp.Entity.Location;
import com.example.SocialEventApp.Model.BookingEventModel;
import com.example.SocialEventApp.Repository.BookingRepository;
import com.example.SocialEventApp.Repository.EventRepository;
import com.example.SocialEventApp.Repository.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@Service
public class BookingServices {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CustomerServices customerServices;
    public String saveBooking(BookingEventModel booking, Customer customer) {
        Boolean customerCheck=customerServices.checkBookingDate(booking,customer);
        System.out.println(customerCheck);
        if(customerCheck==true){
            return "Your Already Booked an Event on This Day "+booking.getLocalDate();
        }

        Location location= booking.getLocation();
        List<BookingEvent> bookingEvents=location.getBookingEvents();
        AtomicBoolean temp= new AtomicBoolean(true);
            bookingEvents.forEach(bookingEvent -> {
                if (bookingEvent.getLocalDate().equals(booking.getLocalDate()) && bookingEvent.getEvent().equals(booking.getEvent())) {
                    //System.out.println("Already " + location.getVenueName() + " is Booked on " + bookingEvent.getLocalDate()+" for "+booking.getEvent().getEventCategory());
                    temp.set(false);
                }
        });
        if(!temp.get()){
            return "Already " + location.getVenueName() + " is Booked on " + booking.getLocalDate()+" for "+booking.getEvent().getEventCategory();
        }
        System.out.println(temp);
        BookingEvent bookingevent=new BookingEvent();
        System.out.println(booking);
        BeanUtils.copyProperties(booking,bookingevent);
        bookingRepository.save(bookingevent);
        customerServices.bookingEvents(bookingevent,customer);

        return location.getVenueName()+" is Successfully Booked for "+booking.getEventName()+" on "+booking.getLocalDate();
    }

    public void deleteBooking(int id,int cid) {
        BookingEvent bookingevent=bookingRepository.findById(id).orElse(null);
        Location location=bookingevent.getLocation();
        bookingevent.setEvent(null);
        bookingevent.setLocation(null);
      //  locationRepository.save(location);
        bookingRepository.save(bookingevent);
        customerServices.deleteBooking(bookingevent,cid);
        bookingRepository.delete(bookingevent);

    }
}
