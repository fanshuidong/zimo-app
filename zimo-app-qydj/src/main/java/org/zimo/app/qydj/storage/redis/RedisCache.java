package org.zimo.app.qydj.storage.redis;

import javax.annotation.Resource;

import org.zimo.redis.RedisHashBean;
import org.zimo.redis.operate.RedisOperate;
import org.zimo.util.reflect.BeanUtils;

public abstract class RedisCache {
	
	@Resource
	protected RedisOperate redisOperate;
	
	public <T extends RedisHashBean> T getHashBean(T bean) {
		return redisOperate.hgetAll(bean.redisKey(), bean);
	}

	public void flushHashBean(RedisHashBean bean) { 
		redisOperate.hmset(bean.redisKey(), BeanUtils.beanToMap(bean));
	}
}
