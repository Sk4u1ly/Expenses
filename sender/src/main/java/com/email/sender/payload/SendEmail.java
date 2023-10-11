package com.email.sender.payload;

import lombok.Data;

@Data
public class SendEmail {
    private String fromEmail;
    private  String toEmail;
    private  String subject;
    private  String content;
}
