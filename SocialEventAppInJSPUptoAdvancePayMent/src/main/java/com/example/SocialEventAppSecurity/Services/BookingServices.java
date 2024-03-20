package com.example.SocialEventAppSecurity.Services;


import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Entity.Events;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;
import com.example.SocialEventAppSecurity.Model.Status;
import com.example.SocialEventAppSecurity.Repository.BookingRepository;
import com.example.SocialEventAppSecurity.Repository.CustomerRepository;
import com.example.SocialEventAppSecurity.Repository.EventRepository;
import com.example.SocialEventAppSecurity.Repository.LocationRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.BookingServiceInterfcae;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServices implements BookingServiceInterfcae {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ModelToEntityConversion modelToEntityConversion;
    @Autowired
    private EntityToModelConversion entityToModelConversion;
    public BookingEventModel saveBooking(BookingEventModel booking) {
        BookingEvent bookingevent=modelToEntityConversion.modelToBookingEntity(booking);
        bookingRepository.save(bookingevent);
        return booking;
    }

    public BookingEventModel BookTheHall(BookingEventModel bookingEventModel, Customer customer, Events events) {
        bookingEventModel.setCategory(events.getEventCategory());
        Location location = bookingEventModel.getLocation();
        List<BookingEvent> bookingEventList = location.getBookingEvents();
        if (!bookingEventList.isEmpty()) {
            boolean hasMatchingDate = bookingEventList.stream()
                    .anyMatch(bookingEvent ->
                            bookingEvent.getLocalDate().equals(bookingEventModel.getLocalDate())&&bookingEvent.isAdvancePaid());

            System.out.println(hasMatchingDate);
            if(hasMatchingDate){
                return null;
            }
        }
        bookingEventModel.setCustomer(customer);
        bookingEventModel.setAdvanceAmount(events.getBudget()+ location.getAdvance());
        Double amount=(location.getBudgetPerGuest()*bookingEventModel.getCapacity()*1.0);
        bookingEventModel.setTotalBudget(amount);
        LocalDate currentDate=LocalDate.now();
        if(bookingEventModel.getLocalDate().isAfter(currentDate.plusDays(15))){
            bookingEventModel.setStatus(Status.Upcoming);
        }
        else{
            bookingEventModel.setStatus(Status.Ongoing);
        }
        BookingEvent bookingEvent= modelToEntityConversion.modelToBookingEntity(bookingEventModel);
        List<BookingEvent> bookingEvents=customer.getBookingEvents();
        bookingEventList.add(bookingEvent);
        customer.setBookingEvents(bookingEvents);
        customerRepository.save(customer);
       return entityToModelConversion.entitytoBookingModel(bookingEvent);
    }

    public List<BookingEventModel> getAllEventsOfCustomer(Customer customer) {
        List<BookingEvent> list=bookingRepository.findByCustomer(customer);
        LocalDate currentDate=LocalDate.now();
        list.forEach(e->{
            if(e.getLocalDate().isBefore(currentDate)){
                e.setStatus(Status.past);
                bookingRepository.save(e);
            }
            else if(e.getLocalDate().isAfter(currentDate)||e.getLocalDate().equals(currentDate)){
                if(e.getLocalDate().isBefore(currentDate.plusDays(15))){
                    e.setStatus(Status.Ongoing);
                    bookingRepository.save(e);
                }

            }
        });
        List<BookingEvent> updatedList=list.stream()
                .sorted(Comparator.comparing(BookingEvent::getLocalDate))
                .toList();
        List<BookingEventModel> modelList=new ArrayList<>();
        updatedList.forEach(bookingEvent -> {
             BookingEventModel bookingEventModel=entityToModelConversion.entitytoBookingModel(bookingEvent);
             modelList.add(bookingEventModel);
        });
        return modelList;
    }

    @Override
    public BookingEventModel getBookingEventById(int bookingId) {
        BookingEvent bookingEvent=bookingRepository.findById(bookingId).orElse(null);
        if(bookingEvent==null){
            return null;
        }
        return entityToModelConversion.entitytoBookingModel(bookingEvent);
    }


}
