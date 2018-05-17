package cn.itproject.crm.dao.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.dao.NewOrderDao;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository("newOrderDao")
public class NewOrderDaoImpl implements NewOrderDao{
	@Resource
	private SessionFactory sessionFactory;
	protected Session getSession() throws Exception{
		return sessionFactory.getCurrentSession();
	}
	@Override
	public List<Map<String, Object>> getNewOrderList(Integer pageIndex,
			Integer pageSize, List<Integer> neworders, Integer[] ids,
			String keyStr) throws Exception {
		String sqlName = "getNewOrderList";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if(ids!=null){
			sql = sql.replace("#{where}", "  and c.employeeId in (:employeeId)");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql+" and (c.name like :name or "
					+ "c.loanType like :loanType or c.phone like :phone or "
					+ "c.customerLevel like :customerLevel)";
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setParameterList("states", neworders);
		
		if(ids!=null){
			query.setParameterList("employeeId",ids);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}
	@Override
	public Integer getNewOrderCount(List<Integer> neworders, Integer[] ids,
			String keyStr) throws Exception {
		String sqlName = "getNewOrderCount";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if(ids!=null){
			sql = sql.replace("#{where}", "  and employeeId in (:employeeId)");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and companyId="+LoginUserUtil.getCompanyId();
		}
		
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql+" and (name like :name or "
					+ "loanType like :loanType or phone like :phone or "
					+ "customerLevel like :customerLevel)";
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("states", neworders);
		
		if(ids!=null){
			query.setParameterList("employeeId",ids);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}
}
