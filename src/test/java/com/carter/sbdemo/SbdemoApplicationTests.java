package com.carter.sbdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbdemoApplicationTests {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    public void contextLoads() {


    }


    @Test
    public void get() {
        // TODO 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        System.out.print(k1);
    }

    /**
     * opsForValue：对应 String（字符串）
     * •opsForZSet：对应 ZSet（有序集合）
     * •opsForHash：对应 Hash（哈希）
     * •opsForList：对应 List（列表）
     * •opsForSet：对应 Set（集合）
     * •opsForGeo：对应 GEO（地理位置）
     */

}
