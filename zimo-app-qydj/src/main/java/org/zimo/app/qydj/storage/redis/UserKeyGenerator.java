package org.zimo.app.qydj.storage.redis;

import java.text.MessageFormat;

/**
 * 和用户相关的所有主键，主键中有用户的 ID
 * 
 * @author Ahab
 */
public final class UserKeyGenerator {
	
	private static final String USER_LOCK							= "string:tmp:user:{0}:lock";						    // 商户操作锁
	
	private static final String MOBILE_USER_MAP						= "hash:cache:mobile:{0}:user";							// mobile - user 映射
	private static final String TOKEN_USER_MAP						= "hash:cache:token:user";
	private static final String USER_DATA							= "hash:db:user:{0}";
	
	public static final String mobileUserMapKey(String mobile){
		return MessageFormat.format(MOBILE_USER_MAP, mobile);
	}
	
	public static final String tokenUserMapKey() { 
		return TOKEN_USER_MAP;
	}
	
	public static final String userLockKey(String mobile){
		return MessageFormat.format(USER_LOCK, mobile);
	}
	
	public static final String userDataKey(long userId){
		return MessageFormat.format(USER_DATA, String.valueOf(userId));
	}
	
	
}
