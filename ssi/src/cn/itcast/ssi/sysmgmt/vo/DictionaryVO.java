package cn.itcast.ssi.sysmgmt.vo;

import cn.itcast.ssi.common.vo.EntityVO;

/**
 * <p>数据字典类</p>
 * 通过字段PARENT_ID自关联
 * 
 * @author lipp
 * @date 2015-03-12
 */
public class DictionaryVO extends EntityVO {


	/** 字典名称 */
	private String name;

	/** 字典编码 */
	private String code;

	/** 字典父ID，自关联 */
	private String parentId;

	/** 字典序号，用于自定义排序 */
	private String serialNum;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
