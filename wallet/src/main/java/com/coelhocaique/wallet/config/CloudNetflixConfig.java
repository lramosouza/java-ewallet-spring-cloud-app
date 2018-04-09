package com.lsouza.wallet.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableHystrixDashboard
@EnableFeignClients("com.lsouza.wallet.feign")
public class CloudNetflixConfig {

}
