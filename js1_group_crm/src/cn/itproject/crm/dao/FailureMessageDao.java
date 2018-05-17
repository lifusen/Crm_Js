package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.FailureMessage;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 签单失败消息数据访问层接口
 * @author MrLiu
 *
 */
public interface FailureMessageDao extends BaseDao<FailureMessage>{
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
