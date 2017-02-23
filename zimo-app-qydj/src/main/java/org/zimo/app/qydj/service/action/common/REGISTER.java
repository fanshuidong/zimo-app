package org.zimo.app.qydj.service.action.common;

import org.zimo.app.qydj.service.action.CommonAction;
import org.zimo.app.qydj.web.QydjParams;
import org.zimo.app.qydj.web.session.IrisSession;

/**
 * app用户注册
 * @author 樊水东
 * 2017年2月23日
 */
public class REGISTER extends CommonAction{

	@Override
	protected String execute0(IrisSession session) {
		String mobile = session.getKVParam(QydjParams.MOBILE);
		int sex = session.getKVParam(QydjParams.SEX);
		String password = session.getKVParam(QydjParams.PASSWORD);
		String nickName = session.getKVParam(QydjParams.NICKNAME);
		String birthday = session.getKVParam(QydjParams.BIRTHDAY);
		return commonService.register(mobile,sex,password,nickName,birthday);
	}

}
