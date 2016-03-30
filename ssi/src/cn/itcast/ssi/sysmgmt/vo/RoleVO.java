package cn.itcast.ssi.sysmgmt.vo;

import java.util.List;

import cn.itcast.ssi.common.vo.EntityVO;

/**
 * <p>角色类</p>
 * 通过字段PARENT_ID自关联
 * 
 * @author lipp
 * @date 2015-03-12
 */
public class RoleVO extends EntityVO {


	/** 角色名称 */
	private String name;
	
	/** 角色编码 */
	private String code;
	
	/** 角色父ID */
	private String parentId;
	
	/** 群组 */
	private String group;
	
	/** 备注 */
	private String remark;
	
	/** 角色可操作菜单列表 */
	private List<MenuVO> menuVOList;

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
	
	public List<MenuVO> getMenuVOList() {
		return menuVOList;
	}

	public void setMenuVOList(List<MenuVO> menuVOList) {
		this.menuVOList = menuVOList;
	}
	
}
