package com.marketplace.ms_pedidos.config;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
@Configuration @EnableFeignClients(basePackages="com.marketplace.ms_pedidos.client")
public class FeignConfig {}
