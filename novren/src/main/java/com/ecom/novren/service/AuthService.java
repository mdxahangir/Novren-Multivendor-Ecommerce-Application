package com.ecom.novren.service;


import com.ecom.novren.enums.USER_ROLE;
import com.ecom.novren.request.LoginRequest;
import com.ecom.novren.response.AuthResponse;
import com.ecom.novren.response.SingupRequest;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SingupRequest req) throws Exception;

    AuthResponse signing(LoginRequest req) throws Exception;

}
