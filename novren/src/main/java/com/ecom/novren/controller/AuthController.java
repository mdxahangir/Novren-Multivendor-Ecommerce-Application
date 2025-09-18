package com.ecom.novren.controller;


import com.ecom.novren.enums.USER_ROLE;
import com.ecom.novren.model.VerificationCode;
import com.ecom.novren.request.LoginOtpRequest;
import com.ecom.novren.request.LoginRequest;
import com.ecom.novren.response.ApiResponse;
import com.ecom.novren.response.AuthResponse;
import com.ecom.novren.response.SingupRequest;
import com.ecom.novren.repository.UserRepository;
import com.ecom.novren.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) throws Exception {
        // Generate OTP and validate email
        authService.sentLoginOtp(req.getEmail(), USER_ROLE.ROLE_CUSTOMER); // or ROLE_SELLER

        // OTP verify + JWT generate
        req.setEmail("seller_" + req.getEmail()); // if seller
        AuthResponse authResponse = authService.signing(req);

        return ResponseEntity.ok(authResponse);
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SingupRequest req) throws Exception {
        String jwt=authService.createUser(req);
        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sentLoginOtp(req.getEmail(),req.getRole());

        ApiResponse res=new ApiResponse();

        res.setMessage("otp sent successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest req) throws Exception {
        authService.signing(req);

        AuthResponse authResponse= authService.signing(req);

        return ResponseEntity.ok(authResponse);
    }
}
