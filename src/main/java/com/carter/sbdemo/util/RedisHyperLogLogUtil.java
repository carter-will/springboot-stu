package com.carter.sbdemo.util;


import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis HyperLogLog 是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定 的、并且是很小的。
 * 在 Redis 里面，每个 HyperLogLog 键只需要花费 12 KB 内存，就可以计算接近 2^64 个不同元素的基 数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。
 * 但是，因为 HyperLogLog 只会根据输入元素来计算基数，而不会储存输入元素本身，所以 HyperLogLog 不能像集合那样，返回输入的各个元素。
 *
 *
 *   HyperLogLog相当于其值是唯一的,可用来统计在线人数
 *  *              pfadd  key  a b c ;key[a,b,c]
 *  *              pfcount key --> size is 3;
 *  *
 *  *              pfadd  key  a  --> key[a,b,c]
 *  *              pfcount key --> size is 3;
 *  *
 *  *              pfadd  key  d  --> key[a,b,c,d]
 *  *              pfcount key --> size is 4;
 */
@Component
public class RedisHyperLogLogUtil<K, V> {

    RedisTemplate<K, V> redisTemplate;

    HyperLogLogOperations<K, V> hyperLogLogOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 8/30/18
     */

    public RedisHyperLogLogUtil(RedisTemplate<K, V> redisTemplate) {

        this.redisTemplate = redisTemplate;
        hyperLogLogOperations =  this.redisTemplate.opsForHyperLogLog();
    }


    /**
     * @description: 向key 添加varlue
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/30/18
     */
    public Long add(K key, V... var){
        try {
            Long num =  hyperLogLogOperations.add(key,var);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:  查看key的size
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/30/18
     */
    public  Long size(K... key){
        try {
            Long size =  hyperLogLogOperations.size(key);
            return size;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:  合并
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/30/18
     */
    public Long union(K destkey, K... sourcekey){
        try {
            Long num =  hyperLogLogOperations.union(destkey,sourcekey);
            return num;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description:　
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/30/18
     */
    public boolean delete(K key){
        try {
            hyperLogLogOperations.delete(key);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
