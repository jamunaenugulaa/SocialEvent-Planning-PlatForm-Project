package com.example.SocialEventApp.Model;

import com.example.SocialEventApp.Entity.Location;
import com.example.SocialEventApp.Validation.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventsModel {
    private int eventId;
    @NotNull(message = "Event category cannot be null")
    private String eventCategory;

    @NotBlank(message = "Description cannot be blank")
    private String description;
    @Positive(message = "Budget must be positive")

    private double budget;
    private double advance;

    private List<Location> locationList=new ArrayList<>();


    public EventsModel() {
    }

    public EventsModel(int eventId,  String eventCategory, String description, double budget) {
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



    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
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
