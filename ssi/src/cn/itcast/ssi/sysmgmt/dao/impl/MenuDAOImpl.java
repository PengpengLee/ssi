package cn.itcast.ssi.sysmgmt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.ssi.common.dao.impl.IBatisEntityDaoImpl;
import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.sysmgmt.dao.IMenuDAO;
import cn.itcast.ssi.sysmgmt.entity.MenuPO;

@Repository("menuDao")
@SuppressWarnings("unchecked")
public class MenuDAOImpl extends IBatisEntityDaoImpl<MenuPO, String> 
		implements IMenuDAO {

	@Override
	public String saveMenu(MenuPO menuPO) throws Exception {
		Object o = saveEntity("menu.insertMenu", menuPO);
		if(o != null)
			return o + "";
		return null;
	}

	@Override
	public int delMenu(String id) throws Exception {
		return this.deleteEntity("menu.delMenu", id);
	}
	
	@Override
	public int updateMenu(MenuPO menuPO) throws Exception {
		return this.updateEntity("menu.updateMenu", menuPO);
	}
	
	@Override
	public MenuPO getMenuById(String id) throws Exception {
		return this.getEntity("menu.getMenuById", id);
	}
	
	
	@Override
	public List<MenuPO> getMenuListByRoleId(String roleId) throws Exception {
		return this.getList("menu.getMenuByRoleId", roleId);
	}

	@Override
	public int getPageListDataCount(PageListData pageData, String sf, String ss)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("name", ss);
		return this.getListCount("menu.getPageListDataCount", map);
	}

	@Override
	public List<MenuPO> getPageListData(PageListData pageData, String sf,
			String ss) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("name", ss);
		return this.getList("menu.getPageListData", map);
	}
	
}
