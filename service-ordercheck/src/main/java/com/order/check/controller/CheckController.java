package com.order.check.controller;

import com.order.check.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by this on 2018/5/8.
 */

@RestController
@RequestMapping("/order")
public class CheckController {

    @Value("${spring.application.name}")
    private String instanceName;

    private final DiscoveryClient discoveryClient;

    @Autowired
    public CheckController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Autowired
    private CheckService checkService;

    @GetMapping
    public String hello() {
        return "Hello,Zookeeper.";
    }

    @GetMapping("/services")
    public List<String> serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances(instanceName);
        List<String> services = new ArrayList<>();
        if (list != null && list.size() > 0 ) {
            list.forEach(serviceInstance -> {
                services.add(serviceInstance.getUri().toString());
            });
        }
        return services;
    }

    @GetMapping("/check")
    public String check(@RequestParam String orderId){
        String result = checkService.check(orderId);
        result = "this is order "+orderId+" check</br>" + result;
        return constructRespones(result);
    }

    public String constructRespones(String s){
        return "<<<<<<<<<<<<<<<<<<<</br>From IP:" + checkService.getLocalIP()  +"</br>"+ s;
    }

}
