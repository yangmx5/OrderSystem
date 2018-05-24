package com.waybill.sendout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by this on 2018/5/10.
 */
@Service
public class SendService {

    public String sendOut(String waybillId){
        String result = "Begin sending out</br>";
        result += "sending";
        return result;
    }

    public String statusChange(String waybillId,String status){
        String result = "Waybill "+waybillId +" status changed: " + status;
        return  result;
    }

    public String sendCallback(String waybillId,String status){
        String result = "Waybill "+waybillId +" send out: " + status;
        return  result;
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
