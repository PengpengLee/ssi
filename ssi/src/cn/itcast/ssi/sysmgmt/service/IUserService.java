package cn.itcast.ssi.sysmgmt.service;

import java.util.List;

import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.service.IBaseService;
import cn.itcast.ssi.sysmgmt.vo.UserVO;

/**
 * <p>用户服务接口</p>
 * 提供所有关于用户操作的服务
 * 
 * @author lipp
 * @date 2015-01-18
 *
 */
public interface IUserService extends IBaseService{
	
	/**
	 * <p>用户登录</p>
	 * 1.获取用户基本信息；<br>
	 * 2.获取用户角色信息；<br>
	 * 3.获取用户菜单列表；<br>
	 * 4.获取用户班级列表；<br>
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回按用户名密码查询用户表得到的查询结果，若该用户不存在返回null
	 * @author lipp
	 * @date 2014-11-18
	 */
	public UserVO getUserForLogin(String username, String password) throws Exception;
	
	/**
	 * <p>根据用户ID获取用户对象</p>
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public UserVO getUserById(String userId) throws Exception;
	
	/**
	 * <p>根据属性获取User对象</p>
	 * @param uservo
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> getByProperty(UserVO uservo) throws Exception;
	
	/**
	 * <p>分页查询数据列表</p>
	 * 
	 * @param page 当前页
	 * @param rows 每页记录数
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @param flag 查询是否为某一角色用户的标识
	 * @param model 查询条件
	 * @return 返回分页结果集，用户JQuery的表格组件接收数据
	 */
	public PageResultSet<UserVO> getPageList(Integer page, Integer rows,
			String sidx, String sord, boolean flag, UserVO model) throws Exception;
	
	/**
	 * <p>保存用户对象</p>
	 * 注册用户都是普通用户，都必须插入角色表和用户表的关联表数据
	 * 
	 * @param uservo 封装用户数据的对象
	 * @return 返回保存后数据的主键
	 * @throws Exception
	 */
	public String saveUser(UserVO uservo) throws Exception;
	
	/**
	 * <p>删除用户对象</p>
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delUserById(String id) throws Exception;

}
