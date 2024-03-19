package com.example.SocialEventApp.Services;

import com.example.SocialEventApp.Entity.BookingEvent;
import com.example.SocialEventApp.Entity.Customer;
import com.example.SocialEventApp.Model.BookingEventModel;
import com.example.SocialEventApp.Model.CustomerModel;
import com.example.SocialEventApp.Repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomerServices {
    @Autowired
    private CustomerRepository customerRepository;



    public CustomerModel registerCustomer(CustomerModel customerModel) {
        //Customer customer=customerRepository.findByName(customerModel.getName());
        Customer customer=customerRepository.findByPhone(customerModel.getPhnNum());

        if(customer!=null){
            return null;
        }
        customer=new Customer();
        BeanUtils.copyProperties(customerModel,customer);
        customerRepository.save(customer);
        customerModel.setUserId(customer.getUserId());
        return customerModel;
    }

    public Customer getCustomer(int cid) {
        return customerRepository.findById(cid).get();
    }

    public void bookingEvents(BookingEvent booking, Customer customer) {
        List<BookingEvent> bookingEvents=customer.getBookingEvents();

        bookingEvents.add(booking);
        customer.setBookingEvents(bookingEvents);
        customerRepository.save(customer);
    }
    public  List<BookingEventModel> getAllBookings(int cid) {
        Customer customer=getCustomer(cid);
        System.out.println(customer);
        if(customer==null){
            return  null;
        }
        List<BookingEventModel> modelList=new ArrayList<>();
        List<BookingEvent> bookingEvents= customer.getBookingEvents();
        bookingEvents.forEach(bookingEvent -> {
            BookingEventModel model=new BookingEventModel();
            BeanUtils.copyProperties(bookingEvent,model);
            modelList.add(model);
        });
        return  modelList;
    }


    public void deleteBooking(BookingEvent bookingevent, int cid) {
        Customer customer=getCustomer(cid);
if(customer!=null){
    List<BookingEvent> bookingEvents=customer.getBookingEvents();
    bookingEvents.removeIf(bookingEvent ->bookingEvent.getId()==bookingEvent.getId() );
    customer.setBookingEvents(bookingEvents);
    customerRepository.save(customer);
}
    }

    public Boolean checkBookingDate(BookingEventModel booking, Customer customer) {
        List<BookingEvent> bookingEventList=customer.getBookingEvents();
        if(bookingEventList==null){
            System.out.println("false111");
            return false;
        }
        AtomicReference<Boolean> temp= new AtomicReference<>(true);
        bookingEventList.forEach(bookingEvent -> {
           if( bookingEvent.getLocalDate().equals(booking.getLocalDate())){
               temp.set(false);
           }
        });
        System.out.println(temp);
        if(temp.get()){
            return false;
        }
        return true;
    }
}
