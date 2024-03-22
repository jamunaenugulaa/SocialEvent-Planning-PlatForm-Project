package com.example.SocialEventAppSecurity.Model;

import com.example.SocialEventAppSecurity.Entity.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventsModel {
    private int eventId;
    @NotBlank(message = "Event name cannot be blank")
    private String eventName;

    @NotNull(message = "Event category cannot be null")
    private EventCategory eventCategory;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Positive(message = "Budget must be positive")
    private double budget;


    private List<Location> locationList=new ArrayList<>();


    public EventsModel() {
    }

    public EventsModel(int eventId,  EventCategory eventCategory, String description, double budget) {
        this.eventId = eventId;
        this.eventCategory = eventCategory;
        this.description = description;
        this.budget = budget;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }



    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }



    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public String toString() {
        return "EventsModel{" +
                "eventId=" + eventId +
                ", eventCategory='" + eventCategory + '\'' +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                '}';
    }
}
