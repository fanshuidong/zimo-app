package org.zimo.app.qydj.service.action;

import org.zimo.app.qydj.web.session.UserSession;

public abstract class UserAction implements IAction<UserSession>{

	@Override
	public void execute(UserSession session) {
		session.write(execute0(session));
	}
	
	protected abstract String execute0(UserSession session);

	public  abstract boolean serial();
}
