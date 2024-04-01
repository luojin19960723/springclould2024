package com.atguigu.cloud.controller;

import com.atguigu.cloud.ResultData;
import com.atguigu.cloud.enetities.PayDto;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/pay")
public class OrderController  {
   // public  static  final  String paymentSrv_url="http://localhost:8001";
    public  static  final  String paymentSrv_url="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/add")
    public ResultData  addOrder(PayDto payDto){
        return  restTemplate.postForObject(paymentSrv_url+"/pay/add", payDto,ResultData.class);
    }
    @GetMapping("/get/{id}")
    public  ResultData getById(@PathVariable("id") Integer id){

        return  restTemplate.getForObject(paymentSrv_url+"/pay/get/"+id, ResultData.class,id);

    } @GetMapping("/getAll")
    public  ResultData getAll(){
        return  restTemplate.getForObject(paymentSrv_url+"/pay/getAll", ResultData.class);
    }

    @GetMapping(value = "/get/info")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(paymentSrv_url + "/pay/get/info", String.class);
    }


    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
