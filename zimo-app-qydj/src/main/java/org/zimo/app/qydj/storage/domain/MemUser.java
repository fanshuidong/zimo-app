package org.zimo.app.qydj.storage.domain;

import org.zimo.app.qydj.storage.redis.UserKeyGenerator;
import org.zimo.redis.RedisHashBean;
import org.zimo.util.lang.DateUtils;

public class MemUser implements RedisHashBean{

	private String mobile;
	private String password;
	private String nickName;
	private int sex;
	private String birthday;
	private int created;
	private int updated;
	private int lastLoginTime;
	// 只存在 redis 的字段
	private String token;
	
	public MemUser(String mobile, String password, String nickName, int sex, String birthday) {
		this.mobile = mobile;
		this.password = password;
		this.nickName = nickName;
		this.sex = sex;
		this.birthday = birthday;
		int time = DateUtils.currentTime();
		this.created = time;
		this.updated = time;
	}

	public MemUser(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public int getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String redisKey() {
		return UserKeyGenerator.mobileUserMapKey(mobile);
	}
	
	
}
