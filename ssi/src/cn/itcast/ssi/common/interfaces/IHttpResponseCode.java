package cn.itcast.ssi.common.interfaces;

/**
 * 状态码分为两部分： 公共类型 操作结果：成功、失败、异常、系统或数据错误。公共条件的检查：必要项错误(如，必要项缺失，约定值中不存在该值，
 * 或值不符合要求格式等。本状态码只用于记录登录日志、操作日志等，不外发。)、多次出现的检查项(如，用户email的未填或格式错误的状态码，多次出现，
 * 即可提取到公共区。) 接口私有类型 操作结果：其他操作结果状态码(各种提示)。条件检查：必要项缺失(各种提示)、条件项不合法(不合法提示)。
 * 信息按照接口划分块，10或20一块。每个接口一或多块。每个块儿开头用注释区分。
 * 常量名命名规则：MSG_接口简称_信息描述对象(如用户名，该部分可用_隔开单词
 * )_信息类型(FAILED、SUCCESS、EXCEPTION、ERROR、NULL、
 * EXISTS、NOTEXISTS、AVAILABLE、UNAVAILABLE等)
 * 值定义规范：带有正确、可用、成功等类型信息为正值字符串；带有错误、不可用、失败等类型信息为负数字符串。正负对应的信息绝对值相同。
 * 
 * @author lipp
 * 
 */
public interface IHttpResponseCode {

	/** 公共信息块 +-1000开始 预留100个 */
	// 操作结果
	/** 成功 */
	public static final String MSG_COMMON_SUCCESS = "+1000";
	/** 失败(可建议用户尝试重新操作该功能) */
	public static final String MSG_COMMON_FAILED = "-1000";
	/** 异常(可建议用户稍后尝试重新操作该功能) */
	public static final String MSG_COMMON_EXCEPTION = "-1001";
	/** 系统或数据错误(可建议用户尝试重新登录并启用该应用) */
	public static final String MSG_COMMON_ERROR = "-1002";

	
	/** 不外发，服务端自用 用户id错误 */
	public static final String MSG_COMMON_USERID_ERROR = "-1005";
	/** 不外发，服务端自用 终端类型错误 */
	public static final String MSG_COMMON_ORIGIN_ERROR = "-1006";
	/** 不外发，服务端自用 设备编号错误 */
	public static final String MSG_COMMON_DEVICECODE_ERROR = "-1007";
	/** 不外发，服务端自用 用户类型错误 */
	public static final String MSG_COMMON_USERTYPE_ERROR = "-1008";
	/** 不外发，服务端自用 用户密码错误 */
	public static final String MSG_COMMON_USERPASSWD_ERRORE = "-1009";
	// 多次出现的检查项
	/** 必须填写且格式正确的Email */
	public static final String MSG_COMMON_USEREMAIL_UNAVAILABLE = "-1020";
	/** 必须填写且格式正确的密码 */
	public static final String MSG_COMMON_USERPASSWD_UNAVAILABLE = "-1021";

	/** 接口私有信息块 +-1100开始 */
	// 接口：用户pad登录。UserGetAction.loginVerify() +-1100开始 预留10个
	/** 输入的Email不存在 */
	public static final String MSG_LOGIN_USEREMAIL_NOTEXISTS = "-1100";
	/** 输入的密码错误 */
	public static final String MSG_LOGIN_USERPASSWD_NOTEXISTS = "-1101";
	/** 不允许在其他厂商的pad登录 */
	public static final String MSG_LOGIN_DEVICECODE_UNAVAILABLE = "-1102";

	// 接口：注册界面中检查email是否可用。UserGetAction.checkUser() +-1110开始 预留10个
	/** 该email已被注册 */
	public static final String MSG_CHECKUSER_USEREMAIL_EXISTS = "-1110";

	// 接口：家长注册前的检查。学生只允许附属注册1位家长，检查学生是否已注册家长。UserGetAction.checkIsRegisterParent()
	// +-1120开始 预留10个
	/** 当前学生已注册parent */
	public static final String MSG_CHECKREGISTPARENT_PARENT_EXISTS = "-1120";

	// 接口：用户基本信息注册。UserGetAction.registerUser() +-1130开始 预留10个
	/** 当前学生已注册parent */
	public static final String MSG_REGISTUSER_PARENT_EXISTS = "-1130";
	/** 当前pad已被注册(目前要求，一台pad只能注册一个我们的学生类型用户。) */
	public static final String MSG_REGISTUSER_PAD_UNAVAILABLE = "-1131";
	/** 该email已被注册 */
	public static final String MSG_REGISTUSER_USEREMAIL_EXISTS = "-1132";

	// 接口：修改用户密码。UserInfoAction.updateUserPasswd() +-1140开始 预留10个
	/** 必须填写且格式正确的新密码 */
	public static final String MSG_UPDUSERPASSWD_USERPASSWDNEW_UNAVAILABLE = "-1140";
	/** 原密码不正确 */
	public static final String MSG_UPDUSERPASSWD_USERPASSWD_ERROR = "-1141";

	// 接口：智能诊断-根据学科id，年级，学期查找教材。TextbookAction.getTextbookById() +-1150开始 预留10个
	/** 必须选择学科 */
	public static final String MSG_GETTEXTBOOKBYID_SUBJECTID_NULL = "-1150";
	/** 必须选择年级 */
	public static final String MSG_GETTEXTBOOKBYID_GRADE_NULL = "-1151";
	/** 必须选择学期 */
	public static final String MSG_GETTEXTBOOKBYID_TERM_NULL = "-1152";

	// 接口：获取试题详情。ThemeGetAction.detail() +-1160开始 预留10个
	/** 必须选择学科 */
	public static final String MSG_DETAIL_SUBJECTID_NULL = "-1160";
	/** 必须选择试题 */
	public static final String MSG_DETAIL_THEMEID_NULL = "-1161";

	// 接口：保存用户的测评数据。OnlineEvalAction.insertAnswerData() +-1170开始 预留10个
	/** 当前用户缺乏省编号 */
	public static final String MSG_INSTANSWDATA_USERAREA_NULL = "-1170";
	/** 按条件，可排名的范围内，没有其他用户，无法排名 */
	public static final String MSG_INSTANSWDATA_OTHERUSER_NULL = "-1171";

	// 接口：修改用户个人信息。UserInfoAction.updateUserInfo() +-1180开始 预留10个
	/** 当前修改项不能为空 */
	public static final String MSG_UPDUSERINFO_PARAM_NULL = "-1180";

	// 拦截器：当用户id为空或不存在 +-1190开始 预留10个
	/** 当前修改项不能为空 */
	public static final String MSG_USER_ID_NULL = "-1190";

	// 接口：检查pad是否可用。UserGetAction.checkDevice() +-1200开始 预留10个
	/** 当前pad已被注册(目前要求，一台pad只能注册一个我们的学生类型用户。) */
	public static final String MSG_CHECKDEVICE_PAD_UNAVAILABLE = "-1200";

}
