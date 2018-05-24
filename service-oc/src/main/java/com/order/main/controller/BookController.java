package com.order.main.controller;

import com.order.main.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this on 2018/5/9.
 */
@RestController
@RequestMapping("/order")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public String book(@RequestParam String id) {
        return "this is place an order status:" + id + "</br>" + bookService.orderCheck(id);
    }

    @GetMapping("/book")
    public String bookTest(@RequestParam String id) {
        String result = "this is place an order status:";
        result += id + "</br>";
        result += bookService.orderCheck(id);
        return  constructRespones(result);
    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam String orderId) {
        return "cancel " + orderId + " success";
    }

    @GetMapping("/query")
    public String query(@RequestParam String orderId) {
        return "the " + orderId + "details is";
    }

    private final DiscoveryClient discoveryClient;

    @Autowired
    public BookController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    public String hello() {
        return "Hello,this is order center.";
    }

    @GetMapping("/services")
    public List<String> serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("oc");
        list.addAll(discoveryClient.getInstances("orderCheck"));
        list.addAll(discoveryClient.getInstances("orderSplit"));
        list.addAll(discoveryClient.getInstances("waybillPush"));
        list.addAll(discoveryClient.getInstances("waybillSendout"));
        List<String> services = new ArrayList<>();
        if (list != null && list.size() > 0) {
            list.forEach(serviceInstance -> {
                services.add(serviceInstance.getUri().toString() + "<>" + serviceInstance.getServiceId() +"<>"+serviceInstance.getHost());
            });
        }
        return services;
    }

    @GetMapping("/test")
    public String test() {
        return "test the orderCheck service" + bookService.test();
    }

    @GetMapping("/test2")
    public String test2() {
        return "test the orderCheck service : </br>" + bookService.getIp() +"</br>" +bookService.getLocalIP();
    }

    public String constructRespones(String s){
        return "<<<<<<<<<<<<<<<<<<<</br>From IP:" + bookService.getLocalIP() +"</br>"+ s;
    }

}
