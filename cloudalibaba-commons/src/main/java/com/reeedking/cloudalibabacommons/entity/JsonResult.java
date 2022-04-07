package com.reeedking.cloudalibabacommons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SpringCloudAlibaba
 * @description:
 * @author: wanghong
 * @create: 2022-04-07 16:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {
    private Integer code;
    private T data;
}