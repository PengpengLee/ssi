package cn.itcast.ssi.sysmgmt.entity;

import java.io.Serializable;

import cn.itcast.ssi.common.entity.Entity;

/**
 * <p>角色类</p>
 * 通过字段PARENT_ID自关联
 * 
 * @author lipp
 * @date 2015-03-12
 */
public class RolePO extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 角色名称 */
	private String name;
	
	/** 角色编码 */
	private String code;
	
	/** 角色父ID */
	private String parentId;
	
	/** 群组：用于处理一个用户有多个角色的情况 */
	private String group;
	
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	
}
