package com.reeedking.cloudalibabasentinelservice8401.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @program: SpringCloudAlibaba
 * @description: sentinel限流测试
 * @author: wanghong
 * @create: 2022-04-07 14:28
 **/
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        //暂停0.8秒 用于测试流控规则中并发线程数设置
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "-----testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "handler_HotKey")
    public String testHotKey(@RequestParam(value = "hot1",required = false) String hot1,
                             @RequestParam(value = "hot2",required = false)String hot2,
                             @RequestParam(value = "hot13",required = false) String hot3){
        return "----testHotKey";
    }

    //处理异常方法，方法签名要和对应的接口方法保持一致
    public String handler_HotKey(String hot1, String hot2, String hot3, BlockException exception){
        return "系统繁忙稍后重试。。";
    }
}
