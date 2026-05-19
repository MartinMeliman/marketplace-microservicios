package com.marketplace.ms_carrito.config;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
@Configuration @EnableFeignClients(basePackages="com.marketplace.ms_carrito.client")
public class FeignConfig {}
