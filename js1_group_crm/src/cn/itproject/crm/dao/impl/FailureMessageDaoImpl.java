package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.FailureMessage;
import cn.itproject.crm.dao.FailureMessageDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.db.utils.OrderByType;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository
public class FailureMessageDaoImpl extends BaseDaoSupport<FailureMessage> implements FailureMessageDao{

	@Override
	public List<Map<String, Object>> getRejectCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds, String keyword,
			String orderColumn, OrderByType orderByType) throws Exception {
		System.out.println("employeeIds:"+employeeIds);
		String sqlName = "getRejectCustomerListByEmployeeIds";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if(employeeIds!=null){
			sql = sql.replace("#{nowEmployee}", " and h.nowEmployeeId in (:employeeIds)");
		}else {
			sql = sql.replace("#{nowEmployee}", " ");
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (employeeIds!=null) {
			query.setParameterList("employeeIds", employeeIds);
		}
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getRejectCustomerListCount(List<Integer> employeeIds,
			String keyword) throws Exception {
		System.out.println("employeeIds:"+employeeIds);
		String sqlName = "getRejectCustomerListCountByEmployees";
		
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		if(employeeIds!=null){
			sql = sql.replace("#{nowEmployee}", " and h.nowEmployeeId in (:employeeIds)");
		}else {
			sql = sql.replace("#{nowEmployee}", " ");
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql.replace("#{companyId}", "  and c.companyId="+LoginUserUtil.getCompanyId());
		}else {
			sql = sql.replace("#{companyId}", " ");
		}
		
		//获取到SQL命名查询
		Query query = getSession().getNamedQuery(sqlName);
		if (employeeIds!=null) {
			query.setParameterList("employeeIds", employeeIds);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}
	
}