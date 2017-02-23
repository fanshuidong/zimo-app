package org.zimo.app.qydj.web;

import org.zimo.core.service.locale.ICode;
import org.zimo.util.lang.StringUtil;

/**
 * 注意 101 ~ 200 已经被 {@link JiLuParams} 中的各种网络参数定义了
 * 
 * @author Ahab
 */
public enum QydjCode implements ICode {
	
	CAPTCHA_GET_CD(201, "captcha get frequently"),
	CAPTCHA_COUNT_LIMIT(202, "captcha count limit"),
	CAPTCHA_ERROR(203, "captcha error");
	
	private int code;
	private String defaultVaule;
	
	private QydjCode(int code, String defaultValue) {
		this.code = code;
		this.defaultVaule = defaultValue;
	}

	@Override
	public int constId() {
		return this.code;
	}

	@Override
	public String key() {
		return CODE_PREFIX + name();
	}

	@Override
	public String parse(String value) {
		this.defaultVaule = StringUtil.trimWhitespace(value);
		return defaultVaule;
	}

	@Override
	public String defaultValue() {
		return this.defaultVaule;
	}
}
