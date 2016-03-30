package cn.itcast.ssi.sysmgmt.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import cn.itcast.ssi.common.constant.Constants;
import cn.itcast.ssi.sysmgmt.action.LoginAction;
import cn.itcast.ssi.sysmgmt.action.MainFrameAction;
import cn.itcast.ssi.sysmgmt.vo.MenuVO;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CommonBusinessInterceptor implements Interceptor {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void destroy() {
	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if(action instanceof LoginAction){
			return invocation.invoke();
		}
		if(action instanceof MainFrameAction){
			return invocation.invoke();
		}
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext
				.get(StrutsStatics.HTTP_RESPONSE);
		
		
		/* 校验的路径必须包含两层：namespace + 具体Action */
		String namespace = invocation.getProxy().getNamespace();
		Map<String, Object> session = actionContext.getSession();
		Object o = session.get(Constants.LOGIN_USER_KEY);
		if(o != null) {
			UserVO uservo = (UserVO)o;
			List<MenuVO> menuVOList = uservo.getRoleVO().getMenuVOList();
			if(menuVOList != null && menuVOList.size() > 0) {
				for (MenuVO menuVO : menuVOList) { // 确保当前用户的菜单路径中包含请求路径的命名空间
					String actionUrl = menuVO.getActionUrl();
					if(actionUrl.indexOf(namespace) != -1) {
						return invocation.invoke();
					}
				}
			}
		}
		return "index";
	}

}
