package cn.itproject.crm.service;

import java.util.List;

import cn.itproject.crm.bean.Plan;
import cn.itproject.crm.controller.viewbean.PlanData;

public interface PlanService extends BaseService<Plan>{
	
	/**
	 * 获取计划
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public Plan getPlan(Integer employeeId) throws Exception;

	/**
	 * 获取所有部门的总计划
	 * @return
	 * @throws Exception
	 */
	public List<PlanData> getDeptTotalPlan() throws Exception;

	/**
	 * 获取所有部门的当日计划
	 * @return
	 * @throws Exception
	 */
	public List<PlanData> getDeptMonthPlan() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PlanData> getDeptDayPlan() throws Exception;
	
	/**
	 * 获取指定部门业务员的总计划
	 * @return
	 * @throws Exception
	 */
	public List<PlanData> getEmpTotalPlan(Integer departmentId) throws Exception;
	
	/**
	 * 获取指定部门业务员的月计划
	 * @return
	 * @throws Exception
	 */
	public List<PlanData> getEmpMonthPlan(Integer departmentId) throws Exception;
	
	
	/**
	 * 获取指定业务员的月计划
	 * @return
	 * @throws Exception
	 */
	public List<PlanData> getEmpMonthPlan(Integer departmentId,Integer employeeId) throws Exception;
	
	/**
	 * 获取指定部门业务员的当日计划
	 * @return
	 * @throws Exception
	 */
	public List<PlanData> getEmpDayPlan(Integer departmentId) throws Exception;

	
	
	
}
