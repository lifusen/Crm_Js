package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.SignCustomerTurn;
import cn.itproject.crm.dao.WarranterTurnDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository("warranterTurnDao")
public class WarranterTurnDaoImpl extends BaseDaoSupport<SignCustomerTurn> implements WarranterTurnDao {

	@Override
	public List<Map<String, Object>> getSignCustomersByEId(Integer pageIndex, Integer pageSize, Integer[] ids,
			String keyStr) throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("warranterOrderList");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString() + " and o.outLoanState=0";
		
		//条件查询：电话、姓名
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql + " and (c.phone like :phone or c.name like :name)";
		}
		//loginUser为Constant.SUPER_ROLE时，queryvo.getRoleId为null
		if(Constant.warranter.equals(LoginUserUtil.getLoginRoleId())){// 权证专员
			sql = sql + " and o.warranterId="+LoginUserUtil.getEmployeeId();
		}else if (Constant.warrantManager.equals(LoginUserUtil.getLoginRoleId())) { // 权证部经理
			sql = sql + " and o.departmentId = "+LoginUserUtil.getLoginDepartmentId();
		}else if (Constant.warrantAdmin.equals(LoginUserUtil.getLoginRoleId())) { 	// 权证中心管理员
			sql = sql + " and o.companyId = "+LoginUserUtil.getCompanyId();
		}
		
		if(ids!=null && ids.length>0){
			sql = sql + " and o.warranterId in (:imployeeIds)";System.out.println("============="+ids[0]+"===========================");
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("phone", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		
		if(ids!=null && ids.length>0){
			query.setParameterList("imployeeIds", ids);
		}
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getCountByEId(Integer[] ids, String keyStr) throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("warranterOrderCount");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString() + " and outLoanState=0";
		//条件查询：电话、姓名
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql + " and (c.phone like :phone or c.name like :name)";
		}
		if(Constant.warranter.equals(LoginUserUtil.getLoginRoleId())){// 权证专员
			sql = sql + " and o.warranterId = "+LoginUserUtil.getEmployeeId();
		}else if (Constant.warrantManager.equals(LoginUserUtil.getLoginRoleId())) { // 权证部经理
			sql = sql + " and o.departmentId = "+LoginUserUtil.getLoginDepartmentId();
		}else if (Constant.warrantAdmin.equals(LoginUserUtil.getLoginRoleId())) { 	// 权证中心管理员
			sql = sql + " and o.companyId = "+LoginUserUtil.getCompanyId();
		}
		
		if(ids!=null && ids.length>0){
			sql = sql + " and o.warranterId in (:imployeeIds)";
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("phone", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		
		if(ids!=null && ids.length>0){
			query.setParameterList("imployeeIds", ids);
		}
		
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public List<Integer> getIdsByEId(Integer[] ids, String keyStr) throws Exception {
		//创建SQL字符串
		String sql = "SELECT o.id FROM signcustomer s,customer c,outloanbelong o"
				+ " WHERE s.customerId = c.id AND c.state in (4,5,6)"
				+ " AND s.id = o.signCustomerId and o.outLoanState=0";
		
		//条件查询：电话、姓名
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql + " and (c.phone like :phone or c.name like :name)";
		}
		//loginUser为Constant.SUPER_ROLE时，queryvo.getRoleId为null
		if(Constant.warranter.equals(LoginUserUtil.getLoginRoleId())){// 权证专员
			sql = sql + " and o.warranterId="+LoginUserUtil.getEmployeeId();
		}else if (Constant.warrantManager.equals(LoginUserUtil.getLoginRoleId())) { // 权证部经理
			sql = sql + " and o.departmentId = "+LoginUserUtil.getLoginDepartmentId();
		}else if (Constant.warrantAdmin.equals(LoginUserUtil.getLoginRoleId())) { 	// 权证中心管理员
			sql = sql + " and o.companyId = "+LoginUserUtil.getCompanyId();
		}
		
		if(ids!=null && ids.length>0){
			sql = sql + " and o.warranterId in (:imployeeIds)";
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("phone", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		
		if(ids!=null && ids.length>0){
			query.setParameterList("imployeeIds", ids);
		}
		
		List<Integer> list =query.list();
		return list;
	}

}
