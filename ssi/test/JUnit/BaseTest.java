package JUnit;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p><b>Summary: 单元测试基类</b></p>
 * 使用Junit4进行测试，所有测试类继承本类；<br>
 * 本类主要进行加载环境的工作 
 * 
 * @author lipp
 * @date 2015-01-18
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/configs/spring/applicationContext.xml" })
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	String args[] = { "classpath*:/configs/spring/applicationContext.xml" };
	protected ApplicationContext context = new ClassPathXmlApplicationContext(
			args);

	/**
	 * <b>Summary: </b> override method setDataSource
	 * 
	 * @param dataSource
	 * @see org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests#setDataSource(javax.sql.DataSource)
	 */
	@Override
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
}