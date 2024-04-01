package com.atguigu.cloud.controller;

import com.atguigu.cloud.ResultData;
import com.atguigu.cloud.ReturnCodeEnum;
import com.atguigu.cloud.enetities.PayDto;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/pay")
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
@Resource
    PayService payService;

    @PostMapping("/add")
    public ResultData add(@RequestBody Pay pay){
        System.out.println(pay);
        int i = payService.addPay(pay);
        return ResultData.success(i);
    }
    @DeleteMapping("/del/{id}")
    public ResultData del(@PathVariable("id") Integer id){
        return ResultData.success(payService.delete(id));
    }
    @PutMapping("/update")
    public ResultData update(@RequestBody PayDto payDto){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDto,pay);
        int i = payService.update(pay);
        return ResultData.success(i);
    }
    @GetMapping("/get/{id}")
    public  ResultData getById(@PathVariable("id") Integer id){

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return  ResultData.success(payService.getById(id));
    }

    @GetMapping("/getAll")
    public  ResultData getAll(){
        return  ResultData.success(payService.getAll());
    }


    @GetMapping("/getError")
    public  ResultData get(){
        Integer integer = Integer.valueOf(100);
        try {
            System.out.println("错误");
         //   int i=1/0;
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return  ResultData.success(integer);
    }
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/get/info")
    private String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo)
    {
        return "atguiguInfo: "+atguiguInfo+"\t"+"port: "+port;
    }


}
