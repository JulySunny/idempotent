package com.gabriel.idempotent.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Gabriel
 * @date: 2020/3/24 23:35
 * @description
 */
public interface TokenService {
    /**
     * 创建token
     *
     * @return
     */
    public String createToken();

    /**
     * 校验token
     *
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request);
}
