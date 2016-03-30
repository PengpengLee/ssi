package cn.itcast.ssi.common.entity;

/**
 * <p>异步请求时返回给页面的消息类</p>
 * 
 * @author lipp
 * 
 */
public class Msg {

	private String key;// 消息标识
	private String type;// 消息类型
	private String msg;// 消息内容
	private Object obj;// 消息附属信息,支持用户自定义消息

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
