package cn.itcast.ssi.sysmgmt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.service.impl.BaseServiceImpl;
import cn.itcast.ssi.common.util.MD5;
import cn.itcast.ssi.common.util.StringUtil;
import cn.itcast.ssi.sysmgmt.dao.IMenuDAO;
import cn.itcast.ssi.sysmgmt.dao.IRoleDAO;
import cn.itcast.ssi.sysmgmt.dao.IUserDAO;
import cn.itcast.ssi.sysmgmt.entity.MenuPO;
import cn.itcast.ssi.sysmgmt.entity.RolePO;
import cn.itcast.ssi.sysmgmt.entity.UserPO;
import cn.itcast.ssi.sysmgmt.entity.UserRolePO;
import cn.itcast.ssi.sysmgmt.module.Role;
import cn.itcast.ssi.sysmgmt.service.IUserService;
import cn.itcast.ssi.sysmgmt.vo.MenuVO;
import cn.itcast.ssi.sysmgmt.vo.RoleVO;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements IUserService {
	
	@Resource
	private IUserDAO userDao;
	@Resource
	private IRoleDAO roleDao;
	@Resource
	private IMenuDAO menuDao;
	
	@Override
	public String saveUser(UserVO uservo) throws Exception {
		if(uservo != null){
			// 设置用户基本信息
			UserPO userpo = new UserPO();
			this.copyPropertis(uservo, userpo);
			String id = userpo.getId();
			if(StringUtil.isEmpty(id)){
				id = this.newEntityKey();
				userpo.setId(id);
			}
			String createTime = format.format(new Date());
			userpo.setCreateTime(createTime);
			userpo.setUsername(userpo.getMobile());
			userpo.setPassword(MD5.encode(userpo.getPassword()));
			/* 保存用户角色为普通用户 */
			UserRolePO userRole = new UserRolePO();
			String userRoleId = this.newEntityKey();
			userRole.setId(userRoleId);
			userRole.setUserId(id);
			userRole.setRoleId(Role.NOMAL.code());
			userRole.setCreateTime(createTime);
			roleDao.saveUserRole(userRole);
			
			/* 保存用户对象 */
			userDao.saveUser(userpo);
			return id;
		}
		return null;
	}
	
	@Override
	public UserVO getUserForLogin(String username, String password) throws Exception{
		password = MD5.encode(password);
		UserPO userPO = userDao.getUserForLogin(username, password);
		if(userPO != null) {
			String userId = userPO.getId();
			UserVO userVO = new UserVO();
			this.copyPropertis(userPO, userVO);
			/* 获取当前用户的角色信息 */
			RolePO rolePO = roleDao.getRoleByUserId(userPO.getUserType());
			RoleVO roleVO = new RoleVO();
			this.copyPropertis(rolePO, roleVO);
			userVO.setRoleVO(roleVO);
			/* 获取用户角色对应的菜单列表 */
			List<MenuPO> menuList = menuDao.getMenuListByRoleId(roleVO.getCode());
			if(menuList != null && menuList.size() > 0) {
				List<MenuVO> menuListVO = new ArrayList<MenuVO>();
				for (MenuPO po : menuList) {
					MenuVO vo = new MenuVO();
					this.copyPropertis(po, vo);
					menuListVO.add(vo);
				}
				userVO.getRoleVO().setMenuVOList(menuListVO);
			} else { // 非法用户
				return null;
			}
			return userVO;
		}
		return null;
		
	}
	
	@Override
	public UserVO getUserById(String userId) throws Exception {
		UserPO po = userDao.getUserById(userId);
		if(po != null) {
			UserVO vo = new UserVO();
			this.copyPropertis(po, vo);
			return vo;
		}
		return null;
	}
	
	@Override
	public List<UserVO> getByProperty(UserVO uservo) throws Exception {
		List<UserPO> userPOList = userDao.getUserByProperty(uservo);
		if(userPOList != null && userPOList.size() > 0) {
			List<UserVO> userVOList = new ArrayList<UserVO>();
			for (int i = 0; i < userPOList.size(); i++) {
				UserPO po = userPOList.get(i);
				UserVO vo = new UserVO();
				this.copyPropertis(po, vo);
				userVOList.add(vo);
			}
			return userVOList;
		}
		return null;
	}
	
	@Override
	public PageResultSet<UserVO> getPageList(Integer page, Integer rows,
			String sidx, String sord, boolean flag, UserVO model) throws Exception {
		PageResultSet<UserVO> result = new PageResultSet<UserVO>();
		result.setPage(page);
		result.setRows(rows);
		
		PageListData pageData = new PageListData();
		pageData.setStart((page - 1) * rows);// beginIndex
		pageData.setEnd(rows); // length
		if(!StringUtil.isEmpty(sidx)){
			pageData.setSidx(sidx);
		}
		if(!StringUtil.isEmpty(sord)){
			pageData.setSord(sord);
		}
		
		int count = userDao.getPageListDataCount(pageData, flag, model.getUserType(), model.getName());
		List<UserVO> userVOList = new ArrayList<UserVO>();
		if(count > 0){
			List<UserPO> userPOList = userDao.getPageListData(pageData, flag, model.getUserType(), model.getName());
			if(userPOList != null && userPOList.size() > 0) {
				for (int i = 0; i < userPOList.size(); i++) {
					UserPO po = userPOList.get(i);
					UserVO vo = new UserVO();
					this.copyPropertis(po, vo);
					userVOList.add(vo);					
				}
			}
		}
		result.setTotal(count);
		result.setData(userVOList);
		return result;
	}

	@Override
	public boolean delUserById(String id) throws Exception {
		int num = userDao.delUserById(id);
		if(num > 0) 
			return true;
		return false;
	}

}
