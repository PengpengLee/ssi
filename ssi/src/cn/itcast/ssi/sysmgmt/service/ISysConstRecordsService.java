package cn.itcast.ssi.sysmgmt.service;

import cn.itcast.ssi.common.entity.Entity;
import cn.itcast.ssi.common.service.IBaseService;

/**
 * <p>系统常量记录服务接口</p>
 * 提供所有关于系统常量操作的服务，
 * 可以通过本接口对系统中使用的常量进行录入、修改、查询、删除等操作。<br>
 * 
 * 所有常量都是逻辑常量。<br>
 * 
 * @author lipp
 * @date 2015-01-18
 *
 */
public interface ISysConstRecordsService extends IBaseService{
	
	public void saveConst(Entity entity) throws Exception;
	public void deleteConst(Entity entity) throws Exception;
	public void updateConst(Entity entity) throws Exception;
	public void getConst(Entity entity) throws Exception;
	
}
