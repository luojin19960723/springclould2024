package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.StorageMapper;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service

public class StorageServiceImpl implements StorageService {

    @Resource
   private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        storageMapper.decrease(productId,count);
    }
}
