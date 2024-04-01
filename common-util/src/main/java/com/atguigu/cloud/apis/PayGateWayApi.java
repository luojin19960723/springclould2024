package com.atguigu.cloud.apis;

import com.atguigu.cloud.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(value = "cloud-gateway")
public interface PayGateWayApi {
    @GetMapping("/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);
    @GetMapping("/pay/gateway/info")
    public ResultData getInfo();
}
