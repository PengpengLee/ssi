package JUnit;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import cn.itcast.ssi.common.util.MD5;
import cn.itcast.ssi.sysmgmt.service.IUserService;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

public class TestService extends BaseTest {
	
	SimpleDateFormat format = new SimpleDateFormat();

	/**
	 * <p>
	 * 测试UserService：成功
	 * </p>
	 * 
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Test
	public void testUserService() {
		try {
			IUserService userService = (IUserService) context
					.getBean("userService");
			UserVO uservo = new UserVO();
			uservo.setId(userService.newEntityKey());
			uservo.setUsername("17701315652");
			uservo.setPassword(MD5.encode(MD5.encode("1") + "itcast"));
			uservo.setCreateTime(format.format(new Date()));
			userService.saveUser(uservo);
			System.out.println(userService);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
