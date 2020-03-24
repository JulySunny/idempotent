package com.gabriel.idempotent.controller;

import com.gabriel.idempotent.config.AutoIdempotent;
import com.gabriel.idempotent.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Gabriel
 * @date: 2020/3/25 0:02
 * @description
 */
@RestController
public class TestController {
    @Resource
    private TokenService tokenService;

    /**
     * 获取token
     *
     * @return
     */
    @PostMapping("/get/token")
    public String getToken() {
        String token = tokenService.createToken();
        return token;
    }

    /**
     * 业务接口
     * @return
     */
    @AutoIdempotent
    @PostMapping("/test/idempotence")
    public String testIdpontence() {
        return "success";
    }
}
