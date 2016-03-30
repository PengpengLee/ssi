package cn.itcast.ssi.sysmgmt.dao;

import java.util.List;

import cn.itcast.ssi.common.entity.Log;
import cn.itcast.ssi.common.entity.PageListData;

public interface ISysLogDAO {

	Object save(Log log) throws Exception;

	/**
	 * <p>分页获取日志数据：获取数据总量</p>
	 * 
	 * @param pageData 分页信息 
	 * @param sf 查询字段
	 * @param ss 查询条件
	 * @return 返回数据总量，整数
	 */
	int getLogCount(PageListData pageData, String sf, String ss);

	/**
	 * <p>分页获取日志数据：获取数据列表</p>
	 * 
	 * @param pageData 分页信息 
	 * @param sf 查询字段
	 * @param ss 查询条件
	 * @return 返回数据列表
	 */
	List<Log> getLogList(PageListData pageData, String sf, String ss);

}
