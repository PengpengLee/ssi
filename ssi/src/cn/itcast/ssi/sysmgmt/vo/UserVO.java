package cn.itcast.ssi.sysmgmt.vo;

import java.util.List;

import cn.itcast.ssi.common.interfaces.ISysUser;
import cn.itcast.ssi.common.vo.EntityVO;

public class UserVO extends EntityVO implements ISysUser {

	/** 帐户信息。以下字段都可以用作登录帐号 **/
	/** 用户名：帐号 */
	private String username;
	/** 密码 */
	private String password;
	/** 用户姓名 */
	private String name;
	/** 电子邮件 */
	private String email;
	/** 手机 */
	private String mobile;
	/** qq */
	private String qq;
	/** 微信 */
	private String weChat;

	/** 附属信息 **/
	/** 性别。MALE-男; FEMAIL-女 */
	private String sex;
	/** 生日。格式：yyyy-MM-dd */
	private String birthday;
	/** 爱好 */
	private String hobbit;
	/** 个人简介 */
	private String intruduce;
	/** 身份证编号 */
	private String card;
	/** 所属省 */
	private String province;
	/** 城市 */
	private String city;
	/** 区/县 */
	private String district;
	/** 街道 */
	private String street;
	/** 地址 */
	private String address;
	/** 固定电话 */
	private String telephone;
	/** 头像路径 */
	private String headPath;

	/** 以下属性与UserPO无关 */
	/** 用户类型（冗余） */
	private String userType = "20";
	/** 用户角色：每个用户只有一个角色 */
	private RoleVO roleVO;
	

	/***** getter and setter *****/
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHobbit() {
		return hobbit;
	}

	public void setHobbit(String hobbit) {
		this.hobbit = hobbit;
	}

	public String getIntruduce() {
		return intruduce;
	}

	public void setIntruduce(String intruduce) {
		this.intruduce = intruduce;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public RoleVO getRoleVO() {
		return roleVO;
	}

	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}

	@Override
	public String toString() {
		String json = "{\"username\":\""+this.username+"\",\"password\":\""+this.password+"\",\"name\":\""+this.name+"\",\"mobile\":\""+this.mobile+"\",\"userType\":\""+this.userType
				+"\",\"roleVO\":{\"name\":\""+this.roleVO.getName()+"\",\"code\":\""+this.roleVO.getCode()+"\",\"menuVOList\":[";
		List<MenuVO> menuVOList = roleVO.getMenuVOList();
		for (int i = 0; i < menuVOList.size(); i++) {
			MenuVO menuVO = menuVOList.get(i);
			json += "{\"name\":\""+menuVO.getName()+"\",\"icon\":\""+menuVO.getIcon()+"\",\"roleId\":\""+menuVO.getRoleId()+"\",\"actionUrl\":\""+menuVO.getActionUrl()+"\",\"type\":\""+menuVO.getType()+"\",\"remark\":\""+menuVO.getRemark()+"\",\"id\":\""+menuVO.getId()+"\",\"createTime\":\""+menuVO.getCreateTime()+"\",\"del\":\""+menuVO.getDel()+"\",\"color\":\""+menuVO.getColor()+"\"}";
			if(i < menuVOList.size() - 1) {
				json += ",";
			}
		}
		json += "],\"id\":\""+this.roleVO.getId()+"\",\"createTime\":\""+this.roleVO.getCreateTime()+"\",\"del\":\""+this.roleVO.getDel()+"\"}," +
				"\"id\":\"C2848E4C98F7463680B35F88166CEC3F\",\"createTime\":\"2015-12-10 14:10:46\",\"del\":\"1\"}";
		return json;
	}

	
}
