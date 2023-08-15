package com.moyu.third.service;

import java.util.concurrent.ExecutionException;

/**
 * @Author: zenghai.li
 * @Date: 2023/08/10/22:24
 * @Description:
 */
public interface ThirdSmsService {

    /**
     * 发送短信
     * @param mobile
     */
    void sendSms(String mobile) throws ExecutionException, InterruptedException;
}
