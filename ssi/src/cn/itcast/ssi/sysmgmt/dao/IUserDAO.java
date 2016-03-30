package cn.itcast.ssi.sysmgmt.dao;

import java.util.List;

import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.sysmgmt.entity.UserPO;
import cn.itcast.ssi.sysmgmt.vo.UserVO;


public interface IUserDAO {
	
	/**
	 * <p>保存用户对象</p>
	 * 
	 * @param userpo 封装用户数据的对象
	 * @return 返回保存后数据的主键
	 * @throws Exception
	 */
	public String saveUser(UserPO userpo) throws Exception;
	
	/**
	 * <p>用户登录按照用户名密码查询用户</p>
	 * 
	 * @param username
	 * @param password
	 * @return 返回查询结果。用户不存在返回null
	 * @author lipp
	 * @date 2014-11-18
	 * @throws Exception
	 */
	public UserPO getUserForLogin(String username, String password) throws Exception;
	
	/**
	 * <p>根据用户ID获取用户对象</p>
	 * 
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
	public UserPO getUserById(String userId) throws Exception;
	
	/**
	 * <p>根据属性获取User对象</p>
	 * @param uservo
	 * @return
	 * @throws Exception
	 */
	public List<UserPO> getUserByProperty(UserVO uservo) throws Exception;
	
	/**
	 * <p>分页：获取数据列表，支持模糊查询</p>
	 * @param pageData 分页信息
	 * @param flag 查询是否为某一角色用户
	 * @param role_id 查询字段值
	 * @param ss 查询字段值
	 * @return
	 * @throws Exception
	 */
	public List<UserPO> getPageListData(PageListData pageData, boolean flag, 
			String role_id, String ss) throws Exception;
	
	/**
	 * <p>分页：获取数据总数，支持模糊查询</p>
	 * @param pageData 分页信息
	 * @param flag 查询是否为某一角色用户
	 * @param role_id 角色ID
	 * @param ss 查询字段值
	 * @return
	 */
	public int getPageListDataCount(PageListData pageData, boolean flag, 
			String role_id, String ss) throws Exception;

	/**
	 * <p>根据ID删除用户对象</p>
	 * @param id
	 * @return
	 */
	public int delUserById(String id) throws Exception;


}