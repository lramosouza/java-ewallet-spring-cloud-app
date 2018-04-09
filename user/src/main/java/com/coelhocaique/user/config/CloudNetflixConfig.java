package com.lsouza.user.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableFeignClients("com.lsouza.user.feign")
public class CloudNetflixConfig {

}
