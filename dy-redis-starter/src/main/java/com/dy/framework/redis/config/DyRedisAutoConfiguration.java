package com.dy.framework.redis.config;

import com.dy.framework.redis.lock.RedisDistributedLock;
import com.dy.framework.redis.lock.impl.RedisDistributedLockImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 自动配置类
 * @author daiyuanjing
 * @date  2023-07-21
 */
@EnableCaching
@AutoConfiguration
@RequiredArgsConstructor
@ConditionalOnClass(RedisConnectionFactory.class)
public class DyRedisAutoConfiguration {

    private final RedisConnectionFactory factory;
    private final RedissonClient redissonClient;

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<?,?> redisTemplate(){
        RedisTemplate<?,?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 序列化
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        return redisTemplate;
    }

    /**
     * 缓存键名生成
     */
    @Bean
    @ConditionalOnMissingBean
    public KeyGenerator keyGenerator(){
        return ((target, method, params) -> {
            StringBuilder sb = new StringBuilder(64);
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object param : params) {
                if (param!= null){
                    sb.append(":").append(param);
                }
            }
            return sb.toString();

        });
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisDistributedLock redisDistributedLock(){
        return new RedisDistributedLockImpl(redissonClient);
    }
}
