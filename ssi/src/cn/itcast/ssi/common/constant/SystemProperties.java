package cn.itcast.ssi.common.constant;

import java.util.Properties;

import cn.itcast.ssi.common.util.PropertiesLoadUtil;

/**
 * <p>系统常量</p>
 * 
 * @author lipp
 *
 */
public class SystemProperties extends Constants {
	
	/** 系统属性配置 */
	private static final Properties configProperties = PropertiesLoadUtil.configProperties;
	
	/** 系统配置：静态文件服务器地址 */
	public static final String STATIC_PATH;
	/** 系统配置：日志开关 */
	public static final String LOG_SWITCH;
	/** 系统配置：MD5加密前缀 */
	public static final String MD5_SUFFIX;
	
	
	static {
		STATIC_PATH = configProperties.getProperty(STATIC_PATH_KEY);
		LOG_SWITCH = configProperties.getProperty(LOG_SWITCH_KEY);
		MD5_SUFFIX = configProperties.getProperty(MD5_SUFFIX_KEY);
	}
}
