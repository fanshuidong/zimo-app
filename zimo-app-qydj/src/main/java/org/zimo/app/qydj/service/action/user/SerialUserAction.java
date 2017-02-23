package org.zimo.app.qydj.service.action.user;

import org.zimo.app.qydj.service.action.UserAction;

public abstract class SerialUserAction extends UserAction {

	@Override
	public boolean serial() {
		return true;
	}
}
