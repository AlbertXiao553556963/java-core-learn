package com.miaosha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-21 22:31
 **/
@Component
@Slf4j
public class MiaoshaService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

//    @PostConstruct
    public void init() {
        //TODO 针对sessionId进行分布式锁，限制重复提交
        //TODO 使用令牌桶，只有获取到令牌的用户才能继续操作，未拿到令牌的不去查数据库
        for(int i = 0; i < 100; i++) {
            stringRedisTemplate.opsForList().leftPush("token", String.valueOf(i));
        }
        log.info("初始化令牌桶完成");
    }
}
