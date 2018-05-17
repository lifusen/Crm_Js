package cn.itproject.crm.service;

import java.util.List;

import cn.itproject.crm.bean.ContractManager;
import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.bean.Employee;

public interface ContractManagerService extends BaseService<ContractManager> {

	/**
	 * 根据客户状态获取客户列表
	 * 
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param state
	 *            状态
	 * @param eId
	 *            员工id
	 * @return
	 * @throws Exception
	 */
	List<ContractManager> getListByState(Integer pageIndex, Integer pageSize, Integer state, Integer eId)
			throws Exception;

	/**
	 * 根据Id获取合同对象
	 * 
	 * @param id
	 * @return
	 */
	ContractManager getContManaById(Integer id) throws Exception;

	/**
	 * 管理人员添加合同
	 * 
	 * @param customer
	 * @param loginEmployee
	 */
	void addManagerContract(ContractManager contractManager, Employee employee) throws Exception;

	/**
	 * 修改合同的内容
	 * 
	 * @param contractManager
	 */
	void updateManagerContract(ContractManager contractManager) throws Exception;

}
