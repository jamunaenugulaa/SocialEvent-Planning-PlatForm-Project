package com.example.SocialEventAppSecurity.Services;


import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Model.CustomerModel;
import com.example.SocialEventAppSecurity.Repository.CustomerRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.CustomerServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices implements CustomerServiceInterface {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelToEntityConversion modelToEntityConversion;
    @Autowired
    private EntityToModelConversion entityToModelConversion;
    public class DuplicateCustomerException extends RuntimeException {
        public DuplicateCustomerException(String message) {
            super(message);
        }
    }
    public CustomerModel registerCustomer(CustomerModel customerModel) {
        try {
            Customer customer2 = customerRepository.findByPhone(customerModel.getPhnNum());
            Customer customer = customerRepository.findByUsername(customerModel.getUsername());


            if (customer != null || customer2 != null) {
                return null;
            }
            customer = new Customer();
            customer = modelToEntityConversion.modelToCustomerEntity(customerModel);
            customerRepository.save(customer);
            customerModel = entityToModelConversion.entityToCustomerModel(customer);
            return customerModel;
        }
        catch (Exception ex) {
            throw new DuplicateCustomerException("Multiple customers found with the same phone number");
        }
    }

    public Customer findByUsernameAndPassowrd(String username, String password) {
        Customer customer= customerRepository.findByUsername(username);
        if(customer!=null&&passwordEncoder.matches(password,customer.getPassword())){
            return customer;
        }
return null;
    }

    public Customer findById(int cid) {
        return customerRepository.findById(cid).orElse(null);
    }



}
