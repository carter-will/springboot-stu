package com.carter.sbdemo.controller;

import com.carter.sbdemo.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/redis")
public class RedisController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisStringUtil redisStringsUtil;

    @Autowired
    private RedisListUtil redisListsUtil;

    @Autowired
    private RedisHashUtil redisHashUtil;

    @Autowired
    private RedisSetUtil redisSetUtil;

    @Autowired
    private RedisZSetUtil redisZSetUtil;

    @Autowired
    private RedisHyperLogLogUtil redisHyperLogLogUtil;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * String操作
     *
     * @param time
     * @return
     */
    @ResponseBody
    @GetMapping("/str")
    public boolean operateString(long time) {
        log.error("11111111111111");
        log.debug("22222222222");
        log.warn("3333333333333333");
        boolean flag = redisStringsUtil.set("strkey1", "strvalue1");
        System.out.println("add " + flag);
        String value = (String) redisStringsUtil.get("strkey1");
        System.out.println("get " + value);

        flag = redisStringsUtil.set("strkey2", "strvalue2", time, TimeUnit.MILLISECONDS);
        System.out.println("add " + flag);
        value = (String) redisStringsUtil.get("strkey2");
        System.out.println("get " + value);

        flag = redisStringsUtil.delete("strkey2");
        System.out.println("delete " + flag);
        return flag;
    }

    /**
     * List操作
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public boolean operateList() {
        log.error("11111111111111");
        log.debug("22222222222");
        log.warn("3333333333333333");
        redisListsUtil.leftPush("language", "c");
        redisListsUtil.leftPushAll("language", "c#", "python", "ruby");
        redisListsUtil.leftPush("language", "python", "||");

        redisListsUtil.rightPush("language", "js");
        redisListsUtil.rightPushAll("language", "jquery", "react", "vue");
        redisListsUtil.rightPush("language", "react", "||");

        redisListsUtil.leftPop("language");
        redisListsUtil.rightPop("language");
        redisListsUtil.rightPopAndLeftPush("language", "language");
        Long l = redisListsUtil.size("language");    //list 的size
        System.out.println("list size is : " + l);


        redisListsUtil.set("language", 3, "PHP");
        Long r = redisListsUtil.remove("language", 2, "||");
        System.out.println(r);
        String v = (String) redisListsUtil.index("language", 2);
        System.out.println(v);


        List<String> vList = redisListsUtil.range("language", 0, -1);//从左到右遍历 list的值
        for (int i = 0; i < vList.size(); i++) {
            System.out.println(vList.get(i));
        }

        return true;
    }


    /**
     * Hash操作
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/hash")
    public boolean operatorHash() {
        redisHashUtil.put("zoo", "dog", "1");
        Map map = new HashMap();
        map.put("cat", "2");
        map.put("rabbit", "3");
        map.put("lion", "9");
        redisHashUtil.putAll("zoo", map);
        redisHashUtil.putIfAbsent("zoo", "tiger", "4");
        System.out.println(redisHashUtil.get("zoo", "cat"));
        System.out.println(redisHashUtil.size("zoo"));
        System.out.println(redisHashUtil.hasKey("zoo", "dog"));
        List<String> list = redisHashUtil.multiGet("zoo", Arrays.asList("dog", "cat", "rabbit"));
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("-----------------------------------------");
        Map<String, String> en = redisHashUtil.entries("zoo");
        System.out.println("-----------------------------------------");
        Set<String> set = redisHashUtil.keys("zoo");
        for (String str : set) {
            System.out.println(str);
        }
        System.out.println("-----------------------------------------");
        List<String> ss = redisHashUtil.values("zoo");
        for (String str : ss) {
            System.out.println(str);
        }
        redisHashUtil.delete("zoo", "lion");
        return true;
    }


    /**
     * Set操作
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/set")
    public boolean operatorSet() {
        redisSetUtil.add("technology", "spring", "mybatis", "hibernate", "springboot");
        redisSetUtil.add("study", "spring", "quartz", "kafka");
        System.out.println(redisSetUtil.isMember("technology", "springboot"));
        System.out.println(redisSetUtil.size("technology"));
        System.out.println(redisSetUtil.randomMember("study"));
        redisSetUtil.intersectAndStore("technology", "study", "intersect");
        redisSetUtil.unionAndStore("technology", "study", "union");
        redisSetUtil.differenceAndStore("technology", "study", "diff");

        redisSetUtil.pop("technology");
        redisSetUtil.remove("study", "kafka");
        redisSetUtil.move("technology", "mybatis", "study");

        return true;
    }


    /**
     * ZSet操作
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/zset")
    public boolean operatorZSet() {
        redisZSetUtil.add("rank", "a", 1);
        redisZSetUtil.add("rank", "b", 2);
        redisZSetUtil.add("rank", "c", 3);
        redisZSetUtil.add("rank", "d", 4);
        redisZSetUtil.add("rank", "e", 5);
        redisZSetUtil.incrementScore("rank", "c", 3.1);
        System.out.println(redisZSetUtil.score("rank", "b"));
        System.out.println(redisZSetUtil.count("rank", 2, 4));
        System.out.println(redisZSetUtil.rank("rank", "e"));
        System.out.println(redisZSetUtil.reverseRank("rank", "e"));
        System.out.println(redisZSetUtil.zCard("rank"));
        redisZSetUtil.remove("rank", "d");


        return true;
    }


    /**
     * ZSet操作
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/hyperLogLog")
    public boolean operatorHyperLogLog() {
        redisHyperLogLogUtil.add("book", "a", "b", "c", "d");
        System.out.println(redisHyperLogLogUtil.size("book"));
        redisHyperLogLogUtil.add("read", "a", "v", "c", "u");
        redisHyperLogLogUtil.union("dest", "book", "read");
        System.out.println(redisHyperLogLogUtil.size("dest"));
        redisHyperLogLogUtil.delete("read");

        return true;
    }


    @ResponseBody
    @GetMapping("/jedis")
    public boolean jedis() {
        Jedis jedis = getJedisByReflect();
        jedis.set("test-key", "Hello world");
        return true;
    }


    @ResponseBody
    @GetMapping("/pipeline")
    public boolean pipeline() {
        Jedis jedis = getJedisByReflect();
        try{
            long start = System.currentTimeMillis();
            Pipeline pl = jedis.pipelined();
            for(int i =0; i<400; i++){
                pl.incr("testKey1");
            }
            pl.sync();
            System.out.println("pipeline -->  " + (System.currentTimeMillis()-start));
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jedis.disconnect();
        }


        Jedis jedis1 = getJedisByReflect();
        try{
            long start = System.currentTimeMillis();
            for(int i =0; i<400; i++){
                jedis1.incr("testKey2");
            }
            System.out.println("without pipeline  -->" + (System.currentTimeMillis()-start));
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jedis1.disconnect();
        }



        return true;
    }



    public Jedis getJedisByReflect(){
        Field jedisField = ReflectionUtils.findField(JedisConnection.class, "jedis");
        ReflectionUtils.makeAccessible(jedisField);
        System.out.println(connectionFactory.getConnection());
        Jedis jedis = (Jedis) ReflectionUtils.getField(jedisField, connectionFactory.getConnection());
        return jedis;
    }
}