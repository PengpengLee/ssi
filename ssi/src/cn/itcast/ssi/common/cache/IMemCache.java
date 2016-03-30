package cn.itcast.ssi.common.cache;

/**
 * Created by IntelliJ IDEA. 
 * change this template use File | Settings | File Templates.
 */
public interface IMemCache {

	/**
	 * 
	 * <P>
	 * 把要缓存的对象放到缓存中去
	 * </P>
	 * 
	 * @param key
	 *            缓存关键字
	 * @param obj
	 *            要缓存的对象
	 * @return
	 */
	public boolean add(String key, Object obj);

	/**
	 * 
	 * <P>
	 * 从缓存中取得缓存对象
	 * </P>
	 * 
	 * @param key
	 *            缓存关键字
	 * @return
	 */
	public Object get(String key);

	/**
	 * 
	 * <P>
	 * 删除缓存对象
	 * </P>
	 * 
	 * @param key
	 * @return
	 */
	public boolean remove(String key);

	/**
	 * 
	 * <P>
	 * 替换缓存对象
	 * </P>
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean replace(String key, Object obj);

	/**
	 * 
	 * <P>
	 * 清除缓存的所有对象信息
	 * </P>
	 */
	public void removeAll();

	/**
	 * <p>
	 * 把要缓存的对象放到缓存中去
	 * </p>
	 * 
	 * @param key
	 *            缓存关键字。<br>
	 * @param o
	 *            要缓存的对象。<br>
	 * @param mins
	 *            缓成的时间，单位分钟。<br>
	 * @return
	 */
	public boolean putCache(String key, Object o, int mins);

}
