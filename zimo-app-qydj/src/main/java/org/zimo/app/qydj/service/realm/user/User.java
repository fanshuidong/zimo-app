package org.zimo.app.qydj.service.realm.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zimo.app.qydj.common.Beans;
import org.zimo.app.qydj.storage.domain.MemUser;
import org.zimo.app.qydj.storage.redis.UserKeyGenerator;

public class User implements Beans{
	
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	private String lock;
	private MemUser memUser;

	public User(MemUser memUser) {
		this.memUser = memUser;
		this.lock = UserKeyGenerator.userLockKey(memUser.getMobile());
	}
	
	public MemUser getMemUser() {
		return memUser;
	}

	public String tryLock() {
		return distributeLock.tryLock(lock);
	}

}
