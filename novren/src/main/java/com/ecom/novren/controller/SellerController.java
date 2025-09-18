package com.ecom.novren.controller;

import com.ecom.novren.model.Seller;
import com.ecom.novren.repository.VerificationCodeRepository;
import com.ecom.novren.request.LoginRequest;
import com.ecom.novren.response.AuthResponse;
import com.ecom.novren.service.AuthService;
import com.ecom.novren.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;


//    @PostMapping("/sent/login-otp")
//    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody VerificationCode req) throws Exception {
//
//        authService.sentLoginOtp(req.getEmail());
//
//        ApiResponse res=new ApiResponse();
//
//        res.setMessage("otp sent successfully");
//
//        return ResponseEntity.ok(res);
//    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> sentLoginOtp(
            @RequestBody LoginRequest req
            ) throws Exception {
        String otp=req.getOtp();
        String email=req.getEmail();
        req.setEmail("seller_"+email);

//        VerificationCode verificationCode=verificationCodeRepository.findByEmail(email);
//        if (verificationCode==null || !verificationCode.getOtp().equals(req.getOtp())){
//            throw new Exception("wrong otp..");
//        }
        AuthResponse authResponse=authService.signing(req);
        return ResponseEntity.ok(authResponse);
//return null;

//        AuthResponse authResponse=authService.signing(req);
//        return ResponseEntity.ok(authResponse);
    }
//    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Seller
}
