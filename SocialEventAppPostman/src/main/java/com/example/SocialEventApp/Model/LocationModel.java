package com.example.SocialEventApp.Model;

import com.example.SocialEventApp.Entity.Events;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationModel {
    private int locationId;

    @NotBlank(message = "Venue name cannot be blank")
    private String venueName;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @Positive(message = "Pincode must be positive")
    private int pincode;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Positive(message = "Budget must be positive")
    private Double totalBudget;
    @Positive(message = "Budget must be positive")
    private int budgetPerGuest;
    @Positive(message = "Capacity must be positive")

    private int capacity;
    private List<Events> eventsList=new ArrayList<>();

    public LocationModel() {
    }

    public LocationModel(int locationId, String venueName, String address, String city, String state, String country, int pincode, String description, Double totalBudget, int budgetPerGuest, int capacity) {
        this.locationId = locationId;
        this.venueName = venueName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.description = description;
        this.totalBudget = totalBudget;
        this.budgetPerGuest = budgetPerGuest;
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Events> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Events> eventsList) {
        this.eventsList = eventsList;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "locationId=" + locationId +
                ", venueName='" + venueName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode=" + pincode +
                ", description='" + description + '\'' +
                ", totalBudget=" + totalBudget +
                ", budgetPerGuest=" + budgetPerGuest +
                ", capacity=" + capacity +
                '}';
    }
}
