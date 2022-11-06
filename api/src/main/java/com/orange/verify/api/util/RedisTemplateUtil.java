package com.orange.verify.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis的类序列化工具类
 * 采用gson做序列化和反序列化
 * @author orange
 */
@Component
public class RedisTemplateUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    public void serializer(String key, Object value) {
        String valueJson = GsonUtil.buildGson().toJson(value);
        redisTemplate.opsForValue().set(key, valueJson);
    }

    public void serializer(String key, Object value, long time, TimeUnit timeUnit) {
        String valueJson = GsonUtil.buildGson().toJson(value);
        redisTemplate.opsForValue().set(key, valueJson, time, timeUnit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> afterDeserializeClass) {
        Object obj = redisTemplate.opsForValue().get(key);
        if (null != obj) {
            return (T) obj;
        }
        return null;
    }

    public <T> T deserialize(String key, Class<T> afterDeserializeClass) {
        Object obj = redisTemplate.opsForValue().get(key);
        if (null != obj) {
            return (T) GsonUtil.buildGson().fromJson((String) obj, afterDeserializeClass);
        }
        return null;
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    public void expire(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

}
