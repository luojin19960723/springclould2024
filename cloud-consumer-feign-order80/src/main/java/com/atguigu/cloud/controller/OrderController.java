package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.atguigu.cloud.ResultData;
import com.atguigu.cloud.ReturnCodeEnum;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.enetities.PayDto;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/consumer/pay")
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;
    @PostMapping("/add")
    public ResultData  addOrder(@RequestBody PayDto payDto){
        System.out.println("省略sql，模拟新增");
        ResultData resultData = payFeignApi.addPay(payDto);
            return  resultData;
    }
    @GetMapping ("/get/{id}")
    public  ResultData getById(@PathVariable("id") Integer id){
        ResultData resultData=null;
       try {
           System.out.println("调用开始："+ DateUtil.now());
           resultData = payFeignApi.getPayInfo(id);
           System.out.println("省略sql，模拟查询");
       }catch (Exception e){
           e.printStackTrace();
           System.out.println("调用结束："+ DateUtil.now());

           return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
       }


        return  resultData ;
    }
   @GetMapping("/mylb")
    public String mylb(){
        return  payFeignApi.mylb();
   }
    @GetMapping ("/getAll")
    public  ResultData getAll(){
        System.out.println("省略sql，模拟查询");

        return         payFeignApi.getAll();
    }


}
