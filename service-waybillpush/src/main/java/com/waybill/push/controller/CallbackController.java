package com.waybill.push.controller;

import com.waybill.push.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by this on 2018/5/10.
 */
@RestController
@RequestMapping("/callback")
public class CallbackController {

    @Autowired
    PushService pushService;

    @GetMapping("/storge")
    public String storgeCallback(@RequestParam String status,@RequestParam String waybillId){
        if(status.equals("true")){
            return pushService.waybillSendout(waybillId);
        }
        return "storge not enough";
    }
}
