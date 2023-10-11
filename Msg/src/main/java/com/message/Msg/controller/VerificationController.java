package com.message.Msg.controller;

import com.message.Msg.Payload.PhoneNumberRequest;
import com.message.Msg.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    private final TwilioService twilioService;

    @Autowired
    public VerificationController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody PhoneNumberRequest request) {
        String recipientPhoneNumber = request.getPhoneNumber();
        twilioService.sendOtp(recipientPhoneNumber);
        return "OTP sent successfully.";
    }
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody PhoneNumberRequest request) {
        boolean otpVerified = twilioService.verifyOtp(request.getReceivedOtp());
        if (otpVerified) {
            return "OTP verification successful.";
        } else {
            return "OTP verification failed.";
        }
    }
}
