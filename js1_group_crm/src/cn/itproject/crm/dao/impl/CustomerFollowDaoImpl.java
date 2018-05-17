package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.CustomerFollow;
import cn.itproject.crm.dao.CustomerFollowDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.db.utils.OrderByType;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

/**
 * 客户跟进DAO实现类
 * @author MrLiu
 *
 */
@Repository
public class CustomerFollowDaoImpl extends BaseDaoSupport<CustomerFollow> implements CustomerFollowDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getReminderList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds, String keyword,
			String orderColumn, OrderByType orderByType, Integer type) throws Exception {
		
		System.out.println("employeeIds:"+employeeIds);
		String sqlName = "getCustomerReminderListByEmployeeIds";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		//拼接排序
//		sql+=" order by c.name";
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("employeeIds", employeeIds);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getReminderListCount(List<Integer> employeeIds,
			String keyword, Integer type) throws Exception {
		System.out.println("employeeIds:"+employeeIds);
		String sqlName = "getCustomerReminderListCountByEmployeeIds";
		//获取到SQL命名查询
		Query query = getSession().getNamedQuery(sqlName);
		query.setParameterList("employeeIds", employeeIds);
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryReport(String edtDateBegin,
			String edtDateEnd) throws Exception {
		String sqlName = "queryReport";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameter("edtDateBegin", edtDateBegin);
		query.setParameter("edtDateEnd", edtDateEnd);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		System.out.println(list.size());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryReport(List<Integer> eIds,
			String edtDateBegin, String edtDateEnd) throws Exception {
		String sqlName = "queryYwbEmpReport";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameter("startDate", edtDateBegin);
		query.setParameter("endDate", edtDateEnd);
		query.setParameterList("ids", eIds);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		System.out.println(list.size());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getRemindTotalCount(String sqlName,String date,List<Integer> employeeIds,Integer type)
			throws Exception {
		//获取到SQL命名查询
 		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if (type==null) {//部门
			sql = sql.replace("#{rid}", "d.id");
			sql = sql.replace("#{idWhere}", "d.id in(select id from department where type = '业务' and companyId = :companyId)");
		}else {
			sql = sql.replace("#{rid}", "e.id");
			sql = sql.replace("#{idWhere}", "e.id in(:employeeIds)");
		}
		//处理日期
		if (date==null) {
			sql = sql.replace("#{today}", "");
		}else {
			sql = sql.replace("#{today}", "and DATEDIFF(cf.remindTime,NOW()) = 0");
		}
		
		System.out.println(sql);
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("states", Constant.reminderList);
		//处理员工ID
		if (type!=null) {
			query.setParameterList("employeeIds", employeeIds);
			System.out.println("employeeIds:"+employeeIds);
		}else{
			Integer companyId = LoginUserUtil.getCompanyId();
			query.setInteger("companyId", companyId);
			System.out.println("companyId:"+companyId);
		}
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getDepartmentReport(List<Integer> dIds,
			String edtDateBegin, String edtDateEnd) throws Exception {
		String sqlName = "queryYwbDeptReport";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameter("startDate", edtDateBegin);
		query.setParameter("endDate", edtDateEnd);
		query.setParameterList("ids", dIds);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		System.out.println(list.size());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getVisitOrSignRemindTotalCount(
			Integer type, String sqlName, String date, List<Integer> employeeIds)
			throws Exception {
		//获取到SQL命名查询
 		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		//处理日期
		if (date==null) {//获取当月
			sql = sql.replace("#{edtDate}", "EXTRACT(YEAR_MONTH FROM cf.edtDate) = EXTRACT(YEAR_MONTH FROM now())");
		}else {			//获取当天
			sql = sql.replace("#{edtDate}", "cf.edtDate = '"+date+"'");
		}
		Integer companyId = LoginUserUtil.getCompanyId();
		System.out.println("companyId:"+companyId);
		sql = sql.replace("#{companyId}", companyId+"");
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		//设置类型
		query.setParameter("type", type);
		//处理员工ID
		if (employeeIds!=null) {
			query.setParameterList("employeeIds", employeeIds);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getReportList(Integer reportType,
			Integer id, String eOrd, String date, Integer pageIndex, Integer pageSize,String whereKey)
			throws Exception {
		//获取到SQL命名查询
 		Query sqlQuery = getSession().getNamedQuery("queryReportCustomerList");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if ("E".equals(eOrd)) {
			sql = sql.replace("${where}", " and cf.employeeId = :id");
		}else {
			sql = sql.replace("${where}", " and d.id = :id");
		}
		if (!"".equals(whereKey)) {
			sql = sql+" and (c.name like :name or c.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)";
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		//设置类型
		query.setInteger("id", id);
		query.setInteger("type", reportType);
		query.setParameter("date", date);
		if (!"".equals(whereKey)) {
			query.setString("loanType", "%"+whereKey+"%");
			query.setString("phone", "%"+whereKey+"%");
			query.setString("customerLevel", "%"+whereKey+"%");
			query.setString("name", "%"+whereKey+"%");
		}
		
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		//处理员工ID
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getReportListCount(Integer reportType, Integer id,
			String eOrd, String date,String whereKey) throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("queryReportCustomerCount");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if ("E".equals(eOrd)) {
			sql = sql.replace("${where}", " and cf.employeeId = :id");
		}else {
			sql = sql.replace("${where}", " and d.id = :id");
		}
		
		if (!"".equals(whereKey)) {
			sql = sql+" and (c.name like :name or c.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)";
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		//设置类型
		query.setParameter("id", id);
		query.setParameter("type", reportType);
		query.setParameter("date", date);
		
		if (!"".equals(whereKey)) {
			query.setString("loanType", "%"+whereKey+"%");
			query.setString("phone", "%"+whereKey+"%");
			query.setString("customerLevel", "%"+whereKey+"%");
			query.setString("name", "%"+whereKey+"%");
		}
		
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public List<Map<String, Object>> getChargeDataForDemp(String totalOrMonth,
			String sqlName) throws Exception {
		//获取到SQL命名查询
 		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sqlquery = sqlQuery.getQueryString();
		
		if ("total".equals(totalOrMonth)) {
			sqlquery = sqlquery.replace("${totalOrMonth}", " ");
		}else {
			sqlquery = sqlquery.replace("${totalOrMonth}", "and EXTRACT(YEAR_MONTH FROM s.signDate) = EXTRACT(YEAR_MONTH FROM now())");
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sqlquery);
		Integer companyId = LoginUserUtil.getCompanyId();
		System.out.println("totalOrMonth:"+totalOrMonth);
		System.out.println("companyId:"+companyId);
		query.setInteger("companyId", companyId);		// 设置公司ID
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getChargeDataForDemp(String totalOrMonth,
			String sqlName, List<Integer> ids) throws Exception {
		//获取到SQL命名查询
 		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sqlquery = sqlQuery.getQueryString();
		
		if ("total".equals(totalOrMonth)) {
			sqlquery = sqlquery.replace("${totalOrMonth}", " ");
		}else {
			sqlquery = sqlquery.replace("${totalOrMonth}", "and EXTRACT(YEAR_MONTH FROM s.signDate) = EXTRACT(YEAR_MONTH FROM now())");
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sqlquery);
		query.setParameterList("ids", ids);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getMoneyDataForDemp(String totalOrMonth,
			String sqlName) throws Exception {
		//获取到SQL命名查询
 		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sqlquery = sqlQuery.getQueryString();
		
		if ("total".equals(totalOrMonth)) {
			sqlquery = sqlquery.replace("${totalOrMonth}", " ");
		}else {
			sqlquery = sqlquery.replace("${totalOrMonth}", " and EXTRACT(YEAR_MONTH FROM o.createTime) = EXTRACT(YEAR_MONTH FROM now()) ");
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sqlquery);
		Integer companyId = LoginUserUtil.getCompanyId();
		query.setInteger("companyId", companyId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		System.out.println("totalOrMonth:"+totalOrMonth);
		System.out.println("companyId:"+companyId);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public List<Map<String, Object>> getMoneyDataForEmp(String totalOrMonth,
			String sqlName, List<Integer> eIds) throws Exception {
		//获取到SQL命名查询
 		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sqlquery = sqlQuery.getQueryString();
		
		if ("total".equals(totalOrMonth)) {
			sqlquery = sqlquery.replace("${totalOrMonth}", " ");
		}else {
			sqlquery = sqlquery.replace("${totalOrMonth}", "and EXTRACT(YEAR_MONTH FROM o.createTime) = EXTRACT(YEAR_MONTH FROM now())");
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sqlquery);
		query.setParameterList("ids", eIds);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTodayRemindCountOfEmp(
			Integer employeeId, Integer type) throws Exception {
		String sql = getSession().getNamedQuery("getTodayRemindCountOfEmp").getQueryString();
		if (type==null) {
			sql = sql.replace("#{symbol}", "<>");
		}else {
			sql = sql.replace("#{symbol}", "=");
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("states", Constant.reminderList);
		query.setInteger("employeeId", employeeId);
//		query.setDate("remindTime", new Date());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String, Object> map =(Map<String, Object>) query.uniqueResult();
		return map;
	}

	@Override
	public Integer getVisibilityCount(Integer employeeId) throws Exception {
		String hql = "select count(*) from Customer where employee.id = :employeeId and state in :states and visibility = 0";
		Long count = (Long) getSession().createQuery(hql)
			.setInteger("employeeId", employeeId)
			.setParameterList("states", Constant.validList)
			.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}
}
