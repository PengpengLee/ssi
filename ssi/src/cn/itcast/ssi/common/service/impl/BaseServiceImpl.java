package cn.itcast.ssi.common.service.impl;

import java.text.SimpleDateFormat;

import cn.itcast.ssi.common.service.IBaseService;
import cn.itcast.ssi.common.util.FWBeanUtils;

public class BaseServiceImpl implements IBaseService {
	
	/** 格式化日期对象 */
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * <p>属性拷贝</p>
	 * 1.当源对象为null时抛出异常；<br>
	 * 2.当源对象和目标对象包含同名但不同类型属性时抛出异常；<br>
	 * 
	 * @param source 源对象
	 * @param target 目标对象
	 */
	public void copyPropertis(Object source, Object target) throws Exception{
		FWBeanUtils.copyPropertis(source, target);
	}
	
	/**
	 * <p>生成32位UUID字符转并转成大写</p>
	 * 
	 * @return 返回32位UUID字符串
	 */
	public String newEntityKey() {
		return FWBeanUtils.newEntityKey();
	}

}
