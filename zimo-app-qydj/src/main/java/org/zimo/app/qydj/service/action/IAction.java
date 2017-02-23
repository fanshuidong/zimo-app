package org.zimo.app.qydj.service.action;

import org.zimo.app.qydj.common.Beans;
import org.zimo.app.qydj.web.session.IrisSession;

public interface IAction<SESSION extends IrisSession> extends Beans{

	void execute(SESSION session);
}
