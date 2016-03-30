package cn.itcast.ssi.sysmgmt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.service.impl.BaseServiceImpl;
import cn.itcast.ssi.common.util.StringUtil;
import cn.itcast.ssi.sysmgmt.dao.IMenuDAO;
import cn.itcast.ssi.sysmgmt.entity.MenuPO;
import cn.itcast.ssi.sysmgmt.service.IMenuService;
import cn.itcast.ssi.sysmgmt.vo.MenuVO;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl implements IMenuService {
	
	@Resource
	private IMenuDAO menuDao;

	@Override
	public String saveMenu(MenuVO menuVO) throws Exception {
		if(menuVO != null) {
			if(StringUtil.hasValue(menuVO.getId())) { // ID存在，作修改动作
				String id = menuVO.getId();
				MenuPO po = menuDao.getMenuById(id);
				this.copyPropertis(menuVO, po);
				po.setUpdateTime(format.format(new Date()));
				menuDao.updateMenu(po);
				return id;
			} else { // ID不存在，作添加动作
				MenuPO po = new MenuPO();
				String id = newEntityKey();
				po.setId(id);
				po.setReserveField1("grey-steel"); // 参见 /modules/testquestion/test/colors.txt
				menuDao.saveMenu(po);
				return id;
			}
		}
		return null;
	}

	@Override
	public boolean delMenu(String id) throws Exception {
		int num = menuDao.delMenu(id);
		if(num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int updateMenu(MenuVO model) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MenuVO getMenu(String id) throws Exception {
		MenuPO po = menuDao.getMenuById(id);
		if(po != null) {
			MenuVO vo = new MenuVO();
			this.copyPropertis(po, vo);
			return vo;
		}
		return null;
	}

	@Override
	public PageResultSet<MenuVO> getPageList(Integer page, Integer rows,
			String sidx, String sord, String sf, String ss) throws Exception {
		PageResultSet<MenuVO> result = new PageResultSet<MenuVO>();
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
		
		int count = menuDao.getPageListDataCount(pageData, sf, ss);
		List<MenuVO> menuVOList = new ArrayList<MenuVO>();
		if(count > 0){
			List<MenuPO> menuPOList = menuDao.getPageListData(pageData, sf, ss);
			if(menuPOList != null && menuPOList.size() > 0) {
				for (int i = 0; i < menuPOList.size(); i++) {
					MenuPO po = menuPOList.get(i);
					MenuVO vo = new MenuVO();
					this.copyPropertis(po, vo);
					menuVOList.add(vo);					
				}
			}
		}
		result.setTotal(count);
		result.setData(menuVOList);
		return result;
	}
	
}
