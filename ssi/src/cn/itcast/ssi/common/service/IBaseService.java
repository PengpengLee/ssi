package cn.itcast.ssi.common.service;

/**
 * <p>服务层父接口</p>
 * 所有服务层接口都必须继承本接口，所有子接口中定义的 方法必须显式的抛出异常，
 * 并由控制层捕获
 * 
 * @author lipp
 * @date 2015-01-18
 *
 */
public interface IBaseService {
	
	/**
	 * <p>属性拷贝</p>
	 * 1.当源对象为null时抛出异常；<br>
	 * 2.当源对象和目标对象包含同名但不同类型属性时抛出异常；<br>
	 * 
	 * @param source 源对象
	 * @param target 目标对象
	 */
	public void copyPropertis(Object source, Object target) throws Exception;
	
	/**
	 * <p>生成32位UUID字符转并转成大写</p>
	 * 
	 * @return 返回32位UUID字符串
	 */
	public String newEntityKey() ;

}
