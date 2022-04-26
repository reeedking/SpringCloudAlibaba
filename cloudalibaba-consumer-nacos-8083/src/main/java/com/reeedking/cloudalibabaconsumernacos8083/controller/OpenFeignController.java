package com.reeedking.cloudalibabaconsumernacos8083.controller;

import com.reeedking.cloudalibabacommons.entity.JsonResult;
import com.reeedking.cloudalibabaconsumernacos8083.service.OpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudAlibaba
 * @description:
 * @author: wanghong
 * @create: 2022-04-26 17:57
 **/
@RestController
public class OpenFeignController {

    @Autowired
    private OpenFeignService openFeignService;

    @GetMapping("getInfo/{id}")
    public JsonResult<String> getInfo(@PathVariable("id") int id) {
        return openFeignService.msbSql(id);
    }
}
