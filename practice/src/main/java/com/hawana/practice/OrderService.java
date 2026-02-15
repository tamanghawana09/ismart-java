package com.hawana.practice;

import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class OrderService {

    private PaymentService paymentService;

    public OrderService(){}

    //Constructor Injection
    @Autowired
    public OrderService(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    public void placeOrder(){
        paymentService.processPayment(10);
    }
    //Setter Injection
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
