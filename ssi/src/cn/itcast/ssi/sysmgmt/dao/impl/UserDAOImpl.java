package cn.itcast.ssi.sysmgmt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.ssi.common.dao.impl.IBatisEntityDaoImpl;
import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.sysmgmt.dao.IUserDAO;
import cn.itcast.ssi.sysmgmt.entity.UserPO;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDAOImpl extends IBatisEntityDaoImpl<UserPO, String> implements IUserDAO {
	
	@Override
	public String saveUser(UserPO userpo) throws Exception {
		Object o = saveEntity("user.insertUser", userpo);
		if(o != null)
			return o + "";
		return null;
	}

	@Override
	public UserPO getUserForLogin(String username, String password) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		return this.getEntity("user.getUserForLogin", map);
	}
	
	@Override
	public UserPO getUserById(String userId) throws Exception {
		return this.getEntity("user.getUserById", userId);
	}
	
	@Override
	public List<UserPO> getUserByProperty(UserVO uservo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(uservo.getId() != null) {
			map.put("id", uservo.getId());
		}
		if(uservo.getUsername() != null) {
			map.put("username", uservo.getUsername());
		}
		if(uservo.getPassword() != null) {
			map.put("password", uservo.getPassword());
		}
		if(uservo.getName() != null) {
			map.put("name", uservo.getName());
		}
		if(uservo.getMobile() != null) {
			map.put("mobile", uservo.getMobile());
		}
		if(uservo.getEmail() != null) {
			map.put("email", uservo.getEmail());
		}
		return this.getList("user.getUserByProperty", map);
	}
	
	@Override
	public List<UserPO> getPageListData(PageListData pageData, boolean flag, 
			String role_id, String ss) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("username", ss);
		map.put("name", ss);
		map.put("user_type", role_id);
		map.put("flag", flag);
		return this.getList("user.getPageListData", map);
	}
	
	@Override
	public int getPageListDataCount(PageListData pageData, boolean flag, 
			String role_id, String ss) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("username", ss);
		map.put("name", ss);
		map.put("user_type", role_id);
		map.put("flag", flag);
		return this.getListCount("user.getPageListDataCount", map);
	}

	@Override
	public int delUserById(String id) {
		return this.updateEntityById("user.delUserById", id);
	}
	
	
}
