package org.zimo.app.qydj.common;

import org.zimo.app.qydj.service.realm.CommonService;
import org.zimo.app.qydj.service.realm.courier.CourierService;
import org.zimo.app.qydj.service.realm.user.UserService;
import org.zimo.app.qydj.storage.mybatis.mapper.MemUserMapper;
import org.zimo.app.qydj.storage.redis.QydjLuaOperate;
import org.zimo.core.util.SpringContextUtil;
import org.zimo.redis.operate.lock.DistributeLock;
import org.zimo.util.network.http.HttpProxy;

public interface Beans {

	
	final QydjLuaOperate luaOperate = SpringContextUtil.getBean("luaOperate", QydjLuaOperate.class); 
	final CommonService commonService = SpringContextUtil.getBean("commonService", CommonService.class);
	final DistributeLock distributeLock = SpringContextUtil.getBean("distributeLock", DistributeLock.class);
	final CourierService courierService = SpringContextUtil.getBean("courierService", CourierService.class);
	final UserService userService = SpringContextUtil.getBean("userService", UserService.class);
	final MemUserMapper memUserMapper = SpringContextUtil.getBean("memUserMapper", MemUserMapper.class);

	final HttpProxy httpProxy = SpringContextUtil.getBean("httpProxy", HttpProxy.class);
	
}
