package cn.itproject.crm.dao;

import cn.itproject.crm.bean.ContractManager;
import cn.itproject.crm.db.BaseDao;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月4日
 *
 *       version 1.0
 */
public interface ContractManagerDao extends BaseDao<ContractManager> {

	/**
	 * 根据项目ID获取项目信息
	 * 
	 * @param ItemNo
	 *            项目ItemNo
	 * @return
	 */
	ContractManager getContractManagerById(Integer id) throws Exception;

	/**
	 * 更新合同的信息
	 * 
	 * @param contractManager
	 *            合同信息
	 * @throws Exception
	 */
	void updateContractManager(ContractManager contractManager) throws Exception;

	/**
	 * 添加合同信息的录入
	 * 
	 * @param contractManager
	 *            合同信息
	 * @throws Exception
	 */
	void addContractManager(ContractManager contractManager) throws Exception;

}
