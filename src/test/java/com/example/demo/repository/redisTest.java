package com.example.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest() {
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(stringSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(stringSerializer);
//
//        redisTemplate.opsForValue().set("name", "tiga");
//        String name = (String) redisTemplate.opsForValue().get("name");
//        System.out.println(name);
//        redisTemplate.delete("name");
    }
}
