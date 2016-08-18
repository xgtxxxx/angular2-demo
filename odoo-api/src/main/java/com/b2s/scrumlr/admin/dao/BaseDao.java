package com.b2s.scrumlr.admin.dao;

import com.b2s.scrumlr.admin.model.KeyValueParamter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class BaseDao extends RedisDao{
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);

    /**
     * Save or Update
     * @param kv
     * @param tableName
     */
    public void save(final KeyValueParamter kv, final String tableName){
        getRedisTemplate().opsForHash().put(tableName,kv.getKey(),kv.getValueAsString());
    }

    public void delete(final String tableName, final String key){
        getRedisTemplate().opsForHash().delete(tableName, key);
    }

    /**
     * query
     * @param key
     * @return
     */
    public Object query(final String key){
        final RedisTemplate<String,Object> redisTemplate = getRedisTemplate();
        try {
            final DataType type = redisTemplate.type(key);
            if(DataType.NONE == type){
                return null;
            }else if(DataType.STRING == type){
                return redisTemplate.opsForValue().get(key);
            }else if(DataType.LIST == type){
                return redisTemplate.opsForList().range(key, 0, -1);
            }else if(DataType.HASH == type){
                return redisTemplate.opsForHash().entries(key);
            }else{
                LOGGER.warn("Can't verify the key!");
                return null;
            }
        } catch (final Exception e) {
            LOGGER.error("Can't query data.", e);
            return null;
        }
    }

    public Object get(final String table, final String key){
        return this.getRedisTemplate().opsForHash().get(table, key);
    }
}
