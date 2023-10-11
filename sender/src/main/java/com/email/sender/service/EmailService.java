package com.email.sender.service;


import com.email.sender.payload.SendEmail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {


    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public void sendEmail(SendEmail sendEmail) throws IOException {
        Email from = new Email(sendEmail.getFromEmail());
        Email to = new Email(sendEmail.getToEmail());
        Content emailContent = new Content("text/plain", sendEmail.getContent());
        Mail mail = new Mail(from, sendEmail.getSubject(), to, emailContent);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            if (response.getStatusCode() == 202) {
                System.out.println("Email sent successfully!");
            } else {
                System.out.println("Failed to send email. Status code: " + response.getStatusCode());
            }
        } catch (IOException ex) {
            System.out.println("Error sending email: " + ex.getMessage());
        }
    }

}
