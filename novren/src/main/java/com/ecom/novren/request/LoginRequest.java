package com.ecom.novren.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String otp;

}
