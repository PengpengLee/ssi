package JUnit;

import org.junit.Test;

import cn.itcast.ssi.sysmgmt.service.IUserService;

/**
 * 本测试类用于做权限控制
 * @author 李鹏鹏
 *
 */
public class TestRole extends BaseTest {

	/**
	 * <p>测试Bean的注入：成功</p>
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Test
	public void testBeanInjected(){
		IUserService iuserService = 
				(IUserService)context.getBean("userService");
		System.out.println(iuserService);
	}
}
