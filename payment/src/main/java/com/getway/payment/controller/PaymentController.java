package com.getway.payment.controller;

import com.getway.payment.service.PayPalService;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PayPalService paypalService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) {
        Payment createdPayment = paypalService.createPayment(payment);

        // Handle the created payment object and return an appropriate response
        // You can return the payment ID or redirect the user to a PayPal payment approval URL
        return ResponseEntity.ok("Payment created successfully");
    }

    @PostMapping("/execute/{paymentId}/{payerId}")
    public ResponseEntity<String> executePayment(
            @PathVariable("paymentId") String paymentId,
            @PathVariable("payerId") String payerId) {
        Payment executedPayment = paypalService.executePayment(paymentId, payerId);

        // Handle the executed payment object and return an appropriate response
        // You can return a success message or redirect the user to a success page
        return ResponseEntity.ok("Payment executed successfully");
    }
}

