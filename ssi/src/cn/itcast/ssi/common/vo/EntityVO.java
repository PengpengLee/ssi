package cn.itcast.ssi.common.vo;

/**
 * <p>实体类基类，所有VO类都要继承这个类</p>
 * 这个类包含所有数据结构都需要的公共属性，并预留了5个字段<br>
 * 
 * @author lipp
 *
 */
public class EntityVO {

	/** ID，主键 */
	protected String id;
	/** 创建时间。格式：yyyy-MM-dd HH:mm:ss */
	protected String createTime;
	/** 修改时间。格式：yyyy-MM-dd HH:mm:ss */
	protected String updateTime;
	/** 删除时间。格式：yyyy-MM-dd HH:mm:ss */
	protected String deleteTime;
	/** 删除状态：0-删除；1-正常（默认值） */
	protected String del = "1";

	/** 预留字段1 */
	protected String reserveField1;
	/** 预留字段2 */
	protected String reserveField2;
	/** 预留字段3 */
	protected String reserveField3;
	/** 预留字段4 */
	protected String reserveField4;
	/** 预留字段5 */
	protected String reserveField5;

	/***
	 * <p>
	 * 判断当前是否为新建对象，可用于判断保存或更新操作
	 * </p>
	 * 根据ID是否为空来判断
	 * 
	 * @return 返回判断结果：true则是游离对象，false为持久态对象
	 */
	public boolean isNew() {
		return (this.id == null) || (this.id.trim().length() == 0);
	}
	

	/***** getter and setter *****/
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getReserveField1() {
		return reserveField1;
	}

	public void setReserveField1(String reserveField1) {
		this.reserveField1 = reserveField1;
	}

	public String getReserveField2() {
		return reserveField2;
	}

	public void setReserveField2(String reserveField2) {
		this.reserveField2 = reserveField2;
	}

	public String getReserveField3() {
		return reserveField3;
	}

	public void setReserveField3(String reserveField3) {
		this.reserveField3 = reserveField3;
	}

	public String getReserveField4() {
		return reserveField4;
	}

	public void setReserveField4(String reserveField4) {
		this.reserveField4 = reserveField4;
	}

	public String getReserveField5() {
		return reserveField5;
	}

	public void setReserveField5(String reserveField5) {
		this.reserveField5 = reserveField5;
	}

}
