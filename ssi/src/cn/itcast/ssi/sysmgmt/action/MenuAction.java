package cn.itcast.ssi.sysmgmt.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.ssi.common.action.BaseAction;
import cn.itcast.ssi.common.constant.Constants;
import cn.itcast.ssi.common.entity.Msg;
import cn.itcast.ssi.sysmgmt.service.IMenuService;
import cn.itcast.ssi.sysmgmt.vo.MenuVO;

/**
 * <p>菜单Action</p>
 * 每一个菜单和一个链接对应，每个角色对应多个菜单，
 * 通过用户拥有的角色获取对应的菜单，从而控制权限
 * 
 * @author lipp
 * @date 2015-01-19
 */
@Controller
@Namespace("/menu")
@Scope("prototype")
public class MenuAction extends BaseAction<MenuVO> {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 用户服务对象 */
	@Resource
	private IMenuService menuService;
	
	
	/**
	 * <p>菜单管理首页</p>
	 * 
	 * @return
	 */
	@Action(value="index", results={@Result(location="/modules/sysmgmt/menu/index.jsp")})
	public String indexAction() {
		return SUCCESS;
	}
	
	/**
	 * <p>保存菜单</p>
	 * 
	 * @return
	 */
	@Action(value="saveMenu", results={@Result(type="json", params={"root", "msg"})})
	public String saveMenuAction(){
		msg = new Msg();
		try {
			// TODO 二次校验
			// TODO 权限验证：是否有添加权限
			
			model.setCreateTime(format.format(new Date()));
			model.setDel(Constants.DEL_STATUS_NO);
			model.setType("0"); // 默认为父菜单，暂时没有子菜单
			menuService.saveMenu(this.model);
			msg.setKey(SUCCESS);
			msg.setMsg(Constants.MSG_SAVE_SUCCESS);
			// TODO Log...
		} catch (Exception e) {
			e.printStackTrace();
			msg.setKey(ERROR);
			msg.setMsg(Constants.MSG_OPERATE_FAIL);
		}
		return SUCCESS;
	}
	
	/**
	 * <p>删除菜单</p>
	 * 
	 * @return
	 */
	@Action(value="delMenu", results={@Result(type="json", params={"root", "msg"})})
	public String delMenuAction(){
		msg = new Msg();
		try {
			// TODO 权限验证
			model.setDeleteTime(format.format(new Date()));
			model.setDel(Constants.DEL_STATUS_YES);
			menuService.delMenu(this.model.getId());
			msg.setKey(SUCCESS);
			msg.setMsg(Constants.MSG_DEL_SUCCESS);
			// TODO Log...
		} catch (Exception e) {
			e.printStackTrace();
			msg.setKey(ERROR);
			msg.setMsg(Constants.MSG_OPERATE_FAIL);
		}
		return SUCCESS;
	}
	
	/**
	 * <p>修改菜单</p>
	 * 
	 * @return
	 */
	@Action(value="updateMenu", results={@Result(type="json", params={"root", "msg"})})
	public String updateMenuAction(){
		msg = new Msg();
		try {
			// TODO 权限验证
			menuService.updateMenu(this.model);
			msg.setKey(SUCCESS);
			msg.setMsg(Constants.MSG_SAVE_SUCCESS);
			// TODO Log...
		} catch (Exception e) {
			e.printStackTrace();
			msg.setKey(ERROR);
			msg.setMsg(Constants.MSG_OPERATE_FAIL);
		}
		return SUCCESS;
	}
	
	/**
	 * <p>获取菜单对象</p>
	 * 
	 * @return
	 */
	@Action(value="getMenuById", results={@Result(type="json", params={"root", "msg"})})
	public String getMenuByIdAction() {
		msg = new Msg();
		try {
			MenuVO menu = menuService.getMenu(model.getId());
			if(menu != null) {
				msg.setKey(SUCCESS);
				msg.setObj(menu);
			} else {
				msg.setKey(ERROR);
				msg.setMsg("参数异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setKey(ERROR);
			msg.setMsg(Constants.MSG_OPERATE_FAIL);
		}
		return SUCCESS;
	}
	
	/**
	 * <p>查询菜单列表</p>
	 * 
	 * @return 返回JSON格式的数据
	 */
	@Action(value="getMenuList", results={@Result(type="json", params={"root", "pageResultSet"})})
	public String getMenuListAction(){
		// TODO 权限验证
		try {
			pageResultSet = menuService.getPageList(page, rows, sidx, sord, sf, ss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
