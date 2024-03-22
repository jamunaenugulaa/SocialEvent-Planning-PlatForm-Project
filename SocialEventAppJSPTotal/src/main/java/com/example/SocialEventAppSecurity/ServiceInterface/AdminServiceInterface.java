package com.example.SocialEventAppSecurity.ServiceInterface;

import com.example.SocialEventAppSecurity.Entity.Admin;
import com.example.SocialEventAppSecurity.Model.AdminModel;

public interface AdminServiceInterface {
    AdminModel registerAnAdmin(AdminModel adminModel);

    Admin findByUsernameAndPassword(String username, String password);

    Admin findById(int adminId);
}
