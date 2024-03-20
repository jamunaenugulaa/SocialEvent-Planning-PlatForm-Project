package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.Payment;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;

public interface PayamentServiceInterface {
    Payment savePayment(Payment payment);

    Boolean checkLocationAvailableModel(BookingEventModel bookingEventModel);
}
