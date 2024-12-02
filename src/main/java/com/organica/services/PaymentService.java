package com.organica.services;

import com.organica.payload.PaymentDetails;

public interface PaymentService {

    PaymentDetails CreateOrder(Double amount);

}
