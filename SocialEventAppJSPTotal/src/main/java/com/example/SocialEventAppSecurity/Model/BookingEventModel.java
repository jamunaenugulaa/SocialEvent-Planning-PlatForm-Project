package com.example.SocialEventAppSecurity.Model;



import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Validation.FutureDate;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class BookingEventModel {
    private int id;
    boolean advancePaid;
    @NotBlank(message = "Event name cannot be null or empty.")
    private String eventName;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;
    private boolean isCancelled;

    @NotNull(message = "Local date cannot be null.")
    @FutureDate
    private LocalDate localDate;
    private EventCategory category;
    private Double totalBudget;

    private Location location;
    private boolean isLocationAvailable;


    private Customer customer;
    private Status status;
    private double advanceAmount;

    public BookingEventModel() {
    }

    public BookingEventModel(int id, String eventName, int capacity, LocalDate localDate) {
        this.id = id;
        this.advancePaid =false;
        this.eventName = eventName;
        this.capacity = capacity;
        this.localDate = localDate;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public boolean isLocationAvailable() {
        return isLocationAvailable;
    }

    public void setLocationAvailable(boolean locationAvailable) {
        isLocationAvailable = locationAvailable;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    @Override
    public String toString() {
        return "BookingEventModel{" +
                "id=" + id +
                ", advancePaid=" + advancePaid +
                ", eventName='" + eventName + '\'' +
                ", capacity=" + capacity +
                ", localDate=" + localDate +
                ", category=" + category +
                ", location=" + location +
                ", customer=" + customer +
                '}';
    }
}
