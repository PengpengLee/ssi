package cn.itcast.ssi.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * <p>数据访问层接口</p>
 * 所有数据访问层接口继承本接口
 * 
 * @author lipp
 * @date 2015-01-19
 *
 */
public interface IBaseDao {

	/**
	 * <p>保存实体</p>
	 * 
	 * @param xmlId 执行的sql的id
	 * @param obj 实现了<pre>java.io.Serializable</pre>接口的实体类对象，需要包含主键
	 * @return 返回结果不确定：保存后的主键/保存成功的数据条数/null
	 */
	public Object saveEntity(String xmlId, Serializable obj);

	/**
	 * <p>删除实体</p>
	 * 本方法根据提供的参数作为删除条件
	 * 
	 * @param xmlId 执行的sql的id
	 * @param params 参数对象
	 * @return 返回删除数据的条数
	 */
	public int deleteEntity(String xmlId,  Object params);
	
	/**
	 * <p>删除实体</p>
	 * 本方法根据实体类对象的id属性作为条件
	 * 
	 * @param xmlId 执行的sql的id
	 * @param obj 实现了<pre>java.io.Serializable</pre>接口的实体类对象id
	 * @return 返回删除数据的条数
	 */
	public int deleteEntityById(String xmlId, Serializable id);

	/**
	 * <p>更新实体</p>
	 * 本方法会更新提供的实体类对象的所有属性，实体类对象必须是持久态对象
	 * 
	 * @param xmlId 执行的sql的id
	 * @param params 参数
	 * @return 返回更新数据的条数
	 */
	public int updateEntity(String xmlId, Object params);
	
	/**
	 * <p>更新实体</p>
	 * 本方法会更新该id对应的数据
	 * 
	 * @param xmlId 执行的sql的id
	 * @param id 实现了<pre>java.io.Serializable</pre>接口的实体类对象的id
	 * @return 返回更新数据的条数
	 */
	public int updateEntityById(String xmlId, Serializable id);

	/**
	 * <p>获取实体</p>
	 * 本方法根据提供的实体类对象的id属性作为条件
	 * 
	 * @param xmlId 执行的sql的id
	 * @param id 实现了<pre>java.io.Serializable</pre>接口的实体类对象id
	 * @return 返回指定类型实体对象
	 */
	public <T> T getEntityById(String xmlId, Serializable id);
	
	/**
	 * <p>获取实体</p>
	 * 本方法根据提供参数作为条件
	 * 
	 * @param xmlId 执行的sql的id
	 * @param params 参数
	 * @return 返回指定类型实体对象
	 */
	public <T> T getEntity(String xmlId, Object params);
	
	/**
	 * <p>获取实体列表</p>
	 * 推荐使用本方法获取分页数据
	 * 
	 * @param xmlId 执行的sql的id
	 * @param params 查询条件，包含分页信息
	 * @return 返回指定类型实体对象列表
	 * @see {@link cn.itcast.ssi.common.entity.PageListData}
	 */
	public <T> List<T> getList(String xmlId, Object params);
	
	/**
	 * <p>获取实体列表数据总数</p>
	 * 推荐使用本方法获取总数据量
	 * 
	 * @param xmlId 执行的sql的id
	 * @param obj 查询条件，包含分页信息
	 * @return 返回指定类型实体对象列表
	 * @see {@link cn.itcast.ssi.common.entity.PageListData}
	 */
	public int getListCount(String xmlId, Object obj);
	
}
