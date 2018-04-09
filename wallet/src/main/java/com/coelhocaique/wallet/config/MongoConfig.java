package com.lsouza.wallet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages="com.lsouza.wallet.repository")
public class MongoConfig {

}
