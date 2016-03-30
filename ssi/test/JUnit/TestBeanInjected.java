package JUnit;

import org.junit.Test;

import cn.itcast.ssi.common.interfaces.ISysUser;
import cn.itcast.ssi.common.util.json.JsonUtil;
import cn.itcast.ssi.sysmgmt.action.LoginAction;
import cn.itcast.ssi.sysmgmt.action.UserAction;
import cn.itcast.ssi.sysmgmt.service.IUserService;
import cn.itcast.ssi.sysmgmt.service.impl.UserServiceImpl;

public class TestBeanInjected extends BaseTest {

	/**
	 * <p>测试Bean的注入：成功</p>
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Test
	public void testBeanInjected(){
//		BaseAction baseaction = (BaseAction)context.getBean(BaseAction.class);
//		System.out.println(baseaction);
		LoginAction loginAction = 
//				(LoginAction)context.getBean(LoginAction.class);
		 		(LoginAction)context.getBean("loginAction");
		UserAction userAction = 
//				(UserAction)context.getBean(UserAction.class);
				(UserAction)context.getBean("userAction");
		IUserService iuserService = 
//				(IUserService)context.getBean(IUserService.class);
				(IUserService)context.getBean("userService");
		UserServiceImpl userService = 
//				(UserServiceImpl)context.getBean(UserServiceImpl.class);
				(UserServiceImpl)context.getBean("userService");
		IUserService iuserServiceImpl = 
//				(IUserService)context.getBean(UserServiceImpl.class);
				(IUserService)context.getBean("userService");
		System.out.println(loginAction.getLoginUser());
//		System.out.println(userAction);
//		System.out.println(iuserService);
//		System.out.println(userService);
//		System.out.println(iuserServiceImpl);
//		System.out.println(imemCache);
		
	}
}
