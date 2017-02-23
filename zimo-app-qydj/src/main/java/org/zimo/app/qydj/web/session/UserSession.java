package org.zimo.app.qydj.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zimo.app.qydj.service.realm.user.User;
import org.zimo.core.exception.IrisRuntimeException;

public class UserSession extends IrisSession {
	
	private User user;
	public UserSession(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	public User getUser() {
		return user;
	}
	
	public void bind(User user) { 
		if (null != this.user) 
			throw new IrisRuntimeException("UserSession already bind with a merchant!");
		this.user = user;
	}
}
