package org.zimo.app.qydj.storage.redis;

import org.zimo.app.qydj.common.bean.enums.QydjLuaCommand;
import org.zimo.redis.operate.lua.LuaOperate;

public class QydjLuaOperate extends LuaOperate {
 
	/**
	 * 替换 token，适用在 login(create也会触发 login)
	 * 
	 * @param merchantLockKey
	 * @param merchantDataKey
	 * @param tokenMerchantMapKey
	 * @param lockId
	 * @param token
	 * @param time
	 * @param merchantId
	 * @return
	 */
	public long tokenReplace(String merchantLockKey, String merchantDataKey, String tokenMerchantMapKey, 
			String lockId, String token, String time, String merchantId, String lockTimeout) { 
		return evalLua(QydjLuaCommand.TOKEN_REPLACE.name(), 3, merchantLockKey, merchantDataKey, tokenMerchantMapKey, lockId, token, time, merchantId, lockTimeout);
	}
}
