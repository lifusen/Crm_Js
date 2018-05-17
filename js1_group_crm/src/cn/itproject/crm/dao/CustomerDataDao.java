package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.db.BaseDao;

/**
 * 2016-3-6 客户数据
 * 
 * @author MrLiu
 *
 */
public interface CustomerDataDao extends BaseDao<Customer> {
	/**
	 * 根据部门id,员工id,客户等级,关注等级获取客户id
	 * 
	 * @param departmentId
	 *            部门id
	 * @param employeeId
	 *            员工id
	 * @param customerLevel
	 *            客户等级名称
	 * @param attentionLevel
	 *            关注等级名称
	 * @return
	 */
	public List<Integer> getCustomerList(Integer departmentId, Integer employeeId, String customerLevel,
			String attentionLevel) throws Exception;
}
