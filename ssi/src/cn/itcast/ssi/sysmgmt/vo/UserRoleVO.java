package cn.itcast.ssi.sysmgmt.vo;

import cn.itcast.ssi.common.vo.EntityVO;

/**
 * <p>用户角色关联实体类</p>
 * 
 * @author lipp
 * @date 2015-03-12
 */
public class UserRoleVO extends EntityVO {


	/** 角色ID */
	private String roleId;
	
	/** 用户ID */
	private String userId;
	
	/** 备注 */
	private String remark;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
