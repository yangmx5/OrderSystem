package com.order.check.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by this on 2018/5/8.
 */
@Service
public class CheckService {

    @Autowired
    RestTemplate restTemplate;

    public String check(String orderId){
        String result = orderSplit(orderId);
        //订单check逻辑.检查库存逻辑
        return result;
    }

    public boolean checkStorge(String goodsId){
        if(goodsId.equals("123"))
            return false;
        return true;
    }

    public String  orderSplit(String orderId){
        return restTemplate.getForObject("http://orderSplit/order/split?orderId="+orderId,String.class);
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

