package com.ataraxia.controller;

import com.ataraxia.controller.support.UserSupport;
import com.ataraxia.domain.BarrageDO;
import com.ataraxia.domain.ResponseResult;
import com.ataraxia.service.BarrageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/25 23:23
 * @description 弹幕接口
 */
@Api(tags = "弹幕接口")
@RestController
public class BarrageController {

    @Autowired
    private BarrageService barrageService;

    @Autowired
    private UserSupport userSupport;

    @GetMapping("/danmus")
    @ApiOperation(value = "获取弹幕信息")
    public ResponseResult<List<BarrageDO>> listBarrages(@RequestParam Long videoId,
                                                        String startTime,
                                                        String endTime) throws Exception {
        List<BarrageDO> list;
        try {
            userSupport.getCurrentUserId();
            list = barrageService.listBarrages(videoId, startTime, endTime);
        } catch (Exception ignored) {
            list = barrageService.listBarrages(videoId, null, null);
        }
        return new ResponseResult<>(list);
    }
}
