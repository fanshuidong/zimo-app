package org.zimo.app.qydj.service.action.common;

import org.zimo.app.qydj.common.model.AccountType;
import org.zimo.app.qydj.service.action.CommonAction;
import org.zimo.app.qydj.web.QydjParams;
import org.zimo.app.qydj.web.session.IrisSession;

/**
 * 获取验证码(手机和邮箱获取验证码的规则是一样的)
 * 
 * @author ahab
 */
public class CAPTCHA_GET extends CommonAction {
	
	@Override
	protected String execute0(IrisSession session) {
		AccountType type = AccountType.match(session.getKVParamOptional(QydjParams.TYPE));
		String account = type == AccountType.MOBILE ? session.getKVParam(QydjParams.MOBILE) : session.getKVParam(QydjParams.EMAIL);
		return courierService.generateCaptcha(type, account);
	}
}
