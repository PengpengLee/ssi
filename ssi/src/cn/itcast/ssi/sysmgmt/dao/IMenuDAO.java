package cn.itcast.ssi.sysmgmt.dao;

import java.util.List;

import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.sysmgmt.entity.MenuPO;


public interface IMenuDAO {
	
	/**
	 * <p>保存菜单对象</p>
	 * 
	 * @param menuPO 封装菜单数据的对象
	 * @return 返回保存后数据的主键
	 * @throws Exception
	 */
	public String saveMenu(MenuPO menuPO) throws Exception;

	/**
	 * <p>删除菜单对象</p>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delMenu(String id) throws Exception;
	
	/**
	 * <p>修改菜单对象</p>
	 * @param menuPO
	 * @return
	 * @throws Exception
	 */
	public int updateMenu(MenuPO menuPO) throws Exception;
	
	/**
	 * <p>获取菜单对象</p>
	 * @param id 菜单ID
	 * @return
	 * @throws Exception
	 */
	public MenuPO getMenuById(String id) throws Exception;
	
	/**
	 * <p>根据角色ID获取菜单列表</p>
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<MenuPO> getMenuListByRoleId(String roleId) throws Exception;

	/**
	 * <p>分页：获取菜单列表数据个数</p>
	 * @param pageData
	 * @param sf
	 * @param ss
	 * @return
	 * @throws Exception
	 */
	public int getPageListDataCount(PageListData pageData, String sf, String ss)
			throws Exception;

	/**
	 * <p>分页：获取菜单列表</p>
	 * @param pageData
	 * @param sf
	 * @param ss
	 * @return
	 * @throws Exception
	 */
	public List<MenuPO> getPageListData(PageListData pageData, String sf,
			String ss) throws Exception;

}

