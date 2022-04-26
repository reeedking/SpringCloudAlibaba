package com.reeedking.cloudalibabaconsumernacos8083.service;

import com.reeedking.cloudalibabacommons.entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: SpringCloudAlibaba
 * @description:
 * @author: wanghong
 * @create: 2022-04-26 17:56
 **/
@Service
@FeignClient("nacos-provider")
public interface OpenFeignService {

    @GetMapping("info/{id}")
    JsonResult<String> msbSql(@PathVariable("id") int id);
}
