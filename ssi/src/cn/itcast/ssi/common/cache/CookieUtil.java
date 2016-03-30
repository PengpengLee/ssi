package cn.itcast.ssi.common.cache;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.ssi.common.constant.Constants;

/**
 * <p>cookie工具类</p>
 * 须先创建本类对象才可以使用相应方法
 * 
 * @author lipp
 *
 */
public class CookieUtil {

	private HttpServletRequest request;
	private HttpServletResponse response;

	/** cookie的时限，表示该cookie经过多长秒后被删除 */
	private int age = -1;

	/**
	 * <p>
	 * 创建<b> CookieUtil </b>对象，通过此对象操作cookie
	 * </p>
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param response
	 *            HttpServletResponse对象
	 * @param age
	 *            cookie的时限：<br>
	 *            1.设置为0表示立即删除；<br> 
	 *            2.-1表示该cookie存储在浏览器进程中（内存中保存），关闭浏览器即失效；<br>
	 */
	public CookieUtil(HttpServletRequest request, HttpServletResponse response,
			int age) {
		this.request = request;
		this.response = response;
		this.age = age;
	}
	
	/**
	 * <p>创建<b> CookieUtil </b>对象，通过此对象操作cookie</p>
	 * 默认cookie的时限为1小时
	 * 
	 * @param request
	 * @param response
	 */
	public CookieUtil(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.age = Constants.COOKIE_AGE_HOUR;
	}

	/**
	 * <p>添加cookie</p>
	 * 
	 * @param name cookie的名称，即KEY
	 * @param value cookie的值
	 */
	public void addCookie(String name, String value) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath("/");// Cookies对此域下所有页面有效，暂时不允许动态设置此项
		cookies.setMaxAge(age);
		addCookie(cookies);
	}
	
	/**
	 * <p>添加cookie</p>
	 * 
	 * @param cookie cookie对象
	 */
	public void addCookie(Cookie cookie) {
		response.addCookie(cookie);
	}

	/**
	 * <p>根据cookie名称获取cookie对象</p>
	 * 
	 * @param cookieName cookie的名称，即key
	 * @return 返回给定名称对应的cookie对象，若不存在则返回null
	 */
	public Cookie getCookie(String cookieName) {
		Cookie[] cookies = this.request.getCookies();
		if(cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * <p>根据cookie名称获取cookie对象的值</p>
	 * 
	 * @param cookieName
	 * @return 返回给定名称对应的值，若不存在则返回null
	 */
	public String getCookieValue(String cookieName) {
		Cookie cookie = this.getCookie(cookieName);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * <p>删除指定名称的cookie对象</p>
	 * 
	 * @param cookieName cookie名
	 */
	public void deleteCookie(String cookieName) {
		Cookie cookie = this.getCookie(cookieName);
		if (cookie != null) {
			cookie.setMaxAge(0);// 如果0，就说明立即删除
			cookie.setPath("/");// 不要漏掉
			addCookie(cookie);
		}
	}

	/**
	 * <p>删除全部cookie对象</p>
	 */
	public void deleteCookies() {
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies) {
				deleteCookie(cookie.getName());
			}
		}
	}

}
