package org.zimo.core.service.locale;

import org.zimo.core.consts.IrisConst;
import org.zimo.util.lang.StringUtil;

/**
 * 0 ~ 100 是 Iris 预留错误码，不要使用这些类型去定义业务错误码
 * 
 * @author Ahab
 */
public interface ICode extends IrisConst<String> {
	
	public static final String CODE_PREFIX					= "CODE_";
	
	enum Code implements ICode {
		
		OK(0, "success"),
		PARAM_LACK(1, "param - {0} lack"),
		PARAM_ERROR(2, "param - {0} error"),
		SYSTEM_ERROR(3, "system exception"),
		AUTH_FAIL(4, "auth fail"),
		REQUEST_FREQUENTLY(5, "request frequently"),
		TOKEN_INVALID(6, "token invalid"),
		DATA_CHANGED(7, "data changed"),
		
		UNIT_NOT_EXIST(50, "unit not exist");
		
		private int code;
		private String defaultVaule;
		
		private Code(int code, String defaultValue) {
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

}
