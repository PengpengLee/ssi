package cn.itcast.ssi.sysmgmt.entity;

import java.io.Serializable;

import cn.itcast.ssi.common.entity.Entity;

/**
 * <p>
 * 菜单类
 * </p>
 * 通过字段PARENT_ID自关联
 * 
 * @author lipp
 * @date 2015-03-12
 */
public class MenuPO extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 菜单名称 */
	private String name;

	/** 菜单编码 */
	private String code;

	/** 菜单图标 */
	private String icon;

	/** 字典父ID，自关联 */
	private String parentId;
	
	/** 所属角色 */
	private String roleId;

	/** 菜单请求链接 */
	private String actionUrl;
	
	/** 菜单类型：0-父菜单；1-子菜单 */
	private String type;

	/** 字典序号，用于自定义排序 */
	private String serialNum;
	
	/** 菜单配色 */
	private String color;

	/** 备注 */
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
