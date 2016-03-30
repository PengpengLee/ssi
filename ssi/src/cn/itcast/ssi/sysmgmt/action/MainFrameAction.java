package cn.itcast.ssi.sysmgmt.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.ssi.common.action.BaseAction;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

@Controller
@Namespace("/")
@Scope("prototype")
public class MainFrameAction extends BaseAction<UserVO> {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>用户登录系统后进入首页</p>
	 * 
	 * @return 
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Action(value = "index", results = { @Result(location = "/modules/index.jsp") })
	public String index(){
		return SUCCESS;
	}
	
}
