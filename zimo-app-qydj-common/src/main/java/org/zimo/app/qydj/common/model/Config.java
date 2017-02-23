package org.zimo.app.qydj.common.model;

public class Config {

	public static final String ZK_CONFIGURATION_PATH						= "/configuration/qydj";
	
	private static Env env;						

	public static Env getEnv() {
		return env;
	}
	
	public static void setEnv(String env) {
		Config.env = Env.match(env);
	}
}
