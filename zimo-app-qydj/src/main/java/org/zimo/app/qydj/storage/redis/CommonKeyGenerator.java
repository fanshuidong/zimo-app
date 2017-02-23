package org.zimo.app.qydj.storage.redis;

import java.text.MessageFormat;

import org.zimo.app.qydj.common.model.AccountType;

public final class CommonKeyGenerator {
	
	private static final String ACCOUNT_CAPTCHA						= "string:tmp:account:{0}:{1}:captcha";					// 账号 - 验证码 对应关系；0-表示账号类型，1-表示账号值
	private static final String ACCOUNT_CAPTCHA_COUNT				= "string:tmp:account:{0}:{1}:captcha:count";			// 账号 - 验证码获取次数 对应关系；0-表示账号类型，1-表示账号值
	
	public static final String accountCaptchaKey(AccountType type, String account) {
		return MessageFormat.format(ACCOUNT_CAPTCHA, type.name().toLowerCase(), account);
	}
	
	public static final String accountCaptchaCountKey(AccountType type, String account){
		return MessageFormat.format(ACCOUNT_CAPTCHA_COUNT, type.name().toLowerCase(), account);
	}
}
