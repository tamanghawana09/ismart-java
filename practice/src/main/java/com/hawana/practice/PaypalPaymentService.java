package com.hawana.practice;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PaypalPaymentService  implements PaymentService{
    @Override
    public void processPayment(double amount){
        System.out.println("PAYPAL PAYMENT SERVICE");
        System.out.println("Amount: " + amount);
    }
}
