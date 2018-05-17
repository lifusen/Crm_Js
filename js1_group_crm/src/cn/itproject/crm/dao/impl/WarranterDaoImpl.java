package cn.itproject.crm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.dao.WarranterDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository
public class WarranterDaoImpl extends BaseDaoSupport<Customer> implements WarranterDao{

	@Override
	public List<Map<String, Object>> orderList(WarrantOrderQuery queryvo) throws Exception{
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("warranterOrderList");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		//条件查询：电话、姓名
		if (queryvo.getQueryWord()!=null && !queryvo.getQueryWord().equals("")) {
			sql = sql + " and (c.phone like :phone or c.name like :name)";
		}
		//loginUser为Constant.SUPER_ROLE时，queryvo.getRoleId为null
		if(Constant.warranter.equals(queryvo.getRoleId())){// 权证专员
			sql = sql + " and o.warranterId="+queryvo.getEmpId();
		}else if (Constant.warrantManager.equals(queryvo.getRoleId())) { // 权证部经理
			sql = sql + " and o.departmentId = "+queryvo.getDempId();
		}else if (Constant.warrantAdmin.equals(queryvo.getRoleId())) { 	// 权证中心管理员
			sql = sql + " and o.companyId = "+queryvo.getCompanyId();
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
		Query sqlQuery = getSession().getNamedQuery("warranterOrderCount");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		//条件查询：电话、姓名
		if (queryvo.getQueryWord()!=null && !queryvo.getQueryWord().equals("")) {
			sql = sql + " and (c.phone like :phone or c.name like :name)";
		}
		if(Constant.warranter.equals(queryvo.getRoleId())){// 权证专员
			sql = sql + " and o.warranterId = "+queryvo.getEmpId();
		}else if (Constant.warrantManager.equals(queryvo.getRoleId())) { // 权证部经理
			sql = sql + " and o.departmentId = "+queryvo.getDempId();
		}else if (Constant.warrantAdmin.equals(queryvo.getRoleId())) { 	// 权证中心管理员
			sql = sql + " and o.companyId = "+queryvo.getCompanyId();
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

	@Override
	public List<Map<String, Object>> search(Integer[] companyIdArray, Integer[] depIdArray, Integer[] empIdArray,
			Integer[] typeArray, String[] loanTypeArray, Date signDateStart, Date signDateEnd,Integer pageIndex,Integer pageSize) throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("warranterOrderList");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if (companyIdArray!=null && companyIdArray.length>0) {
			sql = sql + " and s.warrantCompanyId in (:companyId)";
		}
		
		if (depIdArray!=null && depIdArray.length>0) {
			sql = sql + " and s.warrantDepartmentId in (:depId)";
		}
		
		if (empIdArray!=null && empIdArray.length>0) {
			sql = sql + " and s.warrantEmployeeId in (:warrantEmployeeId)";
		}
		if (typeArray!=null && typeArray.length>0) {
			sql = sql + " and s.orderType in (:orderType)";
		}
		if (loanTypeArray!=null && loanTypeArray.length>0) {
			sql = sql + " and s.loanType in (:loanType)";
		}
		
		if (signDateStart!=null && signDateEnd!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			sql = sql + " and s.signTime>='"+format.format(signDateStart)+"' and s.signTime<='"+format.format(signDateEnd)+"'";
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		
		if (companyIdArray!=null && companyIdArray.length>0) {
			query.setParameterList("companyId", companyIdArray);
		}
		
		if (depIdArray!=null && depIdArray.length>0) {
			query.setParameterList("depId", depIdArray);
		}
		
		if (empIdArray!=null && empIdArray.length>0) {
			query.setParameterList("warrantEmployeeId", empIdArray);
		}
		if (typeArray!=null && typeArray.length>0) {
			query.setParameterList("orderType", typeArray);
		}
		if (loanTypeArray!=null && loanTypeArray.length>0) {
			query.setParameterList("loanType", loanTypeArray);
		}
		
		
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer searchCount(Integer[] companyIdArray, Integer[] depIdArray, Integer[] empIdArray,
			Integer[] typeArray, String[] loanTypeArray, Date signDateStart, Date signDateEnd) throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("warranterOrderCount");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if (companyIdArray!=null && companyIdArray.length>0) {
			sql = sql + " and s.warrantCompanyId in (:companyId)";
		}
		
		if (depIdArray!=null && depIdArray.length>0) {
			sql = sql + " and s.warrantDepartmentId in (:depId)";
		}
		
		if (empIdArray!=null && empIdArray.length>0) {
			sql = sql + " and s.warrantEmployeeId in (:warrantEmployeeId)";
		}
		if (typeArray!=null && typeArray.length>0) {
			sql = sql + " and s.orderType in (:orderType)";
		}
		if (loanTypeArray!=null && loanTypeArray.length>0) {
			sql = sql + " and s.loanType in (:loanType)";
		}
		
		if (signDateStart!=null && signDateEnd!=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			sql = sql + " and s.signTime>='"+format.format(signDateStart)+"' and s.signTime<='"+format.format(signDateEnd)+"'";
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (companyIdArray!=null && companyIdArray.length>0) {
			query.setParameterList("companyId", companyIdArray);
		}
		
		if (depIdArray!=null && depIdArray.length>0) {
			query.setParameterList("depId", depIdArray);
		}
		
		if (empIdArray!=null && empIdArray.length>0) {
			query.setParameterList("warrantEmployeeId", empIdArray);
		}
		if (typeArray!=null && typeArray.length>0) {
			query.setParameterList("orderType", typeArray);
		}
		if (loanTypeArray!=null && loanTypeArray.length>0) {
			query.setParameterList("loanType", loanTypeArray);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}
}