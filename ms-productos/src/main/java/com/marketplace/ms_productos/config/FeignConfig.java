package com.marketplace.ms_productos.config;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.marketplace.ms_productos.client")
public class FeignConfig {}
