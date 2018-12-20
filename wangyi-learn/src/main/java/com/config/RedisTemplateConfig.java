package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-18 13:06
 * 定义RedisTemplate
 **/
@Configuration
@Slf4j
public class RedisTemplateConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisStandaloneConfiguration mainRedisConfig() {
        return new RedisStandaloneConfiguration();
    }

    @Bean
    @Primary
    public LettuceConnectionFactory mainRedisConnectionFactory(RedisStandaloneConfiguration mainRedisConfig) {
        return new LettuceConnectionFactory(mainRedisConfig);
    }

    @Bean(name = "mainRedis")
    public RedisTemplate<String,String> mainRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return stringRedisTemplate;
    }

}
