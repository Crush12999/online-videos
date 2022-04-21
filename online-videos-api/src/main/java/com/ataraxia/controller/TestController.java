package com.ataraxia.controller;

import com.ataraxia.domain.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ataraxia
 * @create 2022/4/21 07:30
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseResult test() {
        int i = 1 / 0;
        return ResponseResult.success();
    }
}
