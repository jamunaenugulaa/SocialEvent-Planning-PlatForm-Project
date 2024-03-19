package com.example.SocialEventAppSecurity.Entity;

import com.example.SocialEventAppSecurity.Model.EventCategory;
import com.example.SocialEventAppSecurity.Model.Status;
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
    private EventCategory category;
    private String eventName;
    private int capacity;
    private LocalDate localDate;
    private Double totalBudget;
    private Status status;
    private double advanceAmount;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="location_id")
    private Location location;
@ManyToOne
@JoinColumn(name="Customer_Id")
private Customer customer;
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


    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
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

    public Customer getCustomer() {
        return customer;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
