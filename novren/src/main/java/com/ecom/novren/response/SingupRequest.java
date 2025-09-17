package com.ecom.novren.response;

import lombok.Data;

@Data
public class SingupRequest {
    private String email;
    private String fullName;
    private String otp;
}
