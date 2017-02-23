package org.zimo.app.qydj.service;

import org.zimo.app.qydj.web.QydjCode;
import org.zimo.core.service.locale.Locale;
import org.zimo.core.util.IrisProperties;

public class QydjLang extends Locale {
	
	public QydjLang(String langConfigurationLocation) {
		super(langConfigurationLocation);
	}

	@Override
	protected void init(IrisProperties langs) {
		for (QydjCode code : QydjCode.values()) 
			code.parse(langs.getOptional(code));
	}
}
