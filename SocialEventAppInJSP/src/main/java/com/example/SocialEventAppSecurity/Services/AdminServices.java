package com.example.SocialEventAppSecurity.Services;

import com.example.SocialEventAppSecurity.Conversions.EntityToModelConversion;
import com.example.SocialEventAppSecurity.Conversions.ModelToEntityConversion;
import com.example.SocialEventAppSecurity.Entity.Admin;
import com.example.SocialEventAppSecurity.Model.AdminModel;
import com.example.SocialEventAppSecurity.Repository.AdminRepository;
import com.example.SocialEventAppSecurity.ServiceInterface.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServices implements AdminServiceInterface {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ModelToEntityConversion modelToEntityConversion;
    @Autowired
    private EntityToModelConversion entityToModelConversion;
    @Override
    public AdminModel registerAnAdmin(AdminModel adminModel) {
        Admin admin=adminRepository.findByUsername(adminModel.getUsername());
        System.out.println(admin);
        if(admin!=null){
            return null;
        }
        adminModel.setPassword(passwordEncoder.encode(adminModel.getPassword()));
        Admin admin1=modelToEntityConversion.modeltoAdminEntity(adminModel);
        adminRepository.save(admin1);
        adminModel=entityToModelConversion.entitytoAdminModel(admin1);
        return adminModel;



    }

    @Override
    public Admin findByUsernameAndPassowrd(String username, String password) {
        Admin admin=adminRepository.findByUsername(username);
        if(admin!=null&& passwordEncoder.matches(password,admin.getPassword())){
            return admin;
        }

        return null;
    }

    @Override
    public Admin findById(int adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }



}
