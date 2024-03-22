package com.example.SocialEventAppSecurity.Conversions;

import com.example.SocialEventAppSecurity.Entity.*;
import com.example.SocialEventAppSecurity.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ModelToEntityConversion {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Admin modelToAdminEntity(AdminModel admin) {

        Admin adminEntity = new Admin();
        adminEntity.setAdminName(admin.getAdminName());
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setRole("ROLE_ADMIN");
        adminEntity.setUsername(admin.getUsername());
        adminEntity.setPassword(admin.getPassword());
        return adminEntity;
    }
    public Events modelToEventsEntity(EventsModel model) {
        Events entity = new Events();
        entity.setEventId(model.getEventId());
        entity.setEventName(model.getEventName());
        entity.setEventCategory(model.getEventCategory());
        entity.setDescription(model.getDescription());
        entity.setBudget(model.getBudget());
        return entity;
    }
    public BookingEvent modelToBookingEntity(BookingEventModel model) {
        BookingEvent entity = new BookingEvent();
        entity.setId(model.getId());
        entity.setAdvancePaid(model.isAdvancePaid());
        entity.setCategory(model.getCategory());
        entity.setEventName(model.getEventName());
        entity.setCapacity(model.getCapacity());
        entity.setLocalDate(model.getLocalDate());
        entity.setTotalBudget(model.getTotalBudget());
        entity.setStatus(model.getStatus());
        entity.setAdvanceAmount(model.getAdvanceAmount());
        entity.setLocation(model.getLocation());
        entity.setCustomer(model.getCustomer());
        entity.setLocationAvailable(model.isLocationAvailable());
        entity.setCancelled(model.getCancelled());
        return entity;
    }
    public Customer modelToCustomerEntity(CustomerModel customerModel) {
        Customer entity = new Customer();
        entity.setName(customerModel.getName());
        entity.setAge(customerModel.getAge());
        entity.setPhone(customerModel.getPhone());
        entity.setEmail(customerModel.getEmail());
        entity.setUsername(customerModel.getUsername());
        entity.setPassword(passwordEncoder.encode(customerModel.getPassword()));
        entity.setRole("ROLE_CUSTOMER");
        entity.setBookingEvents(customerModel.getBookingEvents());
        return entity;
    }
    public Location modelToLocationEntity(LocationModel model) {
        Location entity = new Location();
        entity.setLocationId(model.getLocationId());
        entity.setVenueName(model.getVenueName());
        entity.setAddress(model.getAddress());
        entity.setCity(model.getCity());
        entity.setState(model.getState());
        entity.setCountry(model.getCountry());
        entity.setPincode(model.getPincode());
        entity.setDescription(model.getDescription());
        entity.setCapacity(model.getCapacity());
        entity.setAdvance(model.getAdvance());
        entity.setTotalBudget(model.getTotalBudget());
        entity.setBudgetPerGuest(model.getBudgetPerGuest());
        entity.setBookingEvents(model.getBookingEvents());
        entity.setCategoryList(model.getCategoryList());
        entity.setEventOrganizer(model.getEventOrganizer());
        return entity;
    }
    public EventOrganizer  modelToOrganizerEntity(EventOrganizerModel organizer) {
        EventOrganizer entity = new EventOrganizer();
        entity.setId(organizer.getId());
        entity.setName(organizer.getName());
        entity.setAge(organizer.getAge());
        entity.setPhnNum(organizer.getPhnNum());
        entity.setEmail(organizer.getEmail());
        entity.setRole("ROLE_ORGANIZER");
        entity.setUsername(organizer.getUsername());
        entity.setPassword(passwordEncoder.encode(organizer.getPassword()));
        entity.setLocationList(organizer.getLocationList());
        return entity;
    }
}
