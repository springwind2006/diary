package cn.stwms.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

import cn.stwms.utils.BeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache implements Cache {
	private static String host="127.0.0.1"; //服务器
	private static int port=6379; //连接端口
	private static int timeout=15; //单位：秒
    private static Log logger = LogFactory.getLog(RedisCache.class);
    private Jedis redisClient = createClient();

    /** The ReadWriteLock. */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;
    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("----------->>MybatisRedisCache:id=" + id);
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public int getSize() {
        return Integer.valueOf(redisClient.dbSize().toString());
    }

    public void putObject(Object key, Object value) {
        byte[] bytes = BeanUtils.serialize(value);
        redisClient.set(BeanUtils.serialize(key.toString()), bytes);
        logger.debug("----------->>putObject:" + key + "=" + value);
    }

    public Object getObject(Object key) {
        byte[] bytes = redisClient.get(BeanUtils.serialize(key.toString()));
        Object value = BeanUtils.unserialize(bytes);
        logger.debug("----------->>getObject:" + key + "=" + value);
        return value;
    }

    public Object removeObject(Object key) {
        return redisClient.expire(BeanUtils.serialize(key.toString()), 0);
    }

    public void clear() {
        redisClient.flushDB();
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    protected static Jedis createClient() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            JedisPool pool = new JedisPool(config, host,port,timeout);
               return pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("初始化连接池错误");
    }
}