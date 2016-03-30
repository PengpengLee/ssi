package cn.itcast.ssi.common.entity;

/**
 * <p>日志对象</p>
 * 
 * @author lipp
 * @date 2015-01-21
 *
 */
public class Log extends Entity{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;


	public static final String INFO = "INFO";
	public static final String WARN = "WARN";
	public static final String ERROR = "ERROR";

	/** 模块名 */
	private String model;
	/** 功能名 */
	private String function;
	/** 操作名 */
	private String operate;
	/** 操作者 */
	private String operater;
	/** 远程地址 */
	private String remoteAddr;
	/** 日志级别 */
	private String logLevel;
	/** 实体主键 */
	private String entityKey;
	/** 实体类型 */
	private String entityName;
	
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getEntityKey() {
		return entityKey;
	}

	public void setEntityKey(String entityKey) {
		this.entityKey = entityKey;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * 日志对象
	 * 
	 * @param model
	 *            模块名
	 * @param function
	 *            功能名
	 * @param operate
	 *            操作名
	 * @param operater
	 *            操作者
	 * @param addDate
	 *            日志添加日期
	 * @param remoteAddr
	 *            远程地址
	 * @param entityKey
	 *            实体键
	 * @param entityName
	 *            实体名
	 */
	public Log(String model, String function, String operate, String operater,
			String addDate, String remoteAddr, String entityKey, String entityName) {
		super();
		this.model = model;
		this.function = function;
		this.operate = operate;
		this.operater = operater;
		this.createTime = addDate;
		this.remoteAddr = remoteAddr;
		this.entityKey = entityKey;
		this.entityName = entityName;
	}

	public Log() {
		super();
	}

	@Override
	public String toString() {
		return "Log [日期=" + createTime + ", 模块名=" + model + ", 功能="
				+ function + ", 操作=" + operate + ", 操作者=" + operater + ", 级别="
				+ logLevel + ", 主键=" + entityKey + ", 实体名=" + entityName
				+ ", 远程地址=" + remoteAddr + "]";
	}

}
