package cn.itcast.ssi.sysmgmt.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import cn.itcast.ssi.common.cache.CookieUtil;
import cn.itcast.ssi.common.constant.Constants;
import cn.itcast.ssi.sysmgmt.action.LoginAction;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1101107909818583196L;
	
	@Override
	public void destroy() {
	}

	@Override
	public void init() {

	}

	/**
	 * <p>验证每个请求中cookie信息的UUID和缓存服务器中的UUID是否匹配，
	 * 不匹配则跳转到登录页面。</p>
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object action = invocation.getAction();
		if(action instanceof LoginAction){
			return invocation.invoke(); // 不拦截登录相关Action
		}
		
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
		Map<String, Object> session = actionContext.getSession();
		
		CookieUtil cookieUtil = new CookieUtil(request, response, Constants.COOKIE_AGE_DAY);
		// 获取用户ID的cookie对象
		Cookie cookie = cookieUtil.getCookie(Constants.COOKIE_LOGIN_UUID);// 用户唯一性登录标识UUID，不可能为空
		if(cookie == null) {
			return "login";
		}
		String uuidCookie = cookie.getValue();
		String uuidCache = session.get(Constants.COOKIE_LOGIN_UUID) + "";
		if(uuidCookie.equals(uuidCache)) {// cookie中的uuid和cache中的uuid必须匹配
			Object o = session.get(Constants.LOGIN_USER_KEY);
			if(o != null) {
				String id = ((UserVO)o).getId();
				if(id != null && !"".equals(id)) {
					return invocation.invoke();
				}
			}
		}
		return "login";
	}

}
