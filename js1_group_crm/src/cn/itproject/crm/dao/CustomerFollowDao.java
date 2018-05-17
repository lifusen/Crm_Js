package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 客户跟进DAO接口
 * @author MrLiu
 *
 */
public interface CustomerFollowDao extends BaseDao<CustomerFollow>{
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
	public List<Map<String, Object>> getReminderList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds,String keyword,String orderColumn,OrderByType orderByType, Integer type) throws Exception;
	
	/**
	 * 查询工作提醒列表总行数
	 * @param employeeIds 员工ID
	 * @param keyword 搜索关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getReminderListCount(List<Integer> employeeIds,
			String keyword, Integer type) throws Exception;
	
	public List<Map<String, Object>> queryReport(String edtDateBegin,String edtDateEnd) throws Exception;

	public List<Map<String, Object>> queryReport(List<Integer> eIds,
			String edtDateBegin, String edtDateEnd) throws Exception;
	
	/**
	 * 获取提醒总量
	 * @param currentDate 日期
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getRemindTotalCount(String sqlName,String date,List<Integer> employeeIds,Integer type) throws Exception;
	
	public List<Map<String, Object>> getDepartmentReport(List<Integer> dIds,
			String edtDateBegin, String edtDateEnd) throws Exception;
	
	/**
	 * 获取已上门和已签单数据
	 * @param date 日期
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getVisitOrSignRemindTotalCount(Integer type,String sqlName,String date,List<Integer> employeeIds) throws Exception;

	public List<Map<String, Object>> getReportList(Integer reportType,
			Integer id, String eOrd,String dateStr, Integer pageIndex, Integer pageSize,String whereKey) throws Exception;

	public Integer getReportListCount(Integer reportType, Integer id,
			String eOrd, String dateStr,String whereKey) throws Exception;

	public List<Map<String, Object>> getChargeDataForDemp(String totalOrMonth,
			String sql) throws Exception;

	public List<Map<String, Object>> getChargeDataForDemp(String totalOrMonth,
			String sql, List<Integer> ids) throws Exception;

	public List<Map<String, Object>> getMoneyDataForDemp(String totalOrMonth,
			String sqlName) throws Exception;

	public List<Map<String, Object>> getMoneyDataForEmp(String totalOrMonth,
			String sqlName, List<Integer> eIds) throws Exception;
	
	/**
	 * 获取今日提醒数据
	 * @param employeeId
	 * @param type null:提醒人数(不包含还款)/1:还款提醒
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTodayRemindCountOfEmp(Integer employeeId,Integer type) throws Exception;

	/**
	 * 获取业务员下有效客户的数量
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public Integer getVisibilityCount(Integer employeeId) throws Exception;

}
