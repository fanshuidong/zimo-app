package org.zimo.app.qydj.web.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.zimo.app.qydj.web.QydjParams;
import org.zimo.core.consts.IrisConst;
import org.zimo.core.consts.IrisStrConst;
import org.zimo.core.exception.IllegalConstException;
import org.zimo.core.exception.IrisRuntimeException;
import org.zimo.util.common.ConstsPool;
import org.zimo.util.common.MiMeType;
import org.zimo.util.lang.StringUtil;

/**
 * 默认使用 json
 * 
 * @author fansd
 */
public class IrisSession{

	private HttpServletRequest request;
	private HttpServletResponse response;

	public IrisSession(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		response.setHeader(HttpHeaders.CONTENT_TYPE, MiMeType.TEXT_JSON_UTF_8);
		response.setCharacterEncoding(ConstsPool.UTF_8.name());
	}

	/**
	 * 获取 key - value 形式的键值对，URL 之后的参数，以及 x-www-form-urlencoded 编码的参数可以使用此种方式来获取
	 * 
	 * @param constant
	 * @return
	 * @throws IllegalConstException
	 */
	public <T> T getKVParam(IrisConst<T> constant) throws IllegalConstException {
		String val = request.getParameter(constant.key());
		if (null == val)
			throw IllegalConstException.nullException("param [" + constant.key() + "] missing!", constant);
		try {
			return constant.parse(val);
		} catch (Exception e) {
			throw IllegalConstException.exception("param [" + constant.key() + "] error!", constant);
		}
	}
	
	/**
	 * 和 {@link #getKVParam(IrisConst)} 的不同之处在于如果参数不存在或者错误，不会抛出异常，而是返回 {@link IrisConst} 的默认值
	 * 
	 * @param constant
	 * @return
	 */
	public <T> T getKVParamOptional(IrisConst<T> constant) {
		try {
			return getKVParam(constant);
		} catch (IllegalConstException e) {
			return constant.defaultValue();
		}
	}
	
	/**
	 * 将参数转换成普通的 java 对象来调用，一般适用在 xml 或者 json 格式的调用，当然 key-value 形式的调用也可以转换成普通 java 对象
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> T getObjParam(Class<T> clazz) {
		return null;
	}

	public IrisSession addHeader(String name, String value) {
		response.addHeader(name, value);
		return this;
	}

	public IrisSession setHeader(String name, String value) {
		response.setHeader(name, value);
		return this;
	}
	
	public String getHeader(IrisStrConst constant) {
		String value = request.getHeader(constant.key());
		if (!StringUtil.hasText(value))
			throw IllegalConstException.errorException(QydjParams.TOKEN);
		return constant.parse(value);
	}

	public IrisSession write(String reply) {
		try {
			response.getWriter().write(reply);
		} catch (IOException e) {
			throw new IrisRuntimeException("Servlet Response writer failure!", e);
		}
		return this;
	}
	
	public IrisSession write(byte[] reply) {
		try {
			response.getOutputStream().write(reply);
		} catch (IOException e) {
			throw new IrisRuntimeException("Servlet Response writer failure!", e);
		}
		return this;
	}
}
