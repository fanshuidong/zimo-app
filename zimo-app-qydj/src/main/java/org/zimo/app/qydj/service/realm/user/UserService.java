package org.zimo.app.qydj.service.realm.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zimo.app.qydj.service.realm.courier.CourierService;
import org.zimo.app.qydj.storage.domain.MemUser;
import org.zimo.app.qydj.storage.mybatis.mapper.MemUserMapper;
import org.zimo.app.qydj.storage.redis.QydjLuaOperate;
import org.zimo.app.qydj.storage.redis.RedisCache;
import org.zimo.app.qydj.storage.redis.UserKeyGenerator;

@Service
public class UserService extends RedisCache{
	
	@Resource
	private QydjLuaOperate luaOperate;
	@Resource
	private CourierService courierService;
	@Resource
	private MemUserMapper memUserMapper;

	/**
	 * 通过 token 获取用户手机号
	 * 
	 * @param token
	 * @return
	 */
	public String getMobileByToken(String token) {
		return redisOperate.hget(UserKeyGenerator.tokenUserMapKey(), token);
	}
	
	/**
	 * 通过用户手机获取用户信息
	 * @param userId
	 * @return
	 */
	public User getUserByMobile(String mobile) {
		MemUser memUser = getHashBean(new MemUser(mobile));
		if(null == memUser){
			memUser = memUserMapper.getByMobile(mobile);
			if(null == memUser)
				return null;
			flushHashBean(memUser);
		}
		return new User(memUser); 
	}
}
