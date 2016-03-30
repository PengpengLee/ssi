package cn.itcast.ssi.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import cn.itcast.ssi.common.dao.IBaseDao;

import com.ibatis.sqlmap.client.SqlMapExecutor;

@SuppressWarnings("unchecked")
public class IBatisEntityDaoImpl<T extends Serializable, PK extends Serializable>
		extends SqlMapClientDaoSupport implements IBaseDao {
	
	protected Class<T> entityClass;
	
	/**
	 * <p>数据访问层基类构造方法</p>
	 * 在此方法中获取实体类型
	 * 
	 * @author lipp
	 * @date 2015-01-18
	 * 
	 */
	public IBatisEntityDaoImpl(){
		Class<?> clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();
			if (types != null && types.length > 0) {
				this.entityClass = (Class<T>) types[0];
			}
		}
	}
	
	@Override
	public Object saveEntity(String xmlId, Serializable entity) {
		return getSqlMapClientTemplate().insert(xmlId, entity);
	}
	
	@Override
	public int deleteEntity(String xmlId, Object params) {
		return getSqlMapClientTemplate().delete(xmlId, params);
	}

	@Override
	public int deleteEntityById(String xmlId, Serializable id) {
		return getSqlMapClientTemplate().delete(xmlId, id);
	}
	
	@Override
	public int updateEntity(String xmlId, Object params) {
		return getSqlMapClientTemplate().update(xmlId, params);
	}
	
	@Override
	public int updateEntityById(String xmlId, Serializable id) {
		return getSqlMapClientTemplate().update(xmlId, id);
	}
	
	@Override
	public T getEntityById(String xmlId, Serializable id) {
		return (T) getSqlMapClientTemplate().queryForObject(xmlId, id);
	}
	
	@Override
	public T getEntity(String xmlId, Object params) {
		return (T) getSqlMapClientTemplate().queryForObject(xmlId, params);
	}

	@Override
	public List<T> getList(String xmlId, Object obj) {
		return getSqlMapClientTemplate().queryForList(xmlId, obj);
	}
	
	@Override
	public int getListCount(String xmlId, Object obj) {
		Object o = getSqlMapClientTemplate().queryForObject(xmlId, obj);
		if(o != null)
			return Integer.parseInt(o + "");
		return 0;
		
	}
	
	public List<?> getAll(String xmlId, Object obj) {
		return getSqlMapClientTemplate().queryForList(xmlId, obj);
	}
	
	/**
	 * <p>分页获取数据列表</p>
	 * 
	 * @param xmlId
	 * @param obj
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	public List<T> getAll(String xmlId, Object obj, int skipResults,
			int maxResults) {
		List<T> list = getSqlMapClientTemplate().queryForList(xmlId, obj,
				skipResults, maxResults);
		return list;
	}

	/**
	 * <p>批处理操作：更新</p>
	 * 
	 * @param list 要执行的数据列表
	 * @param xmlId 要执行的sql
	 * @example 示例：<br>
	 * 	<p><pre>
	 *	// 要批量更新的数据列表
	 *	List<Entity> list = new ArrayList<Entity>();
	 *	// 要执行的sql
	 *	String xmlId = "namespace.insertEntity";
	 *	// 执行批处理的方法
	 *	xxxDAO.batchUpdate(xmlId, list);
	 *  </pre></p>
	 */
	public void batchUpdate(String xmlId, List<?> list) throws Exception {
		final List<?> newList = list;
		final String newXml = xmlId;
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<T>() {
			@Override
			public T doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Object t : newList) {
					executor.update(newXml, t);
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	/**
	 * <p>批处理操作：删除</p>
	 * 
	 * @param list 要执行的数据列表
	 * @param xmlId 要执行的sql
	 * @example 示例：<br>
	 * 	<p><pre>
	 *	// 要批量更新的数据列表
	 *	List<Entity> list = new ArrayList<Entity>();
	 *	// 要执行的sql
	 *	String xmlId = "namespace.insertEntity";
	 *	// 执行批处理的方法
	 *	xxxDAO.batchUpdate(xmlId, list);
	 *  </pre></p>
	 */
	public void batchDelete(String xmlId, List<?> list) throws Exception {
		final List<?> newList = list;
		final String newXml = xmlId;
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<T>() {
			@Override
			public T doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Object t : newList) {
					executor.delete(newXml, t);
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	/**
	 * <p>批处理操作：插入</p>
	 * 
	 * @param list 要执行的数据列表
	 * @param xmlId 要执行的sql
	 * @example 示例：<br>
	 * 	<p><pre>
	 *	// 要批量插入的数据列表
	 *	List<Entity> list = new ArrayList<Entity>();
	 *	// 要执行的sql
	 *	String xmlId = "namespace.insertEntity";
	 *	// 执行批处理的方法
	 *	xxxDAO.batchInsert(xmlId, list);
	 *  </pre></p>
	 */
	public void batchInsert(String xmlId, List<?> list) throws Exception {
		final List<?> newList = list;
		final String newXml = xmlId;
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<T>() {
			@Override
			public T doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Object t : newList) {
					executor.insert(newXml, t);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	/**
	 * <p>
	 * 获取数据库当前时间戳，只对oracle有效。
	 * </p>
	 * 返回值为java.sql.Timestamp类型<br>
	 * 
	 * @return
	 */
	public Timestamp getSysTimeStamp() {
		return (Timestamp) getSqlMapClientTemplate().queryForObject(
				"common.getSysTimeStamp", null);
	}

	/**
	 * <p>
	 * 获取数据库当前时间，精确到秒，只对oracle有效。
	 * </p>
	 * 返回值为java.util.Date类型<br>
	 * 
	 * @return
	 */
	public Date getSysDate() {
		return (Date) getSqlMapClientTemplate().queryForObject(
				"common.getSysDate", null);
	}

}
