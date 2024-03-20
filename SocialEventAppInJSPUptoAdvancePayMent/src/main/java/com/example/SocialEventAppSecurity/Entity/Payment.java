package com.example.SocialEventAppSecurity.Entity;

import com.example.SocialEventAppSecurity.Model.OnlinePaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private Double advanceAmount;
    private OnlinePaymentType paymentType;
    @OneToOne
    @JoinColumn(name="booking_id")
    private BookingEvent bookingEvent;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public BookingEvent getBookingEvent() {
        return bookingEvent;
    }

    public void setBookingEvent(BookingEvent bookingEvent) {
        this.bookingEvent = bookingEvent;
    }

    public OnlinePaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(OnlinePaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
