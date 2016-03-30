package cn.itcast.ssi.sysmgmt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.ssi.common.dao.impl.IBatisEntityDaoImpl;
import cn.itcast.ssi.common.entity.Log;
import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.sysmgmt.dao.ISysLogDAO;

/**
 * <p>日志数据访问类</p>
 * 
 * @author lipp
 * @date 2015-01-21
 *
 */
@SuppressWarnings("unchecked")
@Repository("sysLogDao")
public class SysLogDAOImpl extends IBatisEntityDaoImpl<Log, String> implements ISysLogDAO{


	@Override
	public Object save(Log log) throws Exception {
		return this.saveEntity("log.insertLog", log);
	}

	@Override
	public int getLogCount(PageListData pageData, String sf, String ss) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("sf", sf);
		map.put("ss", ss);
		Object o = this.getEntity("log.getLogListCount", map);
		if(o != null)
			return Integer.parseInt(o + "");
		return 0;
	}

	@Override
	public List<Log> getLogList(PageListData pageData, String sf, String ss) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageData", pageData);
		map.put("sf", sf);
		map.put("ss", ss);
		return this.getList("log.getLogList", map);
	}
}
