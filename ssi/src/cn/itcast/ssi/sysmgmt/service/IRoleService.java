package cn.itcast.ssi.sysmgmt.service;

import java.util.List;

import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.service.IBaseService;
import cn.itcast.ssi.sysmgmt.vo.RoleVO;

/**
 * <p>角色服务接口</p>
 * 提供所有关于角色操作的服务
 * 
 * @author lipp
 * @date 2015-01-18
 *
 */
public interface IRoleService extends IBaseService{

	/**
	 * <p>保存角色</p>
	 * 
	 * @param RoleVO 封装了角色字段的实体对象
	 * @author lipp
	 * @date 2015-05-28
	 */
	String saveRole(RoleVO RoleVO) throws Exception;
	
	/**
	 * <p>批量保存角色</p>
	 * 
	 * @param RoleVO 封装了角色字段的实体对象
	 * @author lipp
	 * @date 2015-05-28
	 */
	public void saveRoleUsers(RoleVO roleVO) throws Exception;

	/**
	 * <p>删除角色</p>
	 * 
	 * @param id 角色实体对象ID
	 * @author lipp
	 * @date 2015-05-28
	 */
	int delRole(String id) throws Exception;

	/**
	 * <p>修改角色</p>
	 * 
	 * @param RoleVO 封装了角色字段的实体对象
	 * @author lipp
	 * @date 2015-05-28
	 */
	int updateRole(RoleVO model) throws Exception;
	
	/**
	 * <p>获取角色对象</p>
	 * 
	 * @param id 角色实体对象ID
	 * @author lipp
	 * @date 2015-05-28
	 */
	RoleVO getRoleByUserId(String id) throws Exception;

	/**
	 * <p>获取用户角色列表</p>
	 * @param id 用户ID
	 * @return 
	 */
	List<RoleVO> getRoleListByUserId(String id) throws Exception;

	/**
	 * <p>分页：获取角色列表</p>
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param sf
	 * @param ss
	 * @return
	 * @throws Exception
	 */
	PageResultSet<RoleVO> getPageList(Integer page, Integer rows, String sidx,
			String sord, String sf, String ss) throws Exception;

	/**
	 * <p>删除用户角色，支持批量删除</p>
	 * @param model
	 * @throws Exception
	 */
	void delRoleUsers(RoleVO model) throws Exception;

}
