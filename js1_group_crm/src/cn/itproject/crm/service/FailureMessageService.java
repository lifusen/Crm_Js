package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.FailureMessage;
import cn.itproject.crm.db.utils.OrderByType;


/**
 * 签单放款失败消息业务层接口
 * @author MrLiu
 *
 */
public interface FailureMessageService extends BaseService<FailureMessage>{

	/**
	 * 添加失败消息
	 * @param failureMessage
	 */
	void addFailureMessage(FailureMessage failureMessage) throws Exception;
	
	/**
	 * 根据签单客户ID获取失败消息集合
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	List<FailureMessage> getFailureMessageBySignCustomerId(Integer signCustomerId) throws Exception;
	
	/**
	 * 查询工作提醒列表
	 * @param pageIndex 页码
	 * @param pageSize	也大小
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @param orderColumn 排序列
	 * @param orderByType 排序方式
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getRejectCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds,String keyword,String orderColumn,OrderByType orderByType) throws Exception;

	/**
	 * 查询工作提醒列表总行数
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getRejectCustomerListCount(List<Integer> employeeIds,
			String keyword) throws Exception;
	
	
}
