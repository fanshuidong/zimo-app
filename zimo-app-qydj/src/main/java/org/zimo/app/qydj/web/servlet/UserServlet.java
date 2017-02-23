package org.zimo.app.qydj.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.zimo.app.qydj.service.action.UserAction;
import org.zimo.app.qydj.service.realm.user.User;
import org.zimo.app.qydj.service.realm.user.UserService;
import org.zimo.app.qydj.storage.redis.UserKeyGenerator;
import org.zimo.app.qydj.web.IrisDispatcher;
import org.zimo.app.qydj.web.QydjParams;
import org.zimo.app.qydj.web.session.UserSession;
import org.zimo.core.exception.IllegalConstException;
import org.zimo.core.service.bean.Result;
import org.zimo.core.service.locale.ICode;
import org.zimo.redis.operate.lock.DistributeLock;

public class UserServlet extends IrisDispatcher<UserSession, UserAction>{

	private static final long serialVersionUID = 4366690343926579205L;
	
	@Autowired
	private DistributeLock distributeLock;
	@Autowired
	private UserService userService;
	
	public UserServlet() {
		super("org.zimo.app.qydj.service.action.user");
	}

	@Override
	protected UserSession buildSession(HttpServletRequest req, HttpServletResponse resp) {
		return new UserSession(req, resp);
	}

	@Override
	protected void receive(UserSession session) {
		UserAction action = actions.get(session.getKVParam(QydjParams.ACTION));
		if(action == null)
			throw IllegalConstException.errorException(QydjParams.ACTION);
		String token = session.getKVParam(QydjParams.TOKEN);
		String mobile = userService.getMobileByToken(token);
		User user = null;
		if(null !=mobile){
			if(action.serial()){
				String lock = UserKeyGenerator.userLockKey(mobile);
				String lockId = distributeLock.tryLock(lock);
				if(null == lockId){
					//表示用户正在操作
					session.write(Result.jsonError(ICode.Code.REQUEST_FREQUENTLY));
					return;
				}
				try {
					user = userService.getUserByMobile(mobile);
					if(null == user || !user.getMemUser().getToken().equals(token)){
						session.write(Result.jsonError(ICode.Code.TOKEN_INVALID));
						return;
					}
					
					session.bind(user);
					action.execute(session);
				} finally {
					distributeLock.unLock(lock, lockId);
				}
			}else {
				user = userService.getUserByMobile(mobile);
				if(null == user || !user.getMemUser().getToken().equals(token)){
					session.write(Result.jsonError(ICode.Code.TOKEN_INVALID));
					return;
				}
				
				session.bind(user);
				action.execute(session);
			}
		}else 
			session.write(Result.jsonError(ICode.Code.TOKEN_INVALID));
	}
}
