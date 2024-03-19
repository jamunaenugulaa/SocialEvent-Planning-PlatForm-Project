package com.example.SocialEventAppSecurity.Conversions;

import com.example.SocialEventAppSecurity.Entity.*;
import com.example.SocialEventAppSecurity.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EntityToModelConversion {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AdminModel entitytoAdminModel(Admin adminEntity) {
        AdminModel admin = new AdminModel();
        admin.setAdminName(adminEntity.getAdminName());
        admin.setEmail(adminEntity.getEmail());
        admin.setRole(adminEntity.getRole());
        admin.setUsername(adminEntity.getUsername());
        admin.setPassword(adminEntity.getPassword());
        return admin;
    }
    public EventsModel entitytoEventsModel(Events entity) {
        EventsModel model = new EventsModel();
        model.setEventId(entity.getEventId());
        model.setEventName(entity.getEventName());
        model.setEventCategory(entity.getEventCategory());
        model.setDescription(entity.getDescription());
        model.setBudget(entity.getBudget());
        return model;
    }
    public BookingEventModel entitytoBookingModel(BookingEvent entity) {
        BookingEventModel model = new BookingEventModel();
        model.setId(entity.getId());
        model.setAdvancePaid(entity.isAdvancePaid());
        model.setCategory(entity.getCategory());
        model.setEventName(entity.getEventName());
        model.setCapacity(entity.getCapacity());
        model.setLocalDate(entity.getLocalDate());
        model.setTotalBudget(entity.getTotalBudget());
        model.setStatus(entity.getStatus());
        model.setAdvanceAmount(entity.getAdvanceAmount());
        model.setCustomer(entity.getCustomer());
        model.setLocation(entity.getLocation());
        return model;
    }
    public CustomerModel entityToCustomerModel(Customer customerModel) {
        CustomerModel entity = new CustomerModel();
        entity.setName(customerModel.getName());
        entity.setAge(customerModel.getAge());
        entity.setPhone(customerModel.getPhone());
        entity.setEmail(customerModel.getEmail());
        entity.setUsername(customerModel.getUsername());
        entity.setPassword(customerModel.getPassword());
        entity.setRole(customerModel.getRole());
        entity.setBookingEvents(customerModel.getBookingEvents());
        return entity;
    }
    public LocationModel entityToLocationModel(Location entity) {
        LocationModel model = new LocationModel();
        model.setLocationId(entity.getLocationId());
        model.setVenueName(entity.getVenueName());
        model.setAddress(entity.getAddress());
        model.setCity(entity.getCity());
        model.setState(entity.getState());
        model.setCountry(entity.getCountry());
        model.setPincode(entity.getPincode());
        model.setDescription(entity.getDescription());
        model.setCapacity(entity.getCapacity());
        model.setAdvance(entity.getAdvance());
        model.setTotalBudget(entity.getTotalBudget());
        model.setBudgetPerGuest(entity.getBudgetPerGuest());
        model.setBookingEvents(entity.getBookingEvents());
        model.setCategoryList(entity.getCategoryList());
        return model;
    }
    public EventOrganizerModel entityToOrganizerModel(EventOrganizer entity) {
        EventOrganizerModel organizer = new EventOrganizerModel();
        organizer.setId(entity.getId());
        organizer.setName(entity.getName());
        organizer.setAge(entity.getAge());
        organizer.setPhnNum(entity.getPhnNum());
        organizer.setEmail(entity.getEmail());
        organizer.setRole(entity.getRole());
        organizer.setUsername(entity.getUsername());
        organizer.setPassword(entity.getPassword());
        return organizer;
    }
}
