package com.example.primary.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;

public class RedisCacheConfig extends CachingConfigurerSupport {
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String className = target.getClass().getName();
            String methodName = method.getName();
            return className + "_" + methodName + "_" + params[0].toString();

        };
    }
}
