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
import cn.itcast.ssi.common.interfaces.ISysUser;
import cn.itcast.ssi.common.util.StringUtil;
import cn.itcast.ssi.sysmgmt.service.IRoleService;
import cn.itcast.ssi.sysmgmt.vo.RoleVO;

/**
 * <p>角色Action</p>
 * 每一个角色和一个链接对应，每个角色对应多个角色，
 * 通过用户拥有的角色获取对应的角色，从而控制权限
 * 
 * @author lipp
 * @date 2015-01-19
 */
@Controller
@Namespace("/role")
@Scope("prototype")
public class RoleAction extends BaseAction<RoleVO> {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 用户服务对象 */
	@Resource
	private IRoleService roleService;
	
	
	/**
	 * <p>角色管理首页</p>
	 * 
	 * @return
	 */
	@Action(value="index", results={@Result(location="/modules/sysmgmt/role/index.jsp")})
	public String indexAction() {
		return SUCCESS;
	}
	
	/**
	 * <p>保存角色</p>
	 * 
	 * @return
	 */
	@Action(value="saveRole", results={@Result(type="json", params={"root", "msg"})})
	public String saveRoleAction(){
		msg = new Msg();
		try {
			// TODO 二次校验
			// TODO 权限验证：是否有添加权限
			
			model.setCreateTime(format.format(new Date()));
			model.setDel(Constants.DEL_STATUS_NO);
			roleService.saveRole(this.model);
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
	 * <p>保存角色用户</p>
	 * 
	 * @return
	 */
	@Action(value="saveRoleUsers", results={@Result(type="json", params={"root", "msg"})})
	public String saveRoleUsersAction(){
		msg = new Msg();
		try {
			/* 二次校验 */ 
			String roleId = model.getCode(); // 用户角色
			String ids = model.getId(); // 用户IDs
			if(StringUtil.isEmpty(roleId) || StringUtil.isEmpty(ids)) {
				msg.setKey(ERROR);
				msg.setMsg("非法参数");
				return SUCCESS;
			}
			/* 权限验证：是否有添加权限 */
			ISysUser user = this.getLoginUser();
			int currentRole = Integer.parseInt(user.getRoleVO().getCode());
			int gettedRole = Integer.parseInt(roleId);
			if(gettedRole <= currentRole) { // 如果传递过来的角色编码小于等于当前用户的角色编码
				// 权限不足：角色编码越小，权限越大
				msg.setKey(ERROR);
				msg.setMsg("权限不足");
				return SUCCESS;
			}
			
			model.setCreateTime(format.format(new Date()));
			model.setDel(Constants.DEL_STATUS_NO);
			roleService.saveRoleUsers(this.model);
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
	 * <p>删除角色</p>
	 * 
	 * @return
	 */
	@Action(value="delRole", results={@Result(type="json", params={"root", "msg"})})
	public String delRoleAction(){
		msg = new Msg();
		try {
			// TODO 权限验证
			model.setDeleteTime(format.format(new Date()));
			model.setDel(Constants.DEL_STATUS_YES);
			roleService.delRole(this.model.getId());
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
	 * <p>删除角色用户，支持批量删除</p>
	 * 
	 * @return
	 */
	@Action(value="delRoleUsers", results={@Result(type="json", params={"root", "msg"})})
	public String delRoleUsersAction(){
		msg = new Msg();
		try {
			/* 二次校验 */ 
			String roleId = model.getCode(); // 用户角色
			String ids = model.getId(); // 用户IDs
			if(StringUtil.isEmpty(roleId) || StringUtil.isEmpty(ids)) {
				msg.setKey(ERROR);
				msg.setMsg("非法参数");
				return SUCCESS;
			}
			/* 权限验证：是否有添加权限 */
			ISysUser user = this.getLoginUser();
			int currentRole = Integer.parseInt(user.getRoleVO().getCode());
			int gettedRole = Integer.parseInt(roleId);
			if(gettedRole <= currentRole) { // 如果传递过来的角色编码小于等于当前用户的角色编码
				// 权限不足：角色编码越小，权限越大
				msg.setKey(ERROR);
				msg.setMsg("权限不足");
				return SUCCESS;
			}
			
			// TODO 权限验证
			model.setDeleteTime(format.format(new Date()));
			model.setDel(Constants.DEL_STATUS_YES);
			roleService.delRoleUsers(this.model);
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
	 * <p>修改角色</p>
	 * 
	 * @return
	 */
	@Action(value="updateRole", results={@Result(type="json", params={"root", "msg"})})
	public String updateRoleAction(){
		msg = new Msg();
		try {
			// TODO 权限验证
			roleService.updateRole(this.model);
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
	 * <p>查询角色列表</p>
	 * 
	 * @return 返回JSON格式的数据
	 */
	@Action(value="getRoleList", results={@Result(type="json", params={"root", "pageResultSet"})})
	public String getRoleListAction(){
		// TODO 权限验证
		try {
			pageResultSet = roleService.getPageList(page, rows, sidx, sord, sf, ss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
