package com.example.SocialEventAppSecurity.Model;

import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Component;

@Component
public class PaymentModel {
    private int paymentId;
    private Double advanceAmount;
    private OnlinePaymentType paymentType;

    private BookingEvent bookingEvent;

    public PaymentModel() {
    }

    public PaymentModel(int paymentId, Double advanceAmount, BookingEvent bookingEvent) {
        this.paymentId = paymentId;
        this.advanceAmount = advanceAmount;
        this.bookingEvent = bookingEvent;
    }

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

    @Override
    public String toString() {
        return "PaymentModel{" +
                "paymentId=" + paymentId +
                ", advanceAmount=" + advanceAmount +
                ", paymentType=" + paymentType +
                ", bookingEvent=" + bookingEvent.getId() +
                '}';
    }
}
