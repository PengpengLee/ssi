package cn.itcast.ssi.common.interfaces;

import cn.itcast.ssi.common.constant.Constants;
import cn.itcast.ssi.common.entity.Log;
import cn.itcast.ssi.common.service.impl.BaseServiceImpl;
import cn.itcast.ssi.common.util.PropertiesLoadUtil;

/**
 * <p>日志工具接口，重新实现该类以改变日志行为</p>
 * 
 * @author lipp
 * @date 2015-01-21
 *
 */
public abstract class ISystemLogger extends BaseServiceImpl {
	
	/** 日志开关：为true时写入数据库，为false时打印到控制台，默认为false */
	public static boolean LOG_SWITCH = false;
	
	
	public ISystemLogger(){
		/* 从系统配置中读取日志开关 */
		LOG_SWITCH = Boolean.parseBoolean(
				PropertiesLoadUtil.configProperties.get(Constants.LOG_SWITCH_KEY) + "");
	}
	
	/**
	 * <p>一般信息</p>
	 * @param log
	 * @throws Exception
	 */
	public abstract void info(Log log) throws Exception;

	/**
	 * <p>错误信息</p>
	 * @param log
	 * @throws Exception
	 */
	public abstract void error(Log log) throws Exception;

	/**
	 * <p>警告信息</p>
	 * @param log
	 * @throws Exception
	 */
	public abstract void warn(Log log) throws Exception;
	
}
