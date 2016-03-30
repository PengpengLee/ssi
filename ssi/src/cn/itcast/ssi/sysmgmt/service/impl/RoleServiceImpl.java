package cn.itcast.ssi.sysmgmt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.service.impl.BaseServiceImpl;
import cn.itcast.ssi.common.util.StringUtil;
import cn.itcast.ssi.sysmgmt.dao.IRoleDAO;
import cn.itcast.ssi.sysmgmt.entity.RolePO;
import cn.itcast.ssi.sysmgmt.entity.UserRolePO;
import cn.itcast.ssi.sysmgmt.service.IRoleService;
import cn.itcast.ssi.sysmgmt.vo.RoleVO;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements IRoleService {
	
	@Resource
	private IRoleDAO roleDao;

	@Override
	public String saveRole(RoleVO roleVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void saveRoleUsers(RoleVO roleVO) throws Exception {
		String userIds = roleVO.getId();
		String role_id = roleVO.getCode();
		if(userIds.length() > 32) {
			List<UserRolePO> userRoleList = new ArrayList<UserRolePO>();
			String[] userIdArr = userIds.split(",");
			for (String userId : userIdArr) {
				UserRolePO userRole = new UserRolePO();
				userRole.setId(newEntityKey());
				userRole.setUserId(userId);
				userRole.setRoleId(role_id);
				userRole.setCreateTime(roleVO.getCreateTime());
				userRole.setDel("1");
				userRoleList.add(userRole);
			}
			roleDao.batchUpdateUserRole(userRoleList);
		} else {
			UserRolePO userRole = new UserRolePO();
			userRole.setId(newEntityKey());
			userRole.setUserId(userIds);
			userRole.setRoleId(role_id);
			userRole.setCreateTime(roleVO.getCreateTime());
			userRole.setDel("1");
			roleDao.updateUserRole(userRole);
		}
	}

	@Override
	public int delRole(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRole(RoleVO model) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RoleVO getRoleByUserId(String id) throws Exception {
		RolePO po = roleDao.getRoleByUserId(id);
		if(po != null ) {
			RoleVO vo = new RoleVO();
			this.copyPropertis(po, vo);
			return vo;
		}
		return null;
	}

	@Override
	public List<RoleVO> getRoleListByUserId(String id) throws Exception {
		List<RolePO> listRolePO = roleDao.getRoleListByUserId(id);
		List<RoleVO> listRoleVO = new ArrayList<RoleVO>();
		if(listRolePO != null && listRolePO.size() > 0) {
			for (int i = 0; i < listRolePO.size(); i++) {
				RolePO po = listRolePO.get(i);
				RoleVO vo = new RoleVO();
				this.copyPropertis(po, vo);
				listRoleVO.add(vo);
			}
		}
		return listRoleVO;
	}

	@Override
	public PageResultSet<RoleVO> getPageList(Integer page, Integer rows,
			String sidx, String sord, String sf, String ss) throws Exception {
		PageResultSet<RoleVO> result = new PageResultSet<RoleVO>();
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
		
		int count = roleDao.getPageListDataCount(pageData, sf, ss);
		List<RoleVO> voList = new ArrayList<RoleVO>();
		if(count > 0){
			List<RolePO> poList = roleDao.getPageListData(pageData, sf, ss);
			if(poList != null && poList.size() > 0) {
				for (int i = 0; i < poList.size(); i++) {
					RolePO po = poList.get(i);
					RoleVO vo = new RoleVO();
					this.copyPropertis(po, vo);
					voList.add(vo);					
				}
			}
		}
		result.setTotal(count);
		result.setData(voList);
		return result;
	}

	@Override
	public void delRoleUsers(RoleVO model) throws Exception {
		String userIds = model.getId();
		String role_id = model.getCode();
		if(userIds.length() > 32) {
			List<UserRolePO> userRoleList = new ArrayList<UserRolePO>();
			String[] userIdArr = userIds.split(",");
			for (String userId : userIdArr) {
				UserRolePO userRole = new UserRolePO();
				userRole.setUserId(userId);
//				userRole.setRoleId(role_id);
				userRole.setRoleId("20");
				userRoleList.add(userRole);
				roleDao.batchDeleteUserRole(userRoleList);
			}
		} else {
			UserRolePO userRole = new UserRolePO();
			userRole.setUserId(userIds);
			userRole.setRoleId("20");
//			userRole.setRoleId(role_id);
			roleDao.deleteUserRole(userRole);
		}
		
	}
	
}
