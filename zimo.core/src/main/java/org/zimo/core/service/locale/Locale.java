package org.zimo.core.service.locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zimo.core.service.locale.ICode.Code;
import org.zimo.core.util.IrisProperties;
import org.zimo.core.util.IrisPropertiesUtil;

/**
 * 国际化类，主要管理所有需要国际化的字符串
 * 
 * @author ahab
 */
public class Locale {
	
	private static final Logger logger = LoggerFactory.getLogger(Locale.class);
	
	public static final Locale DEFAULT_LANG					= new Locale();

	private IrisProperties langs;
	
	private Locale() {
		langs = IrisPropertiesUtil.load(Locale.class, "/locale/locale_default.properties");
	}
	
	public Locale(String langConfigurationLocation) {
		this();
		IrisPropertiesUtil.load(langs, langConfigurationLocation);
	}
	
	/**
	 * 主要负责将需要国际化的字符串解析到应用
	 * 
	 */
	public final void init() { 
		// 解析 Code 语言文件
		for (Code code : Code.values())
			code.parse(langs.getOptional(code));
		init(langs);
		logger.info("Local init success");
	}
	
	protected void init(IrisProperties langs) {
		// do nothing
	}
}
