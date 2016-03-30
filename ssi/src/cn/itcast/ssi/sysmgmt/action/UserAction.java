package cn.itcast.ssi.sysmgmt.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.ssi.common.action.BaseAction;
import cn.itcast.ssi.common.entity.Msg;
import cn.itcast.ssi.sysmgmt.service.IUserService;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

@Controller
@Namespace("/user")
@Scope("prototype")
public class UserAction extends BaseAction<UserVO> {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8781190808363765521L;
	
	/** 是否为角色用户的标识 */
	private boolean flag = true;

	/** 用户服务对象 */
	@Resource
	private IUserService userService;
	

	
	/**
	 * <p>用户登录系统后进入首页</p>
	 * 根据用户权限加载侧边栏
	 * 
	 * @return 
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Action(value="index", results={@Result(location="/modules/sysmgmt/user/index.jsp")})
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * <p>删除用户</p>
	 * 
	 * @return 返回json格式的操作消息字符串
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Action(value="delUser", results={@Result(type="json", params={"root", "msg"})})
	public String delUser(){
		msg = new Msg();
		try {
			boolean flag = userService.delUserById(model.getId());
			if(flag) {
				msg.setKey(SUCCESS);
				msg.setMsg("删除成功！");
			} else {
				msg.setKey(ERROR);
				msg.setMsg("删除失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setKey(ERROR);
			msg.setMsg("删除失败！服务器异常");
		}
		return SUCCESS;
	}
	
	/**
	 * <p>修改用户基本信息</p>
	 * 
	 * @return 返回json格式的操作消息字符串
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Action(value="updateUser", results={@Result(type="json", params={"root", "msg"})})
	public String updateUser(){
		return SUCCESS;
	}
	
	/**
	 * <p>获取用户基本信息</p>
	 * 
	 * @return 返回json格式的用户对象字符串
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Action(value="getUser", results={@Result(type="json", params={"root", "model"})})
	public String getUser(){
		return SUCCESS;
	}
	
	/**
	 * <p>获取用户列表</p>
	 * 
	 * @return 返回json格式的用户对象列表字符串
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Action(value="getUserLsit", results={@Result(type="json", params={"root", "pageResultSet"})})
	public String getUserLsit(){
		try {
			pageResultSet = userService.getPageList(page, rows, sidx, sord, flag, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
