package org.zimo.app.qydj.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zimo.app.qydj.service.action.CommonAction;
import org.zimo.app.qydj.web.IrisDispatcher;
import org.zimo.app.qydj.web.QydjParams;
import org.zimo.app.qydj.web.session.IrisSession;
import org.zimo.core.exception.IllegalConstException;

@SuppressWarnings("serial")
public class CommonServlet extends IrisDispatcher<IrisSession, CommonAction>{

	public CommonServlet(){
		super("org.zimo.app.qydj.service.action.common");
	}

	@Override
	protected IrisSession buildSession(HttpServletRequest req, HttpServletResponse resp) {
		return new IrisSession(req, resp);
	}

	@Override
	protected void receive(IrisSession session) {
		CommonAction action = actions.get(session.getKVParam(QydjParams.ACTION));
		if (null == action) 
			throw IllegalConstException.errorException(QydjParams.ACTION);
		action.execute(session);
	}

}
