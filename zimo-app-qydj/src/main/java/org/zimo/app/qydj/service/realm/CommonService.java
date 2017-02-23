package org.zimo.app.qydj.service.realm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zimo.app.qydj.service.realm.courier.CourierService;
import org.zimo.app.qydj.service.realm.user.UserService;
import org.zimo.app.qydj.storage.redis.QydjLuaOperate;
import org.zimo.app.qydj.storage.redis.RedisCache;

@Service
public class CommonService extends RedisCache{
	
	@Resource
	private QydjLuaOperate luaOperate;
	@Resource
	private CourierService courierService;
	@Resource
	private UserService userService;
	/**
	 * 登陆
	 * @param mobile
	 * @param password
	 */
	public void login(String mobile, String password) {
		//MemUser memUser = userService.getUserByMobile(mobile);
		
	}

}
