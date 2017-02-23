package org.zimo.app.qydj.web.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zimo.app.qydj.web.session.IrisSession;
import org.zimo.core.exception.IllegalConstException;

public interface ErrorHandler {
	
	/**
	 * 当参数不存在时的处理回调
	 * 
	 * @param session
	 */
	void onParamNull(IrisSession session, IllegalConstException exception);

	/**
	 * 当参数错误(包括字符串长度不对，类型转换错误等)时的处理回调
	 * 
	 * @param session
	 */
	void onParamError(IrisSession session, IllegalConstException exception);
	
	/**
	 * 当服务器出现异常错误时被调用
	 * 
	 * @param session
	 */
	void onServerError(IrisSession session, Exception exception);
	
	class DefaultErrorHandler implements ErrorHandler {
		private static final Logger logger = LoggerFactory.getLogger(DefaultErrorHandler.class);
		@Override
		public void onParamNull(IrisSession session, IllegalConstException e) {
			session.write(e.getMessage());
		}

		@Override
		public void onParamError(IrisSession session, IllegalConstException e) {
			session.write(e.getMessage());
		}
		
		@Override
		public void onServerError(IrisSession session, Exception e) {
			logger.error("Server exception!", e);
			session.write(e.getMessage());
		}
	}

}
