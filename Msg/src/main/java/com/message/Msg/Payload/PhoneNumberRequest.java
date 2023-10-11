package com.message.Msg.Payload;

import lombok.Data;

@Data
public class PhoneNumberRequest {
    private String phoneNumber;
    private String receivedOtp;


}
