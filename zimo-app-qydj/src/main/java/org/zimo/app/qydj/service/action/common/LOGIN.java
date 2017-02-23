package org.zimo.app.qydj.service.action.common;

import org.zimo.app.qydj.service.action.CommonAction;
import org.zimo.app.qydj.web.QydjParams;
import org.zimo.app.qydj.web.session.IrisSession;
import org.zimo.core.service.bean.Result;

/**
 * 登陆
 * @author 樊水东
 * 2017年2月22日
 */
public class LOGIN extends CommonAction{

	@Override
	protected String execute0(IrisSession session) {
		String mobile = session.getKVParam(QydjParams.MOBILE);
		String password = session.getKVParam(QydjParams.PASSWORD);
		commonService.login(mobile,password);
		return Result.jsonSuccess();
	}

}
