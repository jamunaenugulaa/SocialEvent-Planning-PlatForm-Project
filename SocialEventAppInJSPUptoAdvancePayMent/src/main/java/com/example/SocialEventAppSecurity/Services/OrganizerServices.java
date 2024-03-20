package com.example.SocialEventAppSecurity.Services;



import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;
import com.example.SocialEventAppSecurity.Model.EventOrganizerModel;
import com.example.SocialEventAppSecurity.Repository.BookingRepository;
import com.example.SocialEventAppSecurity.Repository.OrganizerRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.OrganizerServicesInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizerServices implements OrganizerServicesInterface {
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BookingServices bookingServices;
    @Autowired
    private ModelToEntityConversion modelToEntityConversion;
    @Autowired
    private EntityToModelConversion entityToModelConversion;
    public EventOrganizerModel saveOrganizer(EventOrganizerModel organizerModel) {
        EventOrganizer organizer=organizerRepository.findById(organizerModel.getId()).orElse(null);
        if(organizer==null){
            organizerModel.setUsername(organizerModel.getName());
            organizerModel.setPassword("12345");
            EventOrganizer eventOrganizer=modelToEntityConversion.modelToOrganizerEntity(organizerModel);
                organizerRepository.save(eventOrganizer);
                return organizerModel;
        }
        return null;
    }

    public EventOrganizer fibdByUsernameAndPassword(String username, String password) {
        EventOrganizer eventOrganizer= organizerRepository.findByUsername(username);
        if(eventOrganizer!=null&&passwordEncoder.matches(password,eventOrganizer.getPassword())){
            return eventOrganizer;
        }
        return null;
    }

    public EventOrganizerModel getOrganizerById(int oId) {
        EventOrganizer organizer=organizerRepository.findById(oId).orElse(null);
        EventOrganizerModel eventOrganizerModel=entityToModelConversion.entityToOrganizerModel(organizer);
        return eventOrganizerModel;
    }

    public List<BookingEventModel> upcomingBookings() {
        List<BookingEvent> bookingEvents=bookingRepository.findAll();
        LocalDate currentDate=LocalDate.now();
        List<BookingEvent> upcomingEvents=bookingEvents.stream()
                .filter(bookingEvent -> bookingEvent.getLocalDate().isAfter(currentDate) || bookingEvent.getLocalDate().isEqual(currentDate))
                .collect(Collectors.toList());
        List<BookingEventModel> bookingEventModels=new ArrayList<>();
        upcomingEvents.forEach(bookingEvent -> {
            System.out.println(bookingEvent.getLocalDate());
        });
      upcomingEvents.forEach(events-> {
          BookingEventModel bookingEventModel=entityToModelConversion.entitytoBookingModel(events);
          bookingEventModels.add(bookingEventModel);
        });
      return bookingEventModels;
    }

    public List<BookingEventModel> pastBooking() {
        List<BookingEvent> bookingEvents=bookingRepository.findAll();
        LocalDate currentDate=LocalDate.now();
        List<BookingEvent> upcomingEvents=bookingEvents.stream()
                .filter(bookingEvent -> bookingEvent.getLocalDate().isBefore(currentDate) )
                .collect(Collectors.toList());
        List<BookingEventModel> bookingEventModels=new ArrayList<>();
        upcomingEvents.forEach(bookingEvent -> {
            System.out.println(bookingEvent.getLocalDate());
        });
        upcomingEvents.forEach(events-> {
            BookingEventModel bookingEventModel=entityToModelConversion.entitytoBookingModel(events);
            bookingEventModels.add(bookingEventModel);
        });
        return bookingEventModels;
    }




    @Override
    public List<EventOrganizerModel> getAllOrganizers() {
       List<EventOrganizer> eventOrganizers=organizerRepository.findAll();
        List<EventOrganizerModel> eventOrganizerModels=new ArrayList<>();
       eventOrganizers.forEach(organizer->{
           EventOrganizerModel eventOrganizerModel=entityToModelConversion.entityToOrganizerModel(organizer);
           eventOrganizerModels.add(eventOrganizerModel);
       });
       return eventOrganizerModels;
    }

    @Override
    public Boolean deleteOrganizer(int id) {
        EventOrganizer eventOrganizer=organizerRepository.findById(id).orElse(null);
        if(eventOrganizer==null){
            return false;
        }

         organizerRepository.deleteById(id);
                    return true;
    }


}
