package org.zimo.app.qydj.service.realm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zimo.app.qydj.service.realm.courier.CourierService;
import org.zimo.app.qydj.service.realm.user.User;
import org.zimo.app.qydj.service.realm.user.UserService;
import org.zimo.app.qydj.storage.domain.MemUser;
import org.zimo.app.qydj.storage.mybatis.mapper.MemUserMapper;
import org.zimo.app.qydj.storage.redis.QydjLuaOperate;
import org.zimo.app.qydj.storage.redis.RedisCache;
import org.zimo.app.qydj.web.QydjCode;
import org.zimo.core.service.bean.Result;

@Service
public class CommonService extends RedisCache{
	
	@Resource
	private QydjLuaOperate luaOperate;
	@Resource
	private CourierService courierService;
	@Resource
	private UserService userService;
	@Resource
	private MemUserMapper memUserMapper;
	/**
	 * 登陆
	 * @param mobile
	 * @param password
	 */
	public void login(String mobile, String password) {
		//MemUser memUser = userService.getUserByMobile(mobile);
		
	}
	/**
	 * 注册
	 * @param mobile
	 * @param sex
	 * @param password
	 * @param nickName
	 * @param birthday
	 * @return
	 */
	public String register(String mobile, int sex, String password, String nickName, String birthday) {
		User user = userService.getUserByMobile(mobile);
		if(user != null)
			return Result.jsonError(QydjCode.MOBILE_ALREADY_REGISTER);
		MemUser memUser = new MemUser(mobile, password, nickName, sex, birthday);
		memUserMapper.insert(memUser);
		flushHashBean(memUser);
		return Result.jsonSuccess(memUser);
	}

}
