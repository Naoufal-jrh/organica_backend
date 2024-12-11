package com.organica.controllers;

import com.organica.payload.PaymentDetails;
import com.organica.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;


    private PaymentDetails paymentDetails;

    @BeforeEach
    void SetUp(){
        paymentDetails = new PaymentDetails();
        paymentDetails.setOrderId("ORD12345");
    }

    @Test
    void testCreatePayment(){
        when(paymentService.CreateOrder(100.0)).thenReturn(paymentDetails);

        ResponseEntity<PaymentDetails> response = paymentController.CreatePayment(100.0);

        assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(paymentDetails,response.getBody());
        assertEquals("ORD12345", response.getBody().getOrderId());
    }

}
