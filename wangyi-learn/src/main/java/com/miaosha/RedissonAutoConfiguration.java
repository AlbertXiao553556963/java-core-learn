package com.miaosha;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@AutoConfigureAfter(RedisAutoConfiguration.class)
@ConditionalOnClass({Redisson.class, RedisTemplate.class})
//@ConditionalOnProperty(name = {"spring.redis.sentinel.master", "spring.redis.sentinel.nodes"})
@Configuration
@Slf4j
public class RedissonAutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    private SingleServerConfig singleServers(final Config config){
        SingleServerConfig singleServerConfig = config.useSingleServer();
        String schema = redisProperties.isSsl() ? "rediss://" : "redis://";
        singleServerConfig.setAddress(schema + redisProperties.getHost() + ":" + redisProperties.getPort());
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        if (redisProperties.getPassword() != null) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        return singleServerConfig;
    }

    @Bean
    public RedissonClient redissonClient() {
        final Config config = new Config();
        this.singleServers(config);
        return Redisson.create(config);
    }
}