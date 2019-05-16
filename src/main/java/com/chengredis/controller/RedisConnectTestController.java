package com.chengredis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author cheng
 * @Date 2019/5/14 0:24
 **/
@RestController
public class RedisConnectTestController {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnectTestController.class);

    @Autowired
    RedisTemplate redisTemplate;


    /**
     spring-data-redis的RedisTemplate<K, V>模板类在操作redis时默认使用JdkSerializationRedisSerializer来进行序列化
     redisTemplate.opsForList();//操作list
     redisTemplate.opsForValue();//操作字符串
     redisTemplate.opsForCluster();//集群时使用
     redisTemplate.opsForGeo();//地理位置时使用
     redisTemplate.opsForHash();//操作hash
     redisTemplate.opsForSet();//操作set
     redisTemplate.opsForZSet();//操作有序set
     * @Author zhangcheng
     * @Date 2019/5/14 1:05
     * spring-data-redis的RedisTemplate<K, V>模板类在操作redis时默认使用JdkSerializationRedisSerializer来进行序列化,
     * 会导致key有乱码，导致客户端无法正确访问，此处初始化String类型的序列化方法
     */
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/set")
    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    @RequestMapping("/get")
    public String show(String key){
        String str = null;
        try {
            str = redisTemplate.opsForValue().get(key).toString();
        } catch (Exception e) {
            logger.error(""+e);
        }
        return str;


    }

    String requestId = UUID.randomUUID().toString();
    String lockKey = "3";

    public Jedis connectRedis(){
        Jedis jedis = new Jedis("47.102.43.84",6379);
        jedis.auth("1234");
        return jedis;
    }

    @RequestMapping("/lock")
    public boolean lock(){
        boolean flag = RedisTool.tryGetDistributedLock(connectRedis(),lockKey, requestId,10000);
        return flag;
    }
    @RequestMapping("/unlock")
    public boolean unlock(){
        return RedisTool.releaseDistributedLock(connectRedis(),lockKey,requestId);
    }

}
