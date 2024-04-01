package com.atguigu.cloud.controller;


import com.atguigu.cloud.ResultData;
import com.atguigu.cloud.apis.PayGateWayApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class orderGateWayController {


     @Resource
    PayGateWayApi payGateWayApi;
    @GetMapping("/feign/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id){
        return  ResultData.success(payGateWayApi.getById(id));
    }
    @GetMapping("/feign/pay/gateway/info")
    public ResultData getInfo(){
        return  ResultData.success(payGateWayApi.getInfo());
    }
}
