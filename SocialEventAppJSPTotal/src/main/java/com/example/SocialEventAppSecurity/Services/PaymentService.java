package com.example.SocialEventAppSecurity.Services;

import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import com.example.SocialEventAppSecurity.Entity.Location;
import com.example.SocialEventAppSecurity.Entity.Payment;
import com.example.SocialEventAppSecurity.Model.BookingEventModel;
import com.example.SocialEventAppSecurity.Repository.PayamentRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.PayamentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements PayamentServiceInterface {
    @Autowired
    private PayamentRepository payamentRepository;
    @Autowired
    private ModelToEntityConversion modelToEntityConversion;
    @Autowired
    private EntityToModelConversion entityToModelConversion;
    public Payment savePayment(Payment payment) {
        BookingEvent bookingEvent= payment.getBookingEvent();

        payment.setAdvanceAmount(bookingEvent.getAdvanceAmount());
        bookingEvent.setAdvancePaid(true);
        payment.setBookingEvent(bookingEvent);
        payment.setCustomer(bookingEvent.getCustomer());
        return payamentRepository.save(payment);

    }

    @Override
    public Boolean checkLocationAvailableModel(BookingEventModel bookingEventModel) {
        BookingEvent event=modelToEntityConversion.modelToBookingEntity(bookingEventModel);
        return checkLocationAvailable(event);
    }

    private Boolean checkLocationAvailable(BookingEvent bookingEvent) {
        Location location= bookingEvent.getLocation();
        List<BookingEvent> bookingEventList=location.getBookingEvents();
        return bookingEventList.stream().noneMatch(bookingEvent1 ->
            bookingEvent1.getLocalDate().equals(bookingEvent.getLocalDate())&&bookingEvent1.isAdvancePaid());

    }
}
