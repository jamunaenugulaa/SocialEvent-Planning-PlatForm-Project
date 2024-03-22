package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Model.CustomerModel;

public interface CustomerServiceInterface {
    public CustomerModel registerCustomer(CustomerModel customerModel) ;
    public Customer findByUsernameAndPassowrd(String username, String password) ;
    public Customer findById(int cid) ;

    Customer viewUserBookingEvent();
}
