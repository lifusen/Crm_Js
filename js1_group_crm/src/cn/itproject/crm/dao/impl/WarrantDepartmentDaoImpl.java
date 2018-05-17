package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.dao.WarrantDepartmentDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;

@Repository
public class WarrantDepartmentDaoImpl extends BaseDaoSupport<Customer> implements WarrantDepartmentDao{

	@Override
	public List<Map<String, Object>> orderList(WarrantOrderQuery queryvo) throws Exception {
		//获取到SQL命名查询
				Query sqlQuery = getSession().getNamedQuery("warrantDepartmentOrderList");
				//获取到SQL字符串
				String sql = sqlQuery.getQueryString();
				
				if (queryvo.getCompanyId()!=null && queryvo.getCompanyId()>0) { // 权证中心管理员 2017.1.10
				//if (Constant.warrantAdmin.equals(queryvo.getRoleId())) { //权证中心管理员
					sql = sql + " and s.warrantCompanyId = "+queryvo.getCompanyId();
				}
				//}else if (Constant.warrantManager.equals(queryvo.getRoleId())) { 	//权证部经理
				if (queryvo.getDempId()!=null && queryvo.getDempId()>0) { 	//权证部经理2017.1.10
					sql = sql + " and s.warrantDepartmentId = "+queryvo.getDempId();
				}
				
				//条件查询，电话和姓名
				if (queryvo.getQueryWord()!=null && !queryvo.getQueryWord().equals("")) {
					sql = sql + " and (c.phone like :phone or c.name like :name)";
				}
				
				//创建查询对象
				Query query =getSession().createSQLQuery(sql);
				query.setFirstResult((queryvo.getPageIndex()-1)*queryvo.getPageSize());
				query.setMaxResults(queryvo.getPageSize());
				
				if (queryvo.getQueryWord()!=null && !queryvo.getQueryWord().equals("")) {
					query.setString("phone", "%"+queryvo.getQueryWord()+"%");
					query.setString("name", "%"+queryvo.getQueryWord()+"%");
				}
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				List<Map<String, Object>> list =query.list();
				return list;
	}

	@Override
	public Integer getOrderCount(WarrantOrderQuery queryvo) throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("warrantDepartmentOrderCount");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		//条件查询：电话、姓名
		if (queryvo.getQueryWord()!=null && !queryvo.getQueryWord().equals("")) {
			sql = sql + " and (c.phone like :phone or c.name like :name)";
		}
		if (Constant.warrantAdmin.equals(queryvo.getRoleId())) { //权证中心管理员
			sql = sql + " and s.warrantCompanyId = "+queryvo.getCompanyId();
		}else if (Constant.warrantManager.equals(queryvo.getRoleId())) { 	//权证部经理
			sql = sql + " and s.warrantDepartmentId = "+queryvo.getDempId();
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (queryvo.getQueryWord()!=null && !queryvo.getQueryWord().equals("")) {
			query.setString("phone", "%"+queryvo.getQueryWord()+"%");
			query.setString("name", "%"+queryvo.getQueryWord()+"%");
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}
}