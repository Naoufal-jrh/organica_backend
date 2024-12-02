package com.organica.controllers;


import com.organica.payload.PaymentDetails;
import com.organica.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{amount}")
    public ResponseEntity<PaymentDetails> CreatePayment(@PathVariable Double amount){
        PaymentDetails paymentDetails = this.paymentService.CreateOrder(amount);
        return new ResponseEntity<>(paymentDetails, HttpStatusCode.valueOf(200));
    }

}
