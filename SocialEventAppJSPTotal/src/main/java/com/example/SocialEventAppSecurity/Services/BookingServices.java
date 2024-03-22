package com.example.SocialEventAppSecurity.Services;


import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.*;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;
import com.example.SocialEventAppSecurity.Model.Status;
import com.example.SocialEventAppSecurity.Repository.*;
import com.example.SocialEventAppSecurity.ServiceInterface.BookingServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServices implements BookingServiceInterface {
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
    @Autowired
    private PayamentRepository payamentRepository;
    public BookingEventModel saveBooking(BookingEventModel booking) {
        BookingEvent bookingevent=modelToEntityConversion.modelToBookingEntity(booking);
        bookingRepository.save(bookingevent);
        return booking;
    }

    public BookingEventModel checkingTheHall(BookingEventModel bookingEventModel, Customer customer, Events events) {
        bookingEventModel.setCategory(events.getEventCategory());
        Location location = bookingEventModel.getLocation();
        List<BookingEvent> bookingEventList = location.getBookingEvents();
        if (!bookingEventList.isEmpty()) {
            boolean hasMatchingDate = bookingEventList.stream()
                    .anyMatch(bookingEvent ->
                            bookingEvent.getLocalDate().equals(bookingEventModel.getLocalDate())&&bookingEvent.isAdvancePaid());
            if(hasMatchingDate){
                return null;
            }
        }

        BookingEvent bookingEvent1=bookingVenueToCustomer(bookingEventModel,customer,events);
       return entityToModelConversion.entityToBookingModel(bookingEvent1);
    }

    private BookingEvent bookingVenueToCustomer(BookingEventModel bookingEventModel, Customer customer, Events events) {
        Location location = bookingEventModel.getLocation();
        List<BookingEvent> bookingEventList = location.getBookingEvents();
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
        return bookingEvent;
    }


    public List<BookingEventModel> getAllEventsOfCustomer(Customer customer) {
        List<BookingEvent> list=bookingRepository.findByCustomer(customer);
        LocalDate currentDate=LocalDate.now();
        list.forEach(e -> {
            if (e.getLocalDate().isBefore(currentDate)) {
                e.setStatus(Status.past);
            } else if (e.getLocalDate().isBefore(currentDate.plusDays(15))) {
                e.setStatus(Status.Ongoing);
            }
            if(isAvailableOfLocation(e.getLocation(),e.getLocalDate())&&!e.getStatus().equals(Status.past)&&!e.getLocalDate().equals(currentDate)){
                e.setLocationAvailable(true);
            }
            else{
                e.setLocationAvailable(false);

            }
            bookingRepository.save(e);
        });
//        list.removeIf(bookingEvent -> !bookingEvent.isAdvancePaid()&&bookingEvent.getStatus().equals(Status.past));
//        customer.setBookingEvents(list);
//        customerRepository.save(customer);
        List<BookingEvent> updatedList=list.stream()
                .sorted(Comparator.comparing(BookingEvent::getLocalDate))
                .toList();

        return updatedList.stream()
                .map(entityToModelConversion::entityToBookingModel)
                .collect(Collectors.toList());
    }

    private Boolean isAvailableOfLocation(Location location, LocalDate localDate) {
        return location.getBookingEvents().stream()
                .noneMatch(bookingEvent -> bookingEvent.getLocalDate().equals(localDate)&&bookingEvent.isAdvancePaid());
    }

    @Override
    public BookingEventModel getBookingEventById(int bookingId) {
        BookingEvent bookingEvent=bookingRepository.findById(bookingId).orElse(null);
        if(bookingEvent==null){
            return null;
        }
        return entityToModelConversion.entityToBookingModel(bookingEvent);
    }

    public void cancelBookingEventByCustomer(BookingEventModel bookingEventModel, Customer customer) {
        BookingEvent bookingEvent= modelToEntityConversion.modelToBookingEntity(bookingEventModel);
        if(bookingEvent.isAdvancePaid()){
            Payment payment=getPaymentOfBookingVenue(bookingEventModel);
            payment.setCancelled(true);
            bookingEvent.setAdvancePaid(false);
            bookingEvent.setCancelled(true);
            payment.setBookingEvent(bookingEvent);
            payamentRepository.save(payment);
            bookingRepository.save(bookingEvent);

        }
        else {
            List<BookingEvent> bookingEventList = customer.getBookingEvents();
            bookingEventList.removeIf(booking -> booking.getId() == bookingEvent.getId());
            customer.setBookingEvents(bookingEventList);
            customerRepository.save(customer);
            bookingEventList = bookingEvent.getLocation().getBookingEvents();
            bookingEventList.removeIf(booking -> booking.getId() == bookingEvent.getId());
            bookingEvent.getLocation().setBookingEvents(bookingEventList);
            locationRepository.save(bookingEvent.getLocation());
            bookingRepository.delete(bookingEvent);
        }
    }

    private Payment getPaymentOfBookingVenue(BookingEventModel bookingEventModel) {
        BookingEvent bookingEvent=modelToEntityConversion.modelToBookingEntity(bookingEventModel);
        return payamentRepository.findByBookingEvent(bookingEvent);
    }


}
