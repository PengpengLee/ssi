package cn.itcast.ssi.sysmgmt.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.ssi.common.action.BaseAction;
import cn.itcast.ssi.common.cache.CookieUtil;
import cn.itcast.ssi.common.constant.Constants;
import cn.itcast.ssi.common.constant.SystemProperties;
import cn.itcast.ssi.common.entity.Log;
import cn.itcast.ssi.common.entity.Msg;
import cn.itcast.ssi.common.exception.BusinessException;
import cn.itcast.ssi.common.util.MD5;
import cn.itcast.ssi.common.util.StringUtil;
import cn.itcast.ssi.sysmgmt.service.IUserService;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

/**
 * <p>用户登录Action</p>
 * 这个类只能处理登录/登出相关的请求，禁止在本类中处理其它业务
 * 
 * @author lipp
 * @date 2015-01-19
 */
@Controller
@Namespace("/")
@Scope("prototype")
public class LoginAction extends BaseAction<UserVO> {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 用户服务对象 */
	@Resource
	private IUserService userService;
//	@Resource
//	private IRoleService roleService;

	/**
	 * <p>登录跳转action</p>
	 * 判断是否已经登录，若已登录则跳转到首页，否则跳转到登录页
	 * 
	 * @return
	 * @author lipp
	 * @date 2014-11-18
	 */
	@Action(value = "login", results = {
			@Result(name = "login", location = "/modules/login.jsp"),
			@Result(name = "index", type = "redirectAction", params = 
					{"namespace", "/", "actionName", "index"})
	})
	public String login() {
		/** 清缓存 */
		clearCache();
		return "login";

	}

	/**
	 * <p>用户登录</p>
	 * 登录成功后将相关数据写入memCache和cookie<br>
	 * 
	 * @return
	 * @author lipp
	 * @date 2014-11-14
	 */
	@Action(value="doLogin", results={@Result(type="json", params={"root", "msg"})})
	public String doLogin() {
		msg = new Msg();
		try {
			/** 二次验证 */
			if (StringUtil.isEmpty(model.getUsername())) {
				msg.setKey(ERROR);
				msg.setMsg("请输入用户名");
				return SUCCESS;
			} else if (StringUtil.isEmpty(model.getPassword())) {
				msg.setKey(ERROR);
				msg.setMsg("请输入密码");
				return SUCCESS;
			} // 也许需要验证码
			String password = model.getPassword();
			password += SystemProperties.MD5_SUFFIX; // 密码连接后缀
			UserVO uservo = userService.getUserForLogin(model.getUsername(), password); // 访问数据库
			if(uservo == null || StringUtil.isEmpty(uservo.getId())){ // 用户密码错误不允许登录
				msg.setKey(ERROR);
				msg.setMsg("用户名或密码错误");
				return SUCCESS;
			}
			
			/*** 登录成功 ***/
			
			/* 存放到cookie中的信息 */
			String uuid = UUID.randomUUID().toString(); // 唯一登录标识
			CookieUtil cookie = new CookieUtil(this.getRequest(), 
					this.getResponse(), Constants.COOKIE_AGE_DAY); // cookie对象，缓存超时：1天
			cookie.addCookie(Constants.COOKIE_USER_ID, uservo.getId()); // 用户ID
			cookie.addCookie(Constants.COOKIE_USER_NAME, uservo.getUsername()); // 用户名
			cookie.addCookie(Constants.COOKIE_LOGIN_UUID, uuid); // 用户唯一性登录标识uuid
			
			/* 存放到session中的信息 */
			Map<String, Object> session = this.getSession();
			session.put(Constants.COOKIE_LOGIN_UUID, uuid); // UUID
			session.put(Constants.LOGIN_USER_KEY, uservo); // 用户对象
			
			// 日志
			this.getSysLogger().info(new Log("user", "login", "doLogin", uservo.getUsername(), 
					format.format(new Date()), null, uservo.getId(), "User"));
			
			msg.setKey(SUCCESS);
			msg.setMsg("登录成功");
		} catch (Exception e) {
			new BusinessException(e, this.getClass().getName(), "doLogin");
			msg.setKey(ERROR);
			msg.setMsg("登录失败，服务器异常");
		}
		return SUCCESS;
	}
	
	/**
	 * <p>注册用户</p>
	 * 验证用户名是否重复：姓名和手机号必须至少有一个唯一
	 * 支持姓名和手机号登录：姓名重名时用手机号登录
	 * @return 返回json格式的操作消息字符串
	 * @author lipp
	 * @date 2015-01-19
	 */
	@Action(value = "register", results = {@Result(type="json", params={"root", "msg"})})
	public String register(){
		msg = new Msg();
		try {
			/** TODO 二次验证 */
			
			/** 验证用户名是否重复 */
			UserVO vo = new UserVO();
			vo.setName(model.getName());
			List<UserVO> userList = userService.getByProperty(vo);
			if(userList != null && userList.size() > 0) {
				msg.setKey(ERROR);
				msg.setMsg("姓名已存在！");
				return SUCCESS;
			}
			vo = new UserVO();
			vo.setMobile(model.getMobile());
			userList = userService.getByProperty(vo);
			if(userList != null && userList.size() > 0) {
				msg.setKey(ERROR);
				msg.setMsg("手机号已存在！");
				return SUCCESS;
			}
			
			/** 保存用户数据 */
			String password = model.getPassword();
			password = MD5.encode(password);
			password += SystemProperties.MD5_SUFFIX; // 密码连接后缀
			model.setPassword(password);
			String userId = userService.saveUser(model);
			if(userId == null) {
				msg.setKey(ERROR);
				msg.setMsg("注册失败");
			} else {
				msg.setKey(SUCCESS);
				msg.setMsg("注册成功");
				// 日志
				this.getSysLogger().info(new Log("user", "register", "register", userId, 
						format.format(new Date()), null, userId, "User"));
			}
			return SUCCESS;
		} catch (Exception e) {
			new BusinessException(e, this.getClass().getName(), "register");
			msg.setKey(ERROR);
			msg.setMsg("注册失败，服务器异常");
			return SUCCESS;
		}
	}
	
	
	/**
	 * <p>用户登出</p>
	 * 跳转到登出页面，然后再跳转到登录页面
	 * 
	 * @return
	 * @author lipp
	 * @date 2014-11-14
	 */
	@Action(value="doLogout", results={@Result(location="/modules/logout.jsp")})
	public String doLogout(){
		try{
			// 清缓存
			clearCache();
			return SUCCESS;
		} catch (Exception e) {
			new BusinessException(e, this.getClass().getName(), "doLogout");
			return "Exception";
		}
	}
	
	/**
	 * <p>用户登出</p>
	 * 直接跳转到登录页面
	 * 
	 * @return
	 * @author lipp
	 * @date 2014-11-14
	 */
	@Action(value="logout", results={@Result(type="redirectAction", 
			params={"namespace","/user", "actionName", "login"}) })
	public String logout(){
		try{
			// 清缓存
			clearCache();
			return SUCCESS;
		} catch (Exception e) {
			new BusinessException(e, this.getClass().getName(), "logout");
			return "Exception";
		}
	}

	/**
	 * <p>清空缓存</p>
	 * 清除cookie信息和缓存服务器中的登录信息
	 * 
	 * @author lipp
	 * @date 2014-11-14
	 */
	public void clearCache(){
		CookieUtil cookieUtil = new CookieUtil(this.getRequest(), 
				this.getResponse(), 0); // cookie对象，缓存超时：0小时
		Map<String, Object> session = this.getSession();
		
		try {
			session.clear();
		} catch (Exception e) {
			Cookie cookie = cookieUtil.getCookie(Constants.COOKIE_USER_ID);
			if(cookie != null){
				if(!session.isEmpty()){
					// 清除会话中用户唯一登录标识
					session.remove(Constants.COOKIE_LOGIN_UUID);
					// 清除会话中用户对象
					session.remove(Constants.LOGIN_USER_KEY);
				}
			}
		}
		// 删除cookie信息
		cookieUtil.deleteCookies();
	}
	
}
