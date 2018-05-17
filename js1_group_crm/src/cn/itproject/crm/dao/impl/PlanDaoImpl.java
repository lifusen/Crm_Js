package cn.itproject.crm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Plan;
import cn.itproject.crm.controller.viewbean.PlanData;
import cn.itproject.crm.dao.PlanDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class PlanDaoImpl extends BaseDaoSupport<Plan> implements PlanDao{

	@Override
	public Plan getPlan(Integer employeeId) throws Exception {
		String hql = "from Plan p where EXTRACT(YEAR_MONTH FROM p.createDate) = EXTRACT(YEAR_MONTH FROM now()) and p.employee.id = :employeeId";
		return (Plan) getSession().createQuery(hql)
			.setInteger("employeeId", employeeId)
			.uniqueResult();
	}

	@Override
	public List<PlanData> getDeptPlan(Integer type) throws Exception {
		return getPlanData("getPlanOfDept", null, type,null);
	}

	@Override
	public List<PlanData> getEmpPlan(Integer departmentId, Integer type,Integer employeeId)
			throws Exception {
		return getPlanData("getPlanOfEmp", departmentId, type,employeeId);
	}
	@SuppressWarnings("unchecked")
	private List<PlanData> getPlanData(String sqlName,Integer departmentId, Integer type,Integer employeeId)
			throws Exception {
		String sql = getSession().getNamedQuery(sqlName).getQueryString();
		if (type!=null) {
			sql = sql.replace("#{currentMonth}", " and EXTRACT(YEAR_MONTH FROM p.createDate) = EXTRACT(YEAR_MONTH FROM now())");
		}else {
			sql = sql.replace("#{currentMonth}", "");
		}
		
		if (employeeId!=null) {
			sql = sql.replace("#{employee}", " and employeeId = :employeeId");
		}else {
			sql = sql.replace("#{employee}", "");
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (departmentId!=null) {
			query.setInteger("departmentId", departmentId);
		}
		if (employeeId!=null) {
			query.setInteger("employeeId", employeeId);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> listMap =query.list();		
		List<PlanData> list = new ArrayList<PlanData>();
		for (Map<String, Object> map : listMap) {
			PlanData planData = new PlanData();
			Object id = map.get("id");
			planData.setId(id==null?0:Integer.parseInt(String.valueOf(id)));
			Object visit = map.get("visit");
			planData.setVisit(visit==null?0:Double.parseDouble(String.valueOf(visit)));
			Object sign = map.get("sign");
			planData.setSign(sign==null?0:Double.parseDouble(String.valueOf(sign)));
			Object performance = map.get("performance");
			planData.setPerformance(performance==null?0d:Double.parseDouble(String.valueOf(performance)));
			list.add(planData);
		}
		return list;
	}

	

}
