package com.reeedking.cloudalibabaconsumernacos8083.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.reeedking.cloudalibabacommons.entity.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 消费者去访问具体服务，这种写法可以实现
     * 配置文件和代码的分离
     */
    @Value("${service-url.nacos-user-service}")
    private String serverURL;


    @GetMapping(value = "consumer/nacos")
    public String getDiscovery() {
        System.err.println(serverURL);
        return restTemplate.getForObject(serverURL + "/reeedking", String.class);
    }

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "fallbackHandler",blockHandler = "blockHandler")
    public JsonResult<String> fallback(@PathVariable int id) {
        if (id <= 3) {
            JsonResult<String> result = restTemplate.getForObject(serverURL + "/info/" + id, JsonResult.class);
            System.err.println(result.getData());
            return result;
        } else {
            throw new NullPointerException("没有对应的数据记录");
        }

    }

    /**
     * 对/consumer/** 进行流控设置，验证是否会被限流
     * @return
     */
    @GetMapping("/consumer/test")
    public String test(){
        return "test...";
    }


    /**
     * 保证方法签名基本保持一致，但是要添加异常类型参数
     *
     * @param id
     * @param e
     * @return
     */
    public JsonResult<String> fallbackHandler(int id, Throwable e) {
        JsonResult<String> result = new JsonResult<>(444, "出现未知商品id");
        return result;
    }

    /**
     * 处理Sentinel限流
     * @param id
     * @param e
     * @return
     */
    public JsonResult<String> blockHandler(int id, BlockException e){
        JsonResult<String> result = new JsonResult<>(445,"BlockException限流");
        return result;
    }
}
