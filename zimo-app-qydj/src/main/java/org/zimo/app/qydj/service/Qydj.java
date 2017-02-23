package org.zimo.app.qydj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zimo.core.App;

public class Qydj extends App {
	
	private static final Logger logger = LoggerFactory.getLogger(Qydj.class);
	
	//private JmsService jmsService;
	//private AliyunService aliyunService;
	//private WyyxService wyyxService;
	
	public Qydj(String name) {
		super(name);
	}

	@Override
	public void bootstrap() {
		logger.info("App - {} startup!", name);
		
		// init jms service
		//this.jmsService.init(AppConfig.getEnv());
		// init aliyun service
		//this.aliyunService.init();
		// init wyyx service
		//this.wyyxService.init();
	}

	@Override
	public void stop() {
		logger.info("App - {} shutdown!", name);
	}
}
