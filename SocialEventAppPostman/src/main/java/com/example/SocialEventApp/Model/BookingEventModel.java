package com.example.SocialEventApp.Model;

import com.example.SocialEventApp.Entity.Events;
import com.example.SocialEventApp.Entity.Location;
import com.example.SocialEventApp.Validation.FutureDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class BookingEventModel {
    private int id;
    boolean advancePaid;
    @NotBlank(message = "Event name cannot be null or empty.")
    private String eventName;

    @Positive(message = "Capacity must be Positive")
    private int capacity;

    @NotNull(message = "Local date cannot be null.")
    @FutureDate
    private LocalDate localDate;
    private Events event;
    private Location location;


    public BookingEventModel() {
    }

    public BookingEventModel(int id, String eventName, int capacity, LocalDate localDate, Events event) {
        this.id = id;
        this.advancePaid =false;
        this.eventName = eventName;
        this.capacity = capacity;
        this.localDate = localDate;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdvancePaid() {
        return advancePaid;
    }

    public void setAdvancePaid(boolean advancePaid) {
        this.advancePaid = advancePaid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "BookingEventModel{" +
                "id=" + id +
                ", advancePaid=" + advancePaid +
                ", eventName='" + eventName + '\'' +
                ", capacity=" + capacity +
                ", localDate=" + localDate +
                ", event=" + event +
                '}';
    }
}
