package com.message.Msg.service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    private String storedOtp; // Instance variable to store the generated OTP

    public void sendOtp(String recipientPhoneNumber) {
        storedOtp = generateOtp(); // Generate and store the OTP

        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                        new PhoneNumber(recipientPhoneNumber),
                        new PhoneNumber(twilioPhoneNumber),
                        "Your OTP is: " + storedOtp)
                .create();
        System.out.println("OTP sent successfully.");
    }

    private String generateOtp() {
        // Generate and return the OTP
        // You can use a library or your own logic to generate the OTP
        // For simplicity, let's assume a 6-digit numeric OTP
        int otpLength = 6;
        int minOtpValue = 100000;
        int maxOtpValue = 999999;
        int otp = minOtpValue + (int) (Math.random() * (maxOtpValue - minOtpValue + 1));
        return String.valueOf(otp);
    }

    public boolean verifyOtp(String receivedOtp) {
        return receivedOtp.equals(storedOtp); // Verify the received OTP with the stored OTP
    }
}
