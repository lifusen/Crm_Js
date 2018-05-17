package cn.itproject.crm.service;

import cn.itproject.crm.bean.Customer;

public interface ReleaseCommonPoolService extends BaseService<Customer> {

	/**
	 * 客户移交列表中的批量释放客户到公共池
	 * 
	 * @param departmentId
	 *            部门id
	 * @param employeeId
	 *            业务员id
	 * @param customerLevel
	 *            客户等级名称
	 * @param attentionLevel
	 *            关注等级名称
	 * @param releaseId
	 *            操作人ID
	 * @throws Exception
	 */
	public void batchRelease(Integer departmentId, Integer employeeId, String customerLevel, String attentionLevel,
			Integer releaseId) throws Exception;

	public Integer getBatchReleaseCount(Integer departmentId, Integer employeeId, String customerLevel,
			String attentionLevel) throws Exception;

}
