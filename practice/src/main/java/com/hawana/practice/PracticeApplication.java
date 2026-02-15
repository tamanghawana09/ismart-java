package com.hawana.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        //IOC container
        ApplicationContext context = SpringApplication.run(PracticeApplication.class, args);

        //get a bean or object managed by spring
        var orderService = context.getBean(OrderService.class);
        System.out.println("The System has started");

        //Constructor Injection
        // var orderService = new OrderService(new PaypalPaymentService());


       // var orderService = new OrderService();

        //Setter Injection
        //It is used for optional dependencies
//        orderService.setPaymentService(new PaypalPaymentService());
        orderService.placeOrder();
    }

}
