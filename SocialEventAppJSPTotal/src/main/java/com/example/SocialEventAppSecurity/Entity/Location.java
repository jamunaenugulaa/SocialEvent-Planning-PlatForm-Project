package com.example.SocialEventAppSecurity.Entity;

import com.example.SocialEventAppSecurity.Model.EventCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int LocationId;
    private String venueName;

    private String address;

    private String city;

    private String state;

    private String country;
    private int pincode;
    private String description;
    private int capacity;
    private double advance;

    private Double totalBudget;
    private int budgetPerGuest;
    @ManyToOne
    @JoinColumn(name="organizer_id")
    private EventOrganizer eventOrganizer;
    private List<EventCategory> categoryList=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    private List<BookingEvent> bookingEvents=new ArrayList<>();
    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int locationId) {
        LocationId = locationId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public int getBudgetPerGuest() {
        return budgetPerGuest;
    }

    public void setBudgetPerGuest(int budgetPerGuest) {
        this.budgetPerGuest = budgetPerGuest;
    }

    public List<EventCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<EventCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public List<BookingEvent> getBookingEvents() {
        return bookingEvents;
    }

    public EventOrganizer getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public void setBookingEvents(List<BookingEvent> bookingEvents) {
        this.bookingEvents = bookingEvents;
    }
}
