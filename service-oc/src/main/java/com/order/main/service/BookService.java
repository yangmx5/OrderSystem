package com.order.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by this on 2018/5/9.
 */
@Service
public class BookService {

    @Autowired
    RestTemplate restTemplate;

    public String bookOrder(){
        String result = "order book start:</br>";
        result += "</br>" + orderCheck("orderId");
        result += "</br>" + orderSplit("orderId");
        return result;
    }

    public String cancelOrder(String orderId){
        String result = "order is cancelling:</br>";
        /*
        检查order的状态是否允许取消
         */
        return result;
    }

    public String queryOrder(String orderId){
        String result = "order query result:</br>";

        //根据orderId 获取waybillId
        String waybillId = "waybillId ";

        result += waybillQuery(waybillId);

        return "this order "+orderId + "status is";
    }

    public String orderCheck(String orderId){
        return restTemplate.getForObject("http://orderCheck/order/check?orderId="+orderId,String.class);
    }

    public String  orderSplit(String orderId){
        return restTemplate.getForObject("http://orderCheck/order/check?orderId="+orderId,String.class);
    }

    public String waybillQuery(String waybillId){

        //调用waybill服务查询当前发货单状态

        return "this waybill "+waybillId +"status is";
    }

    public String test(){
        return restTemplate.getForObject("http://orderCheck/order",String.class);
    }

    public String getIp(){
        try {
            InetAddress address = InetAddress.getByName("192.168.142.133");//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            String  hostName = address.getHostName();
            String hostAddress = address.getHostAddress();//192.168.0.121
            String canonicalhostname = address.getCanonicalHostName();
            byte[] address1 = address.getAddress();
            String add = new String(address1);
            return hostAddress+"</br>"+hostName +"</br>"+canonicalhostname+"</br>"+add;
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
