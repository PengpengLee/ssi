package cn.itcast.ssi.sysmgmt.dao;

import java.util.List;

import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.sysmgmt.entity.RolePO;
import cn.itcast.ssi.sysmgmt.entity.UserRolePO;


public interface IRoleDAO  {
	
	/**
	 * <p>保存角色对象</p>
	 * 
	 * @param RolePO 封装角色数据的对象
	 * @return 返回保存后数据的主键
	 * @throws Exception
	 */
	public String saveRole(RolePO RolePO) throws Exception;
	
	/**
	 * <p>保存用户角色对象</p>
	 * 
	 * @param userRolePO
	 * @return
	 * @throws Exception
	 */
	public String saveUserRole(UserRolePO userRolePO) throws Exception;
	
	/**
	 * <p>批量保存用户角色对象</p>
	 * 
	 * @param userRolePO
	 * @return
	 * @throws Exception
	 */
	public void batchUpdateUserRole(List<UserRolePO> userRolePOList) throws Exception;
	
	/**
	 * <p>更新用户角色对象</p>
	 * @param userRolePO
	 * @return
	 * @throws Exception
	 */
	public int updateUserRole(UserRolePO userRolePO) throws Exception;

	/**
	 * <p>根据用户ID获取用户角色列表</p>
	 * @param id 用户ID
	 * @return
	 */
	public List<RolePO> getRoleListByUserId(String id) throws Exception;

	/**
	 * <p>根据用户ID获取用户角色</p>
	 * 
	 * @param id 用户ID
	 * @return
	 */
	public RolePO getRoleByUserId(String id) throws Exception;

	public int getPageListDataCount(PageListData pageData, String sf, String ss)
			throws Exception;

	public List<RolePO> getPageListData(PageListData pageData, String sf,
			String ss) throws Exception;

	/**
	 * <p>批量删除用户角色</p>
	 * @param userRolePOList
	 * @throws Exception
	 */
	void batchDeleteUserRole(List<UserRolePO> userRolePOList) throws Exception;

	/**
	 * <p>删除用户角色</p>
	 * @param userRolePO
	 * @throws Exception
	 */
	int deleteUserRole(UserRolePO userRolePO) throws Exception;
	
}

