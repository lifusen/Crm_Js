package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.db.BaseDao;

/**
 * 释放到公共池
 * 
 * @author MrLiu
 *
 */
public interface ReleaseCommonPoolDao extends BaseDao<Customer> {

	/**
	 * 批量释放客户到公共池
	 * 
	 * @param ids
	 *            客户ID列表
	 * @param releaseType
	 *            释放类型(releaseType):1(job自动释放),2(在批量释放客户到公共池列表批量释放(手动操作)),3(admin在数据库操作)
	 * @param releaseId
	 *            释放人id(releaseId):批量释放时,记录是操作人id , 0表示job释放
	 * @throws Exception
	 */
	public void release(List<Integer> ids, Integer releaseType, Integer releaseId) throws Exception;

}
