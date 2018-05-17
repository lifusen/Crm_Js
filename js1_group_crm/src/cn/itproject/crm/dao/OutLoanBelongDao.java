package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.db.BaseDao;

public interface OutLoanBelongDao extends BaseDao<OutLoanBelong>{
	
	/**
	 * 获取指定签单对应的指定权证 类型
	 * @param signCustomerId
	 * @param employeeId
	 * @return
	 * @throws Exception
	 * 2017年1月13日 下午12:51:55 by SwordLiu
	 */
	public Integer getType(Integer signCustomerId, Integer employeeId) throws Exception;

	/**
	 * 获取指定签单下的所有权证
	 * @param signCustomerId
	 * @return
	 * @throws Exception
	 * 2017年1月13日 下午12:53:35 by SwordLiu
	 */
	public List<OutLoanBelong> getOutLoanBelong(Integer signCustomerId) throws Exception;

	/**
	 * 删除指定签单所属的指定贷款人员删除对应的签单权证关系
	 * @param outLoanBelong
	 * @return
	 * @throws Exception
	 * 2017年1月13日 下午4:25:53 by SwordLiu
	 */
	public boolean removeAssistLoaner(OutLoanBelong outLoanBelong)  throws Exception;

	/**
	 * 获取指定的关系
	 * @param signCustomerId
	 * @param employeeId
	 * @return
	 * @throws Exception
	 * 2017年1月14日 下午2:29:30 by SwordLiu
	 */
	public OutLoanBelong getOutLoanBelong(Integer signCustomerId, Integer employeeId) throws Exception;

	/**
	 * 更改签单-权证 放款状态为： 1：代表已放款
	 * @param id
	 * @param warranterId
	 * @return 
	 * @throws Exception
	 * 2017年1月14日 下午3:51:18 by SwordLiu
	 */
	public Integer updateState(Integer id, Integer warranterId) throws Exception;

}
