package com.gabriel.idempotent.service;

import com.gabriel.idempotent.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author: Gabriel
 * @date: 2020/3/24 23:38
 * @description token业务类
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisService redisService;

    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();

        try {
            token.append(Constant.TOKEN_PREFIX).append(str);
            redisService.setEx(token.toString(), token.toString(), 10000L);
            boolean empty = StringUtils.isEmpty(token.toString());
            if (!empty) {
                return token.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(Constant.TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {
                throw new RuntimeException("token不存在");
            }
        }

        if (!redisService.exists(token)) {
            throw new RuntimeException("重复的请求");
        }

        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new RuntimeException("重复的请求");

        }
        return true;
    }
}
