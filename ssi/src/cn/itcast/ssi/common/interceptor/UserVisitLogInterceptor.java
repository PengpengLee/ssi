package cn.itcast.ssi.common.interceptor;

import org.apache.log4j.Logger;

import cn.itcast.ssi.common.action.BaseAction;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <p>
 * 记录用户的访问日志拦截器
 * </p>
 * 
 */
public class UserVisitLogInterceptor extends AbstractInterceptor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger("UserVisitLog");

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation arg0) throws Exception {
		String nameSpace = arg0.getProxy().getNamespace();// 得到nameSpace
		String actionName = arg0.getProxy().getActionName();// 得到requestName

		long startTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		sb.append("访问路径:" + nameSpace + ";").append("访问Action:" + actionName + ";");
		BaseAction action = (BaseAction) arg0.getAction();
		if(action != null && action.getLoginUser() != null){
			sb.append("访问者ID：" + action.getLoginUser().getId() + ";");
			sb.append("访问者：" + action.getLoginUser().getUsername() + ";");
		}
		arg0.invoke();
		long endTime = System.currentTimeMillis();
		sb.append("历时=" + (endTime - startTime) / 1000.00 + "s" + ";");
		log.info(sb.toString());// 请求完成以后
		return Action.SUCCESS;

	}

}
