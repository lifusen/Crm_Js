package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.DailyBasicData;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 客户跟进业务接口
 * @author MrLiu
 *
 */
public interface CustomerFollowService extends BaseService<CustomerFollow>{

	public List<CustomerFollow> getListByCustomerId(Integer customerId,Integer pageIndex,Integer pageSize) throws Exception;
	
	public Integer getCountByCustomerId(Integer customerId) throws Exception;

	public List<CustomerFollow> getCustomerFollowsByEmployeeId(Integer pageIndex, Integer pageSize,Integer id) throws Exception;

	public Integer getCountByEmployeeId(Integer id) throws Exception;
	
	
	/**
	 * 第一次添加客户时,系统默认添加一行跟进记录
	 * @param addType 添加客户的类型
	 * @param customerId 客户ID
	 * @param employeeId 员工ID
	 * @throws Exception
	 */
	public void addDefaultCustomerFollow(Integer addType,Integer customerId,Integer employeeId) throws Exception;
	
	/**
	 * 添加签单客户时,系统默认添加一行跟进记录:成功签约
	 * @param customerId 客户ID
	 * @param employeeId 员工ID
	 * @throws Exception
	 */
	public void addDefaultSignCustomerFollow(Integer customerId,Integer employeeId) throws Exception;
	
	/**
	 * 添加客户跟进记录
	 * @param customerFollow
	 * @param employee
	 * @param integer 
	 * @throws Exception
	 */
	public void addCustomerFollow(CustomerFollow customerFollow,Employee employee, Integer signCustomerId) throws Exception;

	/**
	 * 查询工作提醒列表
	 * @param pageIndex 页码
	 * @param pageSize	也大小
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @param orderColumn 排序列
	 * @param orderByType 排序方式
	 * @param type  0:管理层/1:业务部经理/2:业务员
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getReminderList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds,String keyword,String orderColumn,OrderByType orderByType, Integer type) throws Exception;

	/**
	 * 查询工作提醒列表总行数
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @param type  0:管理层/1:业务部经理/2:业务员
	 * @return
	 * @throws Exception
	 */
	public Integer getReminderListCount(List<Integer> employeeIds,
			String keyword, Integer type) throws Exception;

	/**
	 * 业务报表统计
	 * @param edtDateBegin
	 * @param edtDateEnd
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getReport(String edtDateBegin, String edtDateEnd) throws Exception;

	/**
	 * 员工业务反馈报表
	 * @param eIds
	 * @param edtDateBegin
	 * @param edtDateEnd
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getReport(List<Integer> eIds,
			String edtDateBegin, String edtDateEnd) throws Exception;

	public List<Map<String, Object>> getDepartmentReport(List<Integer> dIds,
			String edtDateBegin, String edtDateEnd) throws Exception;
	
	/**
	 * 获取部门提醒总量
	 * @param todayDate 当天
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getRemindTotalCountOfDept(String todayDate) throws Exception;

	/**
	 * 获取业务员提醒总量
	 * @param todayDate 当天
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getRemindTotalCountOfEmp(List<Integer> employeeIds,String todayDate) throws Exception;
	
	/**
	 * 获取部门提醒总量
	 * @param todayDate 当天
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getVisitOrSignRemindTotalCountOfDept(Integer type,String todayDate) throws Exception;

	/**
	 * 获取业务员提醒总量
	 * @param todayDate 当天
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getVisitOrSignRemindTotalCountOfEmp(Integer type,List<Integer> employeeIds,String todayDate) throws Exception;

	public List<Map<String, Object>> getReportList(Integer reportType,
			Integer id, String eOrd,String dateStr, Integer pageIndex, Integer pageSize,String whereKey) throws Exception;

	public Integer getReportListCount(Integer reportType, Integer id,
			String eOrd, String dateStr,String whereKey) throws Exception;

	public List<Map<String, Object>> getChargeDataForDemp(String totalOrMonth, String sql) throws Exception;
	public List<Map<String, Object>> getChargeDataForEmp(String totalOrMonth, String sql,List<Integer> ids) throws Exception;

	public List<Map<String, Object>> getMoneyDataForDemp(String totalOrMonth,
			String string) throws Exception;

	public List<Map<String, Object>> getMoneyDataForEmp(String totalOrMonth,
			String string, List<Integer> eIds) throws Exception;
	
	/**
	 * 获取今日提醒量
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTodayRemindCountOfEmp(Integer employeeId) throws Exception;
	/**
	 * 获取今日还款提醒量
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTodayRepaymentCountOfEmp(Integer employeeId) throws Exception;
	
	/**
	 * 是否能添加自留客户
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public Boolean isCanAddVisibility(Integer employeeId) throws Exception;
}
