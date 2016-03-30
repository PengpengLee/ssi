package cn.itcast.ssi.common.entity;

/***
 * <p>访问日志实体类</p>
 * 
 * @author lipp
 *
 */
public class VisitLog extends Entity {

	private static final long serialVersionUID = 1L;

	/** 访问者来源终端，存储设备类型 */
	private String originTerminal;
	/** 访问时间 */
	private String visitTime;
	/** 访问模块或应用 */
	private String visitTopic;
	/** 访问请求（具体的访问请求） */
	private String visitActionName;
	/** 访问请求时长，毫秒 */
	private long duration;
	
	
	/***** GETTER AND SETTER *****/
	public String getOriginTerminal() {
		return originTerminal;
	}

	public void setOriginTerminal(String originTerminal) {
		this.originTerminal = originTerminal;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitTopic() {
		return visitTopic;
	}

	public void setVisitTopic(String visitTopic) {
		this.visitTopic = visitTopic;
	}

	public String getVisitActionName() {
		return visitActionName;
	}

	public void setVisitActionName(String visitActionName) {
		this.visitActionName = visitActionName;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

}
