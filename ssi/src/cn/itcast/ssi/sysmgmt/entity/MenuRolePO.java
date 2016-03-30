package cn.itcast.ssi.sysmgmt.entity;

import java.io.Serializable;

import cn.itcast.ssi.common.entity.Entity;

/**
 * <p>菜单角色关联实体类</p>
 * 
 * @author lipp
 * @date 2015-03-12
 */
public class MenuRolePO extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 角色ID */
	private String roleId;
	
	/** 菜单ID */
	private String menuId;
	
	/** 备注 */
	private String remark;

	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
