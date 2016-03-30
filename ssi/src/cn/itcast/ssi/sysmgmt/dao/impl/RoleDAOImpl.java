package cn.itcast.ssi.sysmgmt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.ssi.common.dao.impl.IBatisEntityDaoImpl;
import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.sysmgmt.dao.IRoleDAO;
import cn.itcast.ssi.sysmgmt.entity.RolePO;
import cn.itcast.ssi.sysmgmt.entity.UserRolePO;

@Repository("roleDao")
@SuppressWarnings("unchecked")
public class RoleDAOImpl extends IBatisEntityDaoImpl<RolePO, String> 
		implements IRoleDAO {

	@Override
	public String saveRole(RolePO RolePO) throws Exception {
		Object o = saveEntity("role.insertRole", RolePO);
		if(o != null)
			return o + "";
		return null;
	}
	
	@Override
	public String saveUserRole(UserRolePO userRolePO) throws Exception {
		Object o = saveEntity("role.insertUserRole", userRolePO);
		if(o != null)
			return o + "";
		return null;
	}
	
	@Override
	public void batchDeleteUserRole(List<UserRolePO> userRolePOList) throws Exception {
//		this.batchDelete("role.deleteUserRole", userRolePOList);
		this.batchDelete("user.updateUserType", userRolePOList);
	}
	
	@Override
	public int deleteUserRole(UserRolePO userRolePO) throws Exception {
//		return this.deleteEntity("role.deleteUserRole", userRolePO);
		return this.deleteEntity("user.updateUserType", userRolePO);
	}
	
	@Override
	public void batchUpdateUserRole(List<UserRolePO> userRolePOList) throws Exception {
//		this.batchUpdate("role.updateUserRole", userRolePOList);
		this.batchUpdate("user.updateUserType", userRolePOList);
	}
	
	@Override
	public int updateUserRole(UserRolePO userRolePO) throws Exception {
//		return this.updateEntity("role.updateUserRole", userRolePO);
		return this.updateEntity("user.updateUserType", userRolePO);
	}
	

	@Override
	public RolePO getRoleByUserId(String userId) throws Exception {
//		return this.getEntity("role.getRoleByUserId", userId);
		return this.getEntity("role.getRoleByUserType", userId);
	}
	
	@Override
	public List<RolePO> getRoleListByUserId(String userId) throws Exception {
		return this.getList("role.getRolesByUserId", userId);
	}

	@Override
	public int getPageListDataCount(PageListData pageData, String sf, String ss) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("name", ss);
		return this.getListCount("role.getPageListDataCount", map);
	}

	@Override
	public List<RolePO> getPageListData(PageListData pageData, String sf,
			String ss) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("name", ss);
		return this.getList("role.getPageListData", map);
	}
	
}
