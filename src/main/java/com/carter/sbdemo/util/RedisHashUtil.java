package com.carter.sbdemo.util;

import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RedisHashUtil<H, HK, HV> {

    RedisTemplate<H, HV> redisTemplate;
    HashOperations<H, HK, HV> hashOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     */
    public RedisHashUtil(RedisTemplate<H, HV> redisTemplate) {

        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    /**
     * @description: Delete given hash hashKeys.删除一个或多个哈希字段。
     * @param:相当于hdel命令
     * @return:
     */
    public Long delete(H key, Object... var){
        try {
            Long num  = hashOperations.delete(key,var);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Determine if given hash hashKey exists.判断是否存在散列字段
     * @param:相当于hexist key field命令
     * @return:
     */
    public Boolean hasKey(H key, Object var){
        try {
            Boolean flag = hashOperations.hasKey(key,var);
            return  flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get value for given hashKey from hash at key.获取存储在指定键的哈希字段的值。
     * @param:
     * @return:
     */
    @Nullable
    public HV get(H key, Object var){
        try {
            HV value  = hashOperations.get(key,var);
            return value;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get values for given hashKeys from hash at key.获取所有给定哈希字段的值
     * @param:  相当于hmget命令
     * @return:
     */
    public List<HV> multiGet(H key, Collection<HK> var){
        try {
            List<HV> list = hashOperations.multiGet(key,var);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Increment value of a hash hashKey by the given delta.
     * @param:相当于hincrby命令
     * @return:
     */
    public Long increment(H key, HK hashKey, long delta){
        try {
            Long num  = hashOperations.increment(key,hashKey,delta);
            return  num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Increment value of a hash hashKey by the given delta.
     * @param:相当于hincrbyfloat命令
     * @return:
     */
    public Double increment(H key, HK hashKey, double delta){
        try {
            Double num = hashOperations.increment(key,hashKey,delta);
            return  num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get key set (fields) of hash at key.返回map的key集合Set
     * @param:相当于hkeys命令
     * @return:
     */
    public Set<HK> keys(H key){
        try {
            Set<HK> set = hashOperations.keys(key);
            return  set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get size of hash at key.获取散列中的字段数量
     * @param:
     * @return:
     */
    public Long size(H key){
        try {
            Long size  = hashOperations.size(key);
            return  size;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Set multiple hash fields to multiple values using data provided in var.
     * 为多个哈希字段分别设置它们的值
     * @param:相当于hmset命令
     * @return:
     */
    public void putAll(H key, Map<? extends HK, ? extends HV> var){
        try {
            hashOperations.putAll(key,var);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * @description: Set the value of a hash hashKey.设置散列字段的字符串值
     * @param:相当与hset命令
     * @return:
     */
    public void put(H key, HK hashKey, HV value){
        try {
            hashOperations.put(key,hashKey,value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * @description: Set the value of a hash hashKey only if hashKey does not exist.
     * 仅当字段不存在时，才设置散列字段的值
     * @param:相当于hsetnx命令
     * @return:
     */
    public Boolean putIfAbsent(H key, HK hashKey, HV value){
        try {
            Boolean flag = hashOperations.putIfAbsent(key,hashKey,value);
            return flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get entry set (values) of hash at key.获取哈希中的所有值
     * @param:相当于hvals命令
     * @return:
     */
    public List<HV> values(H key){
        try {
            List<HV> list  = hashOperations.values(key);
            return  list;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Get entire hash stored at key.返回map集合
     * @param:相当于hgetall命令
     * @return:
     */
    public Map<HK, HV> entries(H key){
        try {
            Map<HK, HV>  map  = hashOperations.entries(key);
            return  map;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Use a Cursor to iterate over entries in hash at key.
     * @param:
     * @return:
     */
    public Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options){
        try {
            Cursor<Map.Entry<HK, HV>> cursor  = hashOperations.scan(key,options);
            return  cursor;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description:
     * @param:
     * @return:
     */
    public RedisOperations<H, ?> getOperations(){
        return hashOperations.getOperations();
    }




}
