package com.carter.sbdemo.config;


/**
 * 自定义Template   使用Lettuce 连接器
 */
//@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfig {


/*    //只能支持一个   RedisTemplate
    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }*/
}
