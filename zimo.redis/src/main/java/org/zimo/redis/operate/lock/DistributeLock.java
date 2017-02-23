package org.zimo.redis.operate.lock;

import java.util.concurrent.TimeUnit;

import org.zimo.redis.RedisConsts;
import org.zimo.redis.model.EXPX;
import org.zimo.redis.model.NXXX;
import org.zimo.redis.operate.RedisOperate;
import org.zimo.redis.operate.lua.LuaOperate;
import org.zimo.util.common.uuid.AlternativeJdkIdGenerator;
import org.zimo.util.common.uuid.IdGenerator;

public class DistributeLock {

	private int lockTimeout = 3000;													// 锁的有效时间默认为 3 秒中
	private LuaOperate luaOperate;
	private RedisOperate redisOperate;
	private IdGenerator lockIdGenerator = new AlternativeJdkIdGenerator();			// 用来生成分布式锁Id
	
	public String tryLock(String lock) { 
		String lockId = lockIdGenerator.generateId().toString();
		String result = redisOperate.setnxpx(lock, lockId, NXXX.NX, EXPX.PX, lockTimeout);
		if (null != result && result.equalsIgnoreCase(RedisConsts.OK))
			return lockId;
		return null;
	}
	
	public String lock(String lock, long timeout) {
		long begin = System.nanoTime();
		while (true) {
			String lockId = tryLock(lock);
			if (null != lockId)
				return lockId;
			
			long time = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - begin);
			if (time >= timeout)
				return null;
			// TODO:
			Thread.yield();
		}
	}
	
	public boolean unLock(String lock, String lockId) {
		return luaOperate.delIfEquals(lock, lockId);
	}
	
	public void setLockTimeout(int lockTimeout) {
		this.lockTimeout = lockTimeout;
	}
	
	public void setLuaOperate(LuaOperate luaOperate) {
		this.luaOperate = luaOperate;
	}
	
	public void setRedisOperate(RedisOperate redisOperate) {
		this.redisOperate = redisOperate;
	}
	
	public void setLockIdGenerator(IdGenerator lockIdGenerator) {
		this.lockIdGenerator = lockIdGenerator;
	}
}
