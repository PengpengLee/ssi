package cn.itcast.ssi.sysmgmt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.ssi.common.entity.Log;
import cn.itcast.ssi.common.entity.PageListData;
import cn.itcast.ssi.common.entity.PageResultSet;
import cn.itcast.ssi.common.interfaces.ISystemLogger;
import cn.itcast.ssi.common.util.StringUtil;
import cn.itcast.ssi.sysmgmt.dao.ISysLogDAO;

/**
 * <p>
 * 日志数据服务类
 * </p>
 * 公共静态布尔型属性 <b>LOG_SWTICH</b> 作为日志开关： 
 * true表示将数据录入数据库，false表示将日志打印控制台，默认false
 * 
 * @author lipp
 * @date 2015-01-21
 * 
 */
@Service("sysLogService")
public class SysLogServiceImpl extends ISystemLogger {

	@Resource
	private ISysLogDAO sysLogDao;
	

	@Override
	public void error(Log log) throws Exception {
		log.setLogLevel(Log.ERROR);
		if (LOG_SWITCH) { // 保存日志到数据库
			if (StringUtil.isEmpty(log.getId())) {
				log.setId(this.newEntityKey());
			}
			sysLogDao.save(log);
		} else { // 打印到控制台
			System.out.println(log.toString());
		}
	}

	@Override
	public void info(Log log) throws Exception {
		log.setLogLevel(Log.INFO);
		if (LOG_SWITCH) { // 保存日志到数据库
			if (StringUtil.isEmpty(log.getId())) {
				log.setId(this.newEntityKey());
			}
			sysLogDao.save(log);
		} else { // 打印到控制台
			System.out.println(log.toString());
		}
	}

	@Override
	public void warn(Log log) throws Exception {
		log.setLogLevel(Log.WARN);
		if (LOG_SWITCH) { // 保存日志到数据库
			if (StringUtil.isEmpty(log.getId())) {
				log.setId(this.newEntityKey());
			}
			sysLogDao.save(log);
		} else { // 打印到控制台
			System.out.println(log.toString());
		}
	}

	/**
	 * <p> 分页查询日志列表 </p>
	 * 
	 * @param page 当前页
	 * @param rows 每页记录数
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @param sf 查询字段
	 * @param ss 搜索字符
	 * @return 返回分页结果集，用户JQuery的表格组件接收数据
	 */
	public PageResultSet<Log> getLogList(Integer page, Integer rows,
			String sidx, String sord, String sf, String ss) {
		PageResultSet<Log> result = new PageResultSet<Log>();
		result.setPage(page);
		result.setRows(rows);
		
		PageListData pageData = new PageListData();
		pageData.setStart((page - 1) * rows + 1);
		pageData.setEnd(page * rows);
		if(!StringUtil.isEmpty(sidx)){
			pageData.setSidx(sidx);
		}
		if(!StringUtil.isEmpty(sord)){
			pageData.setSord(sord);
		}
		
		int count = sysLogDao.getLogCount(pageData, sf, ss);
		List<Log> logList = new ArrayList<Log>();
		if(count > 0){
			logList = sysLogDao.getLogList(pageData, sf, ss);
		}
		result.setTotal(count);
		result.setData(logList);
		
		return result;
	}
	
	/**
	 * <p> 分页查询日志列表 </p>
	 * 
	 * @param page 当前页
	 * @param rows 每页记录数
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @param sf 查询字段
	 * @param ss 搜索字符
	 * @return 返回分页结果集，用户JQuery的表格组件接收数据
	 */
	public PageResultSet<Log> getLogList(Integer page, Integer rows,
			String sidx, String sord) {
		return getLogList(page, rows, sidx, sord, null, null);
	}
	
	/**
	 * <p> 分页查询日志列表 </p>
	 * 
	 * @param page 当前页
	 * @param rows 每页记录数
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @param sf 查询字段
	 * @param ss 搜索字符
	 * @return 返回分页结果集，用户JQuery的表格组件接收数据
	 */
	public PageResultSet<Log> getLogList(Integer page, Integer rows) {
		return getLogList(page, rows, null, null, null, null);
	}
}
