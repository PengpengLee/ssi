package cn.itcast.ssi.common.interfaces;

import java.util.List;

import cn.itcast.ssi.sysmgmt.vo.RoleVO;

/**
 * <p>系统用户接口</p>
 * 存储用户基本信息，并将该对象存放到session
 * 
 * @author lipp
 *
 */
public interface ISysUser {

	public String getId();
	
	public String getName();
	
	public String getUsername();
	
	public String getCreateTime();
	
	public String getMobile();
	
	public String getEmail();
	
	public String getUserType();
	
	public String getDel();
	
	public RoleVO getRoleVO();
	
}
