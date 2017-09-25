package cn.stwms.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

import cn.stwms.utils.BaseUtils;
import cn.stwms.utils.BeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisCache implements Cache {
	
    private static Log logger = LogFactory.getLog(RedisCache.class);
    
    /** The ReadWriteLock. */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Jedis redisClient;
    private String id;
    
    //外部可配置参数
    private String host="127.0.0.1";	//服务器
	private int port=6379;				//连接端口
	private int expires=60;		//单位：秒
	private String password=null;

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
    public int getExpires() {
		return expires;
	}
	public void setExpires(int expires) {
		this.expires = expires;
	}

	
    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public int getSize() {
        return Integer.valueOf(getRedis().dbSize().toString());
    }
    
    public void putObject(Object key, Object value) {
        byte[] datas = BeanUtils.serialize(value);
        byte[] nxxx="NX".getBytes();
        byte[] expx="EX".getBytes();
        getRedis().set(getKey(key), datas,nxxx,expx,expires);
    }

    public Object getObject(Object key) {
        byte[] bytes = getRedis().get(getKey(key));
        Object value = BeanUtils.unserialize(bytes);
        return value;
    }

    public Object removeObject(Object key) {
        return getRedis().expire(getKey(key), 0);
    }

    public void clear() {
    	getRedis().flushDB();
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @SuppressWarnings("resource")
	protected Jedis getRedis() {
        try {
        	if(redisClient==null){
	            JedisPoolConfig config = new JedisPoolConfig();
	            //System.out.print("---JedisPool Init-->host:"+host+" port:"+port+" expires:"+expires);
	            JedisPool pool = new JedisPool(config, host,port,Protocol.DEFAULT_TIMEOUT,password);
	            redisClient=pool.getResource();
        	}
        	return redisClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Redis Connect init failed!");
    }
    
    private byte[] getKey(Object key){
    	String md5str=BaseUtils.md5(key.toString());
    	return md5str.getBytes();
    }
}