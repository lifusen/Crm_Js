package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.Plan;
import cn.itproject.crm.controller.viewbean.PlanData;
import cn.itproject.crm.db.BaseDao;

public interface PlanDao extends BaseDao<Plan>{

	Plan getPlan(Integer employeeId) throws Exception;

	/**
	 * 获取所有部门的总/月计划
	 * @param type null,0:当月
	 * @return
	 * @throws Exception
	 */
	List<PlanData> getDeptPlan(Integer type) throws Exception;
	
	/**
	 * 获取指定部门下所有员工的总/月计划
	 * @param departmentId 部门ID
	 * @param type null,0:当月
	 * @return
	 * @throws Exception
	 */
	List<PlanData> getEmpPlan(Integer departmentId,Integer type,Integer employeeId) throws Exception;
}
