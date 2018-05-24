package com.order.split.controller;

import com.order.split.service.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by this on 2018/5/8.
 */

@RestController
@RequestMapping("/order")
public class SplitController {

    @Autowired
    SplitService splitService;

    @GetMapping("/split")
    public String splitOrder(@RequestParam String orderId){
        String result = "order begins spliting:</br>";
        result += splitService.splitOrder(orderId);
        return constructRespones(result);
    }

    public String constructRespones(String s){
        return "<<<<<<<<<<<<<<<<<<<</br>From IP:" + splitService.getLocalIP()  +"</br>"+ s;
    }

}
