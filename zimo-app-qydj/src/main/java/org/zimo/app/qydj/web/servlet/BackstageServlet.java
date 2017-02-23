package org.zimo.app.qydj.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zimo.app.qydj.service.action.BackstageAction;
import org.zimo.app.qydj.service.action.CommonAction;
import org.zimo.app.qydj.web.IrisDispatcher;
import org.zimo.app.qydj.web.QydjParams;
import org.zimo.app.qydj.web.session.IrisSession;
import org.zimo.core.exception.IllegalConstException;

/**
 * 后台
 * @author 樊水东
 * 2017年2月23日
 */
public class BackstageServlet extends IrisDispatcher<IrisSession, BackstageAction>{

	private static final long serialVersionUID = -954074316337614528L;

	public BackstageServlet(){
		super("org.zimo.app.qydj.service.action.backstage");
	}
	
	@Override
	protected IrisSession buildSession(HttpServletRequest req, HttpServletResponse resp) {
		return new IrisSession(req, resp);
	}

	@Override
	protected void receive(IrisSession session) {
		BackstageAction action = actions.get(session.getKVParam(QydjParams.ACTION));
		if (null == action) 
			throw IllegalConstException.errorException(QydjParams.ACTION);
		action.execute(session);
	}

}
