package com.waybill.push.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by this on 2018/5/10.
 */
@Service
public class PushService {

    @Autowired
    RestTemplate restTemplate;


    public String waybillPush(String waybillId){
        String result = "Begin waybill pushing</br>";
        /*
        检查发货单货物Id库存是否足够
         */
        result += "checking storge is enough</br>";
        //进行
        return result;
    }

    public String waybillSendout(String waybillId){
        return restTemplate.getForObject("http://waybillSendout/waybill/sendout?waybillId=" + waybillId, String.class);
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
