package cn.itproject.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Plan;
import cn.itproject.crm.bean.Workday;
import cn.itproject.crm.controller.viewbean.PlanData;
import cn.itproject.crm.dao.PlanDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.PlanService;
import cn.itproject.crm.service.WorkdayService;
import cn.itproject.crm.util.DoubleUtil;

@Service
public class PlanServiceImpl extends BaseServiceSupport<Plan> implements PlanService{
	
	@Resource
	private PlanDao planDao; 
	
	@Resource
	private WorkdayService workdayService;
	
	@Override
	protected BaseDao<Plan> getBaseDao() {
		return planDao;
	}

	@Override
	public Plan getPlan(Integer employeeId) throws Exception {
		return planDao.getPlan(employeeId);
	}

	@Override
	public List<PlanData> getDeptTotalPlan() throws Exception {
		return planDao.getDeptPlan(null);
	}

	@Override
	public List<PlanData> getDeptMonthPlan() throws Exception {
		return planDao.getDeptPlan(0);
	}

	@Override
	public List<PlanData> getEmpTotalPlan(Integer departmentId)
			throws Exception {
		return planDao.getEmpPlan(departmentId, null,null);
	}

	@Override
	public List<PlanData> getEmpMonthPlan(Integer departmentId)
			throws Exception {
		return planDao.getEmpPlan(departmentId, 0,null);
	}

	@Override
	public List<PlanData> getDeptDayPlan() throws Exception {
		//获取到计划数据
		List<PlanData> planDatas = planDao.getDeptPlan(0);
		return getDayPlan(planDatas);
	}

	@Override
	public List<PlanData> getEmpDayPlan(Integer departmentId) throws Exception {
		List<PlanData> planDatas = planDao.getEmpPlan(departmentId, 0,null);
		return getDayPlan(planDatas);
	}
	
	private List<PlanData> getDayPlan(List<PlanData> planDatas) throws Exception{
		//获取到工作日对象
		Workday workday = workdayService.getCurrentMonthWorkDay();
		Integer day = 0;
		if (workday!=null) {
			day = workday.getDay();
		}
		for (PlanData planData : planDatas) {
			Double visit = planData.getVisit();
			Double sign = planData.getSign();
			if (day==0) {
				planData.setVisit(0d);
				planData.setSign(0d);
			}else {
				planData.setVisit(DoubleUtil.div(visit, day,2));
				planData.setSign(DoubleUtil.div(sign, day,2));
			}
		}
		return planDatas;
	}

	@Override
	public List<PlanData> getEmpMonthPlan(Integer departmentId,
			Integer employeeId) throws Exception {
		return planDao.getEmpPlan(departmentId, 0,employeeId);
	}

}
