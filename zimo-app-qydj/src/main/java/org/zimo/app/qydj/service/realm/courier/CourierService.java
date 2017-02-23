package org.zimo.app.qydj.service.realm.courier;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zimo.app.qydj.common.AppConfig;
import org.zimo.app.qydj.common.model.AccountType;
import org.zimo.app.qydj.common.model.Env;
import org.zimo.app.qydj.storage.redis.CommonKeyGenerator;
import org.zimo.app.qydj.storage.redis.QydjLuaOperate;
import org.zimo.app.qydj.web.QydjCode;
import org.zimo.core.service.bean.Result;
import org.zimo.core.service.locale.ICode;
import org.zimo.util.common.KeyUtil;

@Service
public class CourierService {

	@Resource
	private QydjLuaOperate luaOperate;
	
	/**
	 * 生成验证码: 验证码和 账号对应
	 * 
	 * @param type
	 * @param account
	 * @return
	 */
	public String generateCaptcha(AccountType type, String account) {
		String captchaKey = CommonKeyGenerator.accountCaptchaKey(type, account);
		String captchaCountKey = CommonKeyGenerator.accountCaptchaCountKey(type, account);
		
		// 生成验证码并且缓存验证码
		String captcha = KeyUtil.randomCaptcha(AppConfig.getCaptchaDigit());
		long flag = luaOperate.recordCaptcha(captchaKey, captchaCountKey, captcha, 
				AppConfig.getCaptchaLifeTime(), AppConfig.getCaptchaCountMaximum(), AppConfig.getCaptchaCountLifeTime());
		if (-1 == flag) 
			return Result.jsonError(QydjCode.CAPTCHA_GET_CD);
		if (-2 == flag)
			return Result.jsonError(QydjCode.CAPTCHA_COUNT_LIMIT);
		Env env = AppConfig.getEnv();
		switch (env) {
		case LOCAL:											// 测试环境下直接返回验证码
		case TEST:
			return Result.jsonSuccess(captcha);				
		case ONLINE:										// 线上环境需要发送短信
			//jmsService.sendCaptchaMessage(type, account, captcha);
			return Result.jsonSuccess();					
		default:
			return Result.jsonError(ICode.Code.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 检查验证码
	 * 
	 * @param type
	 * @param account
	 * @return
	 */
	public boolean verifyCaptch(AccountType type, String account, String captch) {
		return luaOperate.delIfEquals(CommonKeyGenerator.accountCaptchaKey(type, account), captch);
	}
}
