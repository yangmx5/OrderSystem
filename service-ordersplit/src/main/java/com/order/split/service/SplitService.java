package com.order.split.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by this on 2018/5/8.
 */
@Service
public class SplitService {

    @Autowired
    RestTemplate restTemplate;

    public String splitOrder(String orderId) {
        String result = "order " + orderId + " is spliting</br>";
        result += "Deducting the inventory</br>";
        List<String> waybillIds = new ArrayList<>();
        waybillIds.add("waybillId");
        for (String waybillId:waybillIds) {
            result += waybillPush(waybillId)+"</br>";
        }

        return result;
    }

    public String waybillPush(String waybillId) {
        return restTemplate.getForObject("http://waybillPush/waybill/push?waybillId=" + waybillId, String.class);
    }

    public String getIp(){
        try {
            InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            String hostAddress = address.getHostAddress();//192.168.0.121
            return hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "IP get erro";
        }
    }

    public String getLocalIP() {
        String ip = "";
        try {
            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0")) {
                    continue;
                } else {
                    Enumeration<?> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = (InetAddress) e2.nextElement();
                        if (ia instanceof Inet6Address)
                            continue;
                        ip = ia.getHostAddress();
                    }
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return ip;
    }

}
