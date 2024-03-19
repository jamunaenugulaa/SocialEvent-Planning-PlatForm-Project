package com.example.SocialEventApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    boolean advancePaid;
    private String eventName;
    private int capacity;
    private LocalDate localDate;
@JsonIgnore
    @ManyToOne()
    @JoinColumn(name="location_id")
    private Location location;
@JsonIgnore
    @ManyToOne
    @JoinColumn(name="event_id")
    private Events event;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
