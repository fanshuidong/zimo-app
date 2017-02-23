package org.zimo.redis;

public interface RedisHashLuaSerializableBean extends RedisHashBean {
	
	String[] serializeToLuaParams();
}
