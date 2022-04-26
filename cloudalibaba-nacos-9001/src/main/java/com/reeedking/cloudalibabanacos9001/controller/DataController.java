package com.reeedking.cloudalibabanacos9001.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.reeedking.cloudalibabacommons.entity.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @program: SpringCloudAlibaba
 * @description:
 * @author: wanghong
 * @create: 2022-04-07 16:39
 **/
@RestController
public class DataController {


    @Value("${server.port}")
    private String serverPort;

    /**
     * 模拟数据库存储
     */
    public static HashMap<Integer, String> hashMap = new HashMap<>();

    static {
        hashMap.put(1, "鼠标");
        hashMap.put(2, "键盘");
        hashMap.put(3, "耳机");
    }

    @GetMapping("info/{id}")
    public JsonResult<String> msbSql(@PathVariable("id") String id) {
        JsonResult<String> result = new JsonResult(200, serverPort + hashMap.get(Integer.parseInt(id)));
        return result;
    }

}

