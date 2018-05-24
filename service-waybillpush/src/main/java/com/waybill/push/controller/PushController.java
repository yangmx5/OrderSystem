package com.waybill.push.controller;

import com.waybill.push.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by this on 2018/5/10.
 */
@RestController
@RequestMapping("/waybill")
public class PushController {

    @Autowired
    PushService pushService;

    @GetMapping("/push")
    public String waybillPush(@RequestParam String waybillId){
        String result = "Ready for waybill push</br>";
        result += pushService.waybillPush(waybillId) +"</br>";
        result += pushService.waybillSendout(waybillId) + "</br>";
        return constructRespones(result);
    }

    public String constructRespones(String s){
        return "<<<<<<<<<<<<<<<<<<<</br>From IP:" + pushService.getLocalIP()  +"</br>"+ s;
    }

}
