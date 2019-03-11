package com.carter.sbdemo.util;

import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class RedisSetUtil<K,V> {

    RedisTemplate<K, V> redisTemplate;
    SetOperations<K, V> setOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     */
    public RedisSetUtil(RedisTemplate<K, V> redisTemplate) {

        this.redisTemplate = redisTemplate;
        this.setOperations = redisTemplate.opsForSet();
    }

    /**
     * @description: Add given values to set at key.
     * @param:如果不存在这个字符串，将字符串存入set集合，返回存入元素的个数；如果存在这个字符串就不操作，返回0；
     * @return:
     */
    @Nullable
    public Long add(K key, V... var){
        try {
            Long num = setOperations.add(key,var);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:   Remove given values from set at key and return the number of removed elements.
     * @param:移除集合中一个或多个成员
     * @return:
     */
    @Nullable
    public Long remove(K key, Object... var){
        try {
            Long num = setOperations.remove(key,var);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Remove and return a random member from set at key.
     * @param:移除并返回集合中的一个随机元素
     * @return:
     */
    @Nullable
    public V pop(K key){
        try {
            V var = setOperations.pop(key);
            return var;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:   Remove and return count random members from set at key.
     * @param:
     * @return:
     */
    @Nullable
    public List<V> pop(K key, long count){
        try {
            List<V> var = setOperations.pop(key,count);
            return var;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Move value from key to key
     * @param:将 value 元素从 key 集合移动到 key 集合
     * @return:
     */
    @Nullable
    public Boolean move(K key, V value, K destKey){
        try {
            Boolean flag = setOperations.move( key, value,  destKey);
            return flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Get size of set at key.
     * @param:返回set长度
     * @return:
     */
    @Nullable
    public Long size(K key){
        try {
            Long size = setOperations.size(key);
            return size;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Check if set at key contains value.
     * @param:判断 var 元素是否是集合 key 的成员
     * @return:
     */
    @Nullable
    public Boolean isMember(K key, Object var){
        try {
            Boolean flag = setOperations.isMember(key,var);
            return flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @description: Returns the members intersecting all given sets at key and otherKey.
     * @param:比较key与otherKey的set集合，取出二者交集，返回set交集合
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Nullable
    public Set<V> intersect(K key,  K otherKey){
        try {
            Set<V>  set = setOperations.intersect(key,otherKey);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Returns the members intersecting all given sets at key and otherKeys.
     * @param:
     * @return:
     */
    @Nullable
    public Set<V> intersect(K key, Collection<K> otherKeys){
        try {
            Set<V>  set = setOperations.intersect(key,otherKeys);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:   Intersect all given sets at key and otherKey and store result in destKey.
     * @param:
     * @return:   null when used in pipeline / transaction.
     */
    @Nullable
    public Long intersectAndStore(K key, K otherKey, K destKey){
        try {
            Long num = setOperations.intersectAndStore(key,otherKey,destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:  Intersect all given sets at key and otherKeys and store result in destKey.
     * @param:
     * @return:   null when used in pipeline / transaction.
     */
    @Nullable
    public Long intersectAndStore(K key,
                                  Collection<K> otherKeys,
                                  K destKey){
        try {
            Long num = setOperations.intersectAndStore(key,otherKeys,destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:   Union all sets at given keys and otherKey.
     * @param:比较key与otherKey的set集合，取出二者并集，返回set并集合
     * @return:     null when used in pipeline / transaction.
     */
    @Nullable
    public Set<V> union(K key, K otherKey){
        try {
            Set<V> set = setOperations.union(key,otherKey);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:   Union all sets at given keys and otherKeys.
     * @param:
     * @return:   null when used in pipeline / transaction.
     */
    @Nullable
    public Set<V> union(K key, Collection<K> otherKeys){
        try {
            Set<V> set = setOperations.union(key,otherKeys);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:   Union all sets at given key and otherKey and store result in destKey.
     * @param:比较key与otherKey的set集合，取出二者并集，并存入destKey集合，返回destKey集合个数
     * @return: null when used in pipeline / transaction.
     */
    @Nullable
    public Long unionAndStore(K key,
                              K otherKey,
                              K destKey){
        try {
            Long num = setOperations.unionAndStore(key,otherKey,destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Union all sets at given key and otherKeys and store result in destKey.
     * @param:
     * @return:   null when used in pipeline / transaction.
     */
    @Nullable
    public Long unionAndStore(K key,
                              Collection<K> otherKeys,
                              K destKey){
        try {
            Long num = setOperations.unionAndStore(key,otherKeys,destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Diff all sets for given key and otherKey.
     * @param:比较key与otherKey的set集合，取出与otherKey的set集合不一样的set集合  取key对比otherkey的不同值集合
     * @return: null when used in pipeline / transaction.
     */
    @Nullable
    public Set<V> difference(K key, K otherKey){
        try {
            Set<V> set = setOperations.difference(key,otherKey);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Diff all sets for given key and otherKey.
     * @param:
     * @return: null when used in pipeline / transaction.
     */
    @Nullable
    public Set<V> difference(K key,
                             Collection<K> otherKeys){
        try {
            Set<V> set = setOperations.difference(key,otherKeys);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Diff all sets for given key and otherKey and store result in destKey.
     * @param:
     * @return:  null when used in pipeline / transaction.
     */
    @Nullable
    public  Long differenceAndStore(K key,
                                    K otherKey,
                                    K destKey){
        try {
            Long num = setOperations.differenceAndStore(key,otherKey,destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:  Diff all sets for given key and otherKeys and store result in destKey
     * @param:
     * @return: null when used in pipeline / transaction.
     */
    @Nullable
    public Long differenceAndStore(K key,
                                   Collection<K> otherKeys,
                                   K destKey){
        try {
            Long num = setOperations.differenceAndStore(key,otherKeys,destKey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:   Get all elements of set at key.
     * @param:列出key的所有set集合
     * @return:   null when used in pipeline / transaction.
     */
    @Nullable
    public Set<V> members(K key){
        try {
            Set<V> set = setOperations.members(key);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Get random element from set at key.
     * @param:随机取key的一个set元素
     * @return:
     */
    public V randomMember(K key){
        try {
            V var = setOperations.randomMember(key);
            return var;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Get count distinct random elements from set at key.
     * @param:随机取N次key的元素，组成一个set集合，不可以重复取出
     * @return:
     */
    @Nullable
    public Set<V> distinctRandomMembers(K key, long count){
        try {
            Set<V>   set = setOperations.distinctRandomMembers(key,count);
            return set;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description: Get count random elements from set at key.
     * @param:
     * @return:
     */
    @Nullable
    public  List<V> randomMembers(K key, long count){
        try {
            List<V> list = setOperations.randomMembers(key,count);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:  Iterate over elements in set at key.
     * @param:遍历set
     * @return:
     */
    public Cursor<V> scan(K key, ScanOptions options){
        try {
            Cursor<V> cursor = setOperations.scan(key,options);
            return cursor;
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
    public RedisOperations<K, V> getOperations(){
        try {
            RedisOperations<K, V> redisOperations= setOperations.getOperations();
            return redisOperations;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
