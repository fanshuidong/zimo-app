package org.zimo.app.qydj.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zimo.app.qydj.common.model.Config;
import org.zimo.app.qydj.common.model.Env;
import org.zimo.util.common.IpUtil;
import org.zimo.util.common.SerializeUtil;

public class ZkUtil {

	private static final Logger logger = LoggerFactory.getLogger(ZkUtil.class);

	@SuppressWarnings("unchecked")
	public static Map<String, String> loadConfiguration(ZkClient zkClient, Env env) { 
		byte[] buffer = zkClient.readData(Config.ZK_CONFIGURATION_PATH, true);
		if (null == buffer) {
			logger.warn("Zookeeper /configuration/jilu has no data to read!");
			return new HashMap<String, String>();
		}
		Map<String, String> map = SerializeUtil.JsonUtil.GSON.fromJson(new String(buffer), Map.class);
		for (Entry<String, String> entry : map.entrySet()) {
			StringBuilder builder = new StringBuilder();
			builder.append(entry.getValue()).append("-").append(env.name().toLowerCase());
			if (env == Env.LOCAL) {
				String macAddr = IpUtil.getMacAddress();
				if (null != macAddr)
					builder.append("-").append(macAddr);
			}
			map.put(entry.getKey(), builder.toString());
		}
		logger.info("JiLu zookeeper configuration load success, load {} queue names -- {}", map.size(), map);
		return map;
	}
}
