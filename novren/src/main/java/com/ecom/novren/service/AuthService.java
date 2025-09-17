package com.ecom.novren.service;


import com.ecom.novren.response.SingupRequest;

public interface AuthService {

    void sentLoginOtp(String email) throws Exception;
    String createUser(SingupRequest req) throws Exception;

}
