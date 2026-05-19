package com.marketplace.ms_resenas.config;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
@Configuration @EnableFeignClients(basePackages="com.marketplace.ms_resenas.client")
public class FeignConfig {}
