package com.waybill.sendout.controller;

import com.waybill.sendout.service.SendService;
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
public class SendController {

    @Autowired
    SendService sendService;

    @GetMapping("/sendout")
    public String sendOut(@RequestParam String waybillId){
        String result = "Ready for sending out</br>";
        result += sendService.sendOut(waybillId);
        return constructRespones(result);
    }

    @GetMapping("/callback")
    public String sendCallback(@RequestParam String waybillId,@RequestParam String status){
        String result = "Waybill ID:" + waybillId +"has send out</br>";
        result += sendService.sendCallback(waybillId,status);
        return constructRespones(result);
    }

    @GetMapping("/status")
    public String statusChang(@RequestParam String waybillId,@RequestParam String status){
        String result = sendService.statusChange(waybillId,status);
        return constructRespones(result);
    }

    public String constructRespones(String s){
        return "<<<<<<<<<<<<<<<<<<<</br>From IP:" + sendService.getLocalIP()  +"</br>"+ s;
    }
}
