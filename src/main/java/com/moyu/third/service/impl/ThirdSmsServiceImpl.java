package com.moyu.third.service.impl;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import com.moyu.third.service.ThirdSmsService;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zenghai.li
 * @Date: 2023/08/10/22:24
 * @Description:
 */

@Service
public class ThirdSmsServiceImpl implements ThirdSmsService {


    @Override
    public void sendSms(String mobile) throws ExecutionException, InterruptedException {

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5tGypQJf6GAWAdKfT9RK")
                .accessKeySecret("u2AcByOwsksbHq38De4411aFtkFl20")
                .build());

        AsyncClient client = AsyncClient.builder()
                .region("cn-shanghai")
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers("17312741120")
                .signName("支付平台")
                .templateCode("SMS_284315191")
                .templateParam("{\"code\":\"1234\"}")
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        client.close();
    }

}
