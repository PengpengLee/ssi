package cn.itcast.ssi.common.constant;

/**
 * <p>
 * 系统常量
 * </p>
 * 
 * @author lipp
 * 
 */
public class Constants {

	/** 公共后缀 */
	public static final String COMMON_SUFFIX = "ITCAST";

	/** 系统配置：静态文件服务器地址在配置文件中的key */
	public static final String STATIC_PATH_KEY = "static.path";
	/** 系统配置：日志开关存储在配置文件里的key */
	public static final String LOG_SWITCH_KEY = "log_switch";
	/** 系统配置：MD5加密前缀存储在配置文件里的key */
	public static final String MD5_SUFFIX_KEY = "md5_suffix";

	/** 验证码放到session中的key */
	public static final String VERYCODE = "verycode";

	/** 会话中存放当前登录用户信息的KEY */
	public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
	/** 会话中存放当前登录用户唯一性登录标识的KEY */
	public static final String LOGIN_USER_UUID = "LOGIN_USER_UUID";

	/** cookie过期时间。10分钟：单位为秒；默认值 */
	public final static int COOKIE_AGE_TEN_MINUTES = 60 * 10;
	/** cookie过期时间。一小时：单位为秒；默认值 */
	public final static int COOKIE_AGE_HOUR = 60 * 60;
	/** cookie过期时间。一天：单位为秒 */
	public final static int COOKIE_AGE_DAY = 60 * 60 * 24;
	/** cookie过期时间。一周：单位为秒 */
	public final static int COOKIE_AGE_WEEK = 60 * 60 * 24 * 7;
	/** cookie过期时间。一月：单位为秒 */
	public final static int COOKIE_AGE_MONTH = 60 * 60 * 24 * 7 * 30;
	/** cookie中存放用户ID的key */
	public static final String COOKIE_USER_ID = "COOKIE_USER_ID";
	/** cookie中存放用户名的key */
	public static final String COOKIE_USER_NAME = "COOKIE_USER_NAME";
	/** cookie中存放用户唯一性登录标识的key */
	public static final String COOKIE_LOGIN_UUID = "COOKIE_LOGIN_UUID";

	/** 删除状态：已删除 */
	public static final String DEL_STATUS_YES = "0";
	/** 删除状态：未删除（默认） */
	public static final String DEL_STATUS_NO = "1";

	/** 前台保存消息：保存成功 */
	public static final String MSG_SAVE_SUCCESS = "保存成功";
	/** 前台删除消息：删除成功 */
	public static final String MSG_DEL_SUCCESS = "删除成功";
	/** 前台修改消息：修改成功 */
	public static final String MSG_UPDATE_SUCCESS = "修改成功";
	/** 前台操作消息：操作失败 */
	public static final String MSG_OPERATE_FAIL = "操作失败";

}
