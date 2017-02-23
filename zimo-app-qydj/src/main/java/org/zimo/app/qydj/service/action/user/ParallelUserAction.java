package org.zimo.app.qydj.service.action.user;

import org.zimo.app.qydj.service.action.UserAction;

public abstract class ParallelUserAction extends UserAction {

	@Override
	public boolean serial() {
		return false;
	}
}
