package top.potat.spring.common.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 保存属性 过期时间 秒
     */
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }


    /**
     * 保存属性
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 获取属性
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 删除属性
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }


    /**
     * 批量删除属性
     */
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }


    /**
     * 设置过期时间
     */
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }


    /**
     * 获取过期时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断是否有该属性
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 按delta递增
     */
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 按delta递增 过期时间 秒
     */
    public Long incr(String key, long delta,long time) {
        Long increment = redisTemplate.opsForValue().increment(key, delta);
        expire(key, time);
        return increment;
    }


    /**
     * 按delta递减
     */
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    /**
     * 获取Hash结构中的属性
     */
    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }


    /**
     * 向Hash结构中放入一个属性
     */
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }


    /**
     * 向Hash结构中放入一个属性
     */
    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }


    /**
     * 直接获取整个Hash结构
     */
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 直接设置整个Hash结构
     */
    public Boolean hSetAll(String key, Map<String, ?> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }


    /**
     * 直接设置整个Hash结构
     */
    public void hSetAll(String key, Map<String, ?> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * 删除Hash结构中的属性
     */
    public void hDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }


    /**
     * 判断Hash结构中是否有该属性
     */
    public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }


    /**
     * Hash结构中属性递增
     */
    public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }


    /**
     * Hash结构中属性递增 过期时间 秒
     */
    public Long hIncr(String key, String hashKey, Long delta,long time) {
        Long increment = redisTemplate.opsForHash().increment(key, hashKey, delta);
        expire(key, time);
        return increment;
    }


    /**
     * 直接获取Hash结构中属性递增
     */
    public Map<Object, Object> hIncrGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * Hash结构中属性递减
     */
    public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }


    /**
     * 获取Set结构
     */
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    /**
     * 向Set结构中添加属性
     */
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }


    /**
     * 向Set结构中添加属性
     */
    public Long sAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }


    /**
     * 是否为Set中的属性
     */
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }


    /**
     * 获取Set结构的长度
     */
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }


    /**
     * 删除Set结构中的属性
     */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }


    /**
     * 获取List结构中的属性
     */
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    /**
     * 获取List结构的长度
     */
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 根据索引获取List中的属性
     */
    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }


    /**
     * 向List结构中添加属性
     */
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }


    /**
     * 向List结构中添加属性
     */
    public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }


    /**
     * 向List结构中批量添加属性
     */
    public Long lPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }


    /**
     * 向List结构中批量添加属性
     */
    public Long lPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }


    /**
     * 从List结构中移除属性
     */
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }


    /**
     * 从有序集合结构中的属性进行增减操作
     */
    public Double zSAddIncr(String key, Object value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }


    /**
     *  从有序集合结构中的属性 按照从大到小的顺序 来获取成员
     */
    public Set<Object> zSIncrReverseRange(String key,long start, long end){
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }


    /**
     * 发布订阅
     */
    public void convertAndSentRedis(String key,Object value){
        redisTemplate.convertAndSend(key,value);
    }


    /**
     * 终极简化版本获取锁
     * @param lockKey 锁key
     * @return 锁值 ->如果是null表示获取锁失败
     */
    public String getLockEsyPlus(String lockKey){
        // 生成随机 UUID (版本 4)
        UUID uuid = UUID.randomUUID();
        String compactUuid = uuid.toString().replace("-", "");
        Boolean lockEsy = getLockEsy(lockKey, compactUuid, 60);
        if(lockEsy) return compactUuid;
        return null;
    }


    /**
     * 简化获取锁
     * @param lockKey 锁key
     * @param value 锁值
     * @param expireTimeMillis：单位-秒
     */
    public Boolean getLockEsy(String lockKey, String value, int expireTimeMillis) {
        try {
            // 使用 SET key value NX PX 命令，原子性操作
            Boolean result = redisTemplate.opsForValue().setIfAbsent(
                    lockKey,
                    value,
                    expireTimeMillis,
                    TimeUnit.SECONDS
            );
            return result != null && result;
        } catch (Exception e) {
            logger.error("获取分布式锁失败", e);
            return false;
        }
    }


    /**
     * 获取锁
     * @param lockKey 锁key
     * @param value 锁值
     * @param expireTime：单位-秒
     * @return true-获取锁成功，false-获取锁失败
     */
    public Boolean getLock(String lockKey, Object value, int expireTime) {
        try {
            String script = "if (redis.call('setnx',KEYS[1],ARGV[1]) == 1) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";
            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
            Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value, expireTime);
            return result == 1L;
        } catch (Exception e) {
            logger.error("获取分布式锁失败", e);
        }
        return false;
    }

    /**
     * 释放锁
     * @param lockKey 锁key
     * @param value 锁值
     * @return
     */
    public Boolean releaseLock(String lockKey, String value) {
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
            Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);
            return result == 1L;
        }catch (Exception e){
            logger.error("释放分布式锁失败", e);
        }
        return false;
    }

}
