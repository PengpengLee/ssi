package cn.itcast.ssi.sysmgmt.service;

import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.service.IBaseService;
import cn.itcast.ssi.sysmgmt.vo.MenuVO;

/**
 * <p>菜单服务接口</p>
 * 提供所有关于菜单操作的服务
 * 
 * @author lipp
 * @date 2015-01-18
 *
 */
public interface IMenuService extends IBaseService{

	/**
	 * <p>保存菜单</p>
	 * 
	 * @param menuVO 封装了菜单字段的实体对象
	 * @author lipp
	 * @date 2015-05-28
	 */
	String saveMenu(MenuVO menuVO) throws Exception;

	/**
	 * <p>删除菜单</p>
	 * 
	 * @param id 菜单实体对象ID
	 * @author lipp
	 * @date 2015-05-28
	 */
	boolean delMenu(String id) throws Exception;

	/**
	 * <p>修改菜单</p>
	 * 
	 * @param menuVO 封装了菜单字段的实体对象
	 * @author lipp
	 * @date 2015-05-28
	 */
	int updateMenu(MenuVO model) throws Exception;
	
	/**
	 * <p>获取菜单对象</p>
	 * 
	 * @param id 菜单实体对象ID
	 * @author lipp
	 * @date 2015-05-28
	 */
	MenuVO getMenu(String id) throws Exception;
	
	/**
	 * <p>分页获取菜单列表，支持模糊查询</p>
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param sf
	 * @param ss
	 * @return
	 */
	PageResultSet<MenuVO> getPageList(Integer page, Integer rows, String sidx,
			String sord, String sf, String ss) throws Exception;

}
