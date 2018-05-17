package cn.itproject.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Customer.CustomerAddType;
import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.dao.CustomerFollowDao;
import cn.itproject.crm.dao.SignCustomerDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Service
public class CustomerFollowServiceImpl extends BaseServiceSupport<CustomerFollow> implements CustomerFollowService {
	@Resource
	private CustomerFollowDao customerFollowDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private CustomerService customerService;

	@Resource
	private SignCustomerDao signCustomerDao;

	@Override
	protected BaseDao<CustomerFollow> getBaseDao() {
		return customerFollowDao;
	}

	@Override
	public List<CustomerFollow> getListByCustomerId(Integer customerId, Integer pageIndex, Integer pageSize)
			throws Exception {
		// 查询客户跟进列表并封装成视图对象
		String hql = "select new CustomerFollow(c.id,c.remindTime,c.type,c.visibility,c.remindContent,c.feedbackContent,c.employee.name as employeeName,c.edtTime) from CustomerFollow c where c.customer.id = ? order by c.edtTime desc";
		return customerFollowDao.getList(pageIndex, pageSize, hql, customerId);
	}

	@Override
	public Integer getCountByCustomerId(Integer customerId) throws Exception {
		return customerFollowDao.count("customer.id = :cid", customerId);
	}

	@Override
	public List<CustomerFollow> getCustomerFollowsByEmployeeId(Integer pageIndex, Integer pageSize, Integer id)
			throws Exception {
		return getBaseDao().queryList(pageIndex, pageSize, "employee.id = :eid", null, id);
	}

	@Override
	public Integer getCountByEmployeeId(Integer id) throws Exception {
		return getBaseDao().count("employee.id = :eid", id);
	}

	@Override
	public void addDefaultCustomerFollow(Integer addType, Integer customerId, Integer employeeId) throws Exception {
		CustomerFollow customerFollow = new CustomerFollow();
		customerFollow.setCustomer(new Customer(customerId)); // 客户
		customerFollow.setEmployee(new Employee(employeeId)); // 操作人
		customerFollow.setRemindTime(null); // 提醒时间为空
		// 默认的提醒内容
		if (addType == null) {
			// no something
		} else if (addType == CustomerAddType.salesman.ordinal()) {
			customerFollow.setRemindContent("业务员新增客户");
		} else if (addType == CustomerAddType.excel.ordinal()) {
			customerFollow.setRemindContent("批量导入客户");
		} else if (addType == CustomerAddType.excelCommonPool.ordinal()) {
			customerFollow.setRemindContent("批量导入客户到公共池");
		}

		// 添加跟进消息
		customerFollowDao.save(customerFollow);
	}

	@Override
	public void addCustomerFollow(CustomerFollow customerFollow, Employee employee, Integer signCustomerId)
			throws Exception {
		// 设置录入时间
		customerFollow.setEdtTime(new Date());
		// 操作人
		customerFollow.setEmployee(employee);
		// 添加跟进公司id
		customerFollow.setCompanyId(employee.getCompanyId());
		// 添加跟进消息
		customerFollowDao.save(customerFollow);

		// 客户ID
		Integer customerId = customerFollow.getCustomer().getId();

		// 如果提醒类型是电话联系或上门洽谈,当前客户的通话或上门累加
		if (customerFollow.getType() == CustomerFollow.PHONE) {// 通话
			customerDao.addCustomerCallCount(customerFollow.getCustomer().getId()); // 累加客户通话次数
		} else if (customerFollow.getType() == CustomerFollow.VISIT) {// 上门
			customerDao.addCustomerVisitCount(customerFollow.getCustomer().getId()); // 累加客户上门次数
		}

		// 判断是否是第一次跟进(如果是新订单,则是第一次跟进)
		if (customerService.getCustomerState(customerId) == CustomerState.allocatedToEmp.ordinal()) {// 第一次跟进
			// 修改客户的状态为:跟进中
			customerDao.updateState(customerId, CustomerState.follow.ordinal());
		}
		// 修改客户最后一次跟进时间为当前时间
		customerDao.updateFlowDate(customerId);
		// 修改可见性(是否自留)
		customerDao.updateVisibility(customerId, customerFollow.getVisibility());
		// 设置修改时间
		customerDao.updateTime(customerId);

		// 如果权证跟进为放款进度信息，则跟新最后一次跟进进度listCourse
		if (customerFollow.getType() >= 100 && customerFollow.getType() <= 103) {
			customerDao.updateListCourse(customerId, customerFollow.getType());
			signCustomerDao.updateOrderType(signCustomerId, customerFollow.getType());
		}

		// 如果跟进类型和提醒时间不为null,则设置最后一次提醒的信息
		if (customerFollow.getType() != null && customerFollow.getRemindTime() != null) {
			// 如果是权证专员,则修改客户跟进信息为权证,否则为业务员
			if (LoginUserUtil.isWarrantRole()) {
				// 修改权证专员的最后一次跟进
				customerDao.updateWarrantLastRemindInfo(customerFollow);
			} else {
				// 修改业务员的最后一次跟进
				customerDao.updateLastRemindInfo(customerFollow);
			}
		} else {
			// 修改最后跟进的时间
			customerDao.updateLastRemindTime(customerId, customerFollow.getRemindTime());
		}

	}

	@Override
	public List<Map<String, Object>> getReminderList(Integer pageIndex, Integer pageSize, List<Integer> employeeIds,
			String keyword, String orderColumn, OrderByType orderByType, Integer type) throws Exception {
		return customerFollowDao.getReminderList(pageIndex, pageSize, employeeIds, keyword, orderColumn, orderByType,
				type);
	}

	@Override
	public Integer getReminderListCount(List<Integer> employeeIds, String keyword, Integer type) throws Exception {
		return customerFollowDao.getReminderListCount(employeeIds, keyword, type);
	}

	@Override
	public List<Map<String, Object>> getReport(String edtDateBegin, String edtDateEnd) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (edtDateBegin == null) {
			edtDateBegin = format.format(new Date());
			edtDateEnd = format.format(new Date());
		}
		System.out.println(edtDateBegin);
		System.out.println(edtDateEnd);
		return customerFollowDao.queryReport(edtDateBegin, edtDateEnd);
	}

	@Override
	public void addDefaultSignCustomerFollow(Integer customerId, Integer employeeId) throws Exception {
		CustomerFollow customerFollow = new CustomerFollow();
		customerFollow.setCustomer(new Customer(customerId)); // 客户
		customerFollow.setEmployee(new Employee(employeeId)); // 操作人
		customerFollow.setRemindTime(null); // 提醒时间为空
		customerFollow.setType(CustomerFollow.SIGN);
		// 默认的提醒内容
		customerFollow.setRemindContent("成功签约");

		// 添加跟进消息
		customerFollowDao.save(customerFollow);
	}

	@Override
	public List<Map<String, Object>> getReport(List<Integer> eIds, String edtDateBegin, String edtDateEnd)
			throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (edtDateBegin == null) {
			edtDateBegin = format.format(new Date());
			edtDateEnd = format.format(new Date());
		}
		return customerFollowDao.queryReport(eIds, edtDateBegin, edtDateEnd);
	}

	@Override
	public List<Map<String, Object>> getDepartmentReport(List<Integer> dIds, String edtDateBegin, String edtDateEnd)
			throws Exception {
		return customerFollowDao.getDepartmentReport(dIds, edtDateBegin, edtDateEnd);
	}

	@Override
	public List<Map<String, Object>> getRemindTotalCountOfDept(String todayDate) throws Exception {
		return customerFollowDao.getRemindTotalCount("getDailyBasicData", todayDate, null, null);
	}

	@Override
	public List<Map<String, Object>> getRemindTotalCountOfEmp(List<Integer> employeeIds, String todayDate)
			throws Exception {
		return customerFollowDao.getRemindTotalCount("getDailyBasicData", todayDate, employeeIds, 0);
	}

	@Override
	public List<Map<String, Object>> getVisitOrSignRemindTotalCountOfDept(Integer type, String todayDate)
			throws Exception {
		return customerFollowDao.getVisitOrSignRemindTotalCount(type, "getVisitOrSignRemindTotalCountOfDept", todayDate,
				null);
	}

	@Override
	public List<Map<String, Object>> getVisitOrSignRemindTotalCountOfEmp(Integer type, List<Integer> employeeIds,
			String todayDate) throws Exception {
		return customerFollowDao.getVisitOrSignRemindTotalCount(type, "getVisitOrSignRemindTotalCountOfEmp", todayDate,
				employeeIds);
	}

	@Override
	public List<Map<String, Object>> getReportList(Integer reportType, Integer id, String eOrd, String dateStr,
			Integer pageIndex, Integer pageSize, String whereKey) throws Exception {
		return customerFollowDao.getReportList(reportType, id, eOrd, dateStr, pageIndex, pageSize, whereKey);
	}

	@Override
	public Integer getReportListCount(Integer reportType, Integer id, String eOrd, String dateStr, String whereKey)
			throws Exception {
		return customerFollowDao.getReportListCount(reportType, id, eOrd, dateStr, whereKey);
	}

	@Override
	public List<Map<String, Object>> getChargeDataForDemp(String totalOrMonth, String sql) throws Exception {
		return customerFollowDao.getChargeDataForDemp(totalOrMonth, sql);
	}

	@Override
	public List<Map<String, Object>> getChargeDataForEmp(String totalOrMonth, String sql, List<Integer> ids)
			throws Exception {
		return customerFollowDao.getChargeDataForDemp(totalOrMonth, sql, ids);
	}

	@Override
	public List<Map<String, Object>> getMoneyDataForDemp(String totalOrMonth, String sqlName) throws Exception {
		return customerFollowDao.getMoneyDataForDemp(totalOrMonth, sqlName);
	}

	@Override
	public List<Map<String, Object>> getMoneyDataForEmp(String totalOrMonth, String sqlName, List<Integer> eIds)
			throws Exception {
		return customerFollowDao.getMoneyDataForEmp(totalOrMonth, sqlName, eIds);
	}

	@Override
	public Map<String, Object> getTodayRemindCountOfEmp(Integer employeeId) throws Exception {
		return customerFollowDao.getTodayRemindCountOfEmp(employeeId, null);
	}

	@Override
	public Map<String, Object> getTodayRepaymentCountOfEmp(Integer employeeId) throws Exception {
		return customerFollowDao.getTodayRemindCountOfEmp(employeeId, 1);
	}

	@Override
	public Boolean isCanAddVisibility(Integer employeeId) throws Exception {
		Integer visibilityCount = customerFollowDao.getVisibilityCount(employeeId);
		System.out.println("visibilityCount:" + visibilityCount);
		if (visibilityCount >= Constant.VISIBILITY_COUNT) {
			return false;
		}
		return true;
	}
}
