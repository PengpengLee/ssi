package cn.itcast.ssi.common.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.SessionAware;

import cn.itcast.ssi.common.constant.Constants;
import cn.itcast.ssi.common.entity.Msg;
import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.interfaces.ISysUser;
import cn.itcast.ssi.common.interfaces.ISystemLogger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@ParentPackage("ssi2")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,
		SessionAware {

	private static final long serialVersionUID = 1L;
	
	/** 格式化日期对象 */
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** 模型对象 */
	protected T model;
	/** 模型类型 */
	private Class<T> modelClass = null;
	/** 会话 */
	private Map<String, Object> session;
	
	/** 当前页码 */
	protected Integer page = 1;
	/** 每页记录数量 */
	protected Integer rows = 10;
	/** 排序字段 */
	protected String sidx = "create_time";
	/** 排序方式 */
	protected String sord = "desc";
	/** searchField 查询字段 */
	protected String sf;
	/** searchString 查询条件 */
	protected String ss;
	

	/** 返回给页面消息对象 */
	protected Msg msg;
	/** 返回页面的数据：JSON */
	protected String json;
	/** 分页对象 */
	protected PageResultSet<T> pageResultSet;
	/** 日志 */
	@Resource
	private ISystemLogger sysLogService;
	
	
	@SuppressWarnings("unchecked")
	public BaseAction() {
		super();
		/* 加载model对象 */
		Class<?> clazz = this.getClass();
		Type superType = clazz.getGenericSuperclass();
		if(superType instanceof ParameterizedType){
			Type[] types = ((ParameterizedType)superType).getActualTypeArguments();
			if(types.length > 0){
				this.modelClass = (Class<T>)types[0];
			}
		}
		if(this.modelClass != null){
			try {
				this.model = this.modelClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * <p>获取当前登录用户对象</p>
	 * 
	 * @return
	 */
	public ISysUser getLoginUser(){
		return (ISysUser)this.getSession().get(Constants.LOGIN_USER_KEY);
	}
	
	/**
	 * 获取日志对象
	 * 
	 * @return
	 */
	public ISystemLogger getSysLogger() {
		return sysLogService;
	}

	public void setSysLogger(ISystemLogger sysLogger) {
		this.sysLogService = sysLogger;
	}

	@Override
	public T getModel() {
		return model;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	/** 重写setSession方法 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/** 项目发布根目录:contextPath */
	public String getContextPath() {
		return this.getRequest().getContextPath();
	}
	
	/** 项目发布根目录:contextPath */
	public String getCtx() {
		return getContextPath();
	}

	/** HTTP请求对象 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/** HTTP响应对象 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSf() {
		return sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public Msg getMsg() {
		return msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public PageResultSet<T> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<T> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}

}
