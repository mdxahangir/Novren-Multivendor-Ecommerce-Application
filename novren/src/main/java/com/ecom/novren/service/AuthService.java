package com.ecom.novren.service;


import com.ecom.novren.response.SingupRequest;

public interface AuthService {
    String createUser(SingupRequest req);

}
