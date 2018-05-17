package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.AllotCustomer;
import cn.itproject.crm.dao.AllotCustomerDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;
@Repository("allotCustomerDao")
public class AllotCustomerDaoImpl extends BaseDaoSupport<AllotCustomer> implements AllotCustomerDao{

	@Override
	public void updateStaff(Integer cId, Integer eId) throws Exception{
		String hql = "update AllotCustomer set staff.id=:eId where customer.id = :cId";
		Query query = getSession().createQuery(hql);
		query.setInteger("eId", eId);
		query.setInteger("cId", cId);
		query.executeUpdate();
	}

	@Override
	public List<AllotCustomer> getListByCId(Integer[] ids) throws Exception{
		String hql = "from AllotCustomer where customer.id in (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return query.list();
	}

	@Override
	public void batchUpdateStaff(Integer[] idArrayInt, Integer eId) throws Exception{
		String hql = "update AllotCustomer set staff.id=:eId where customer.id in(:cId)";
		Query query = getSession().createQuery(hql);
		query.setInteger("eId", eId);
		query.setParameterList("cId", idArrayInt);
		query.executeUpdate();
	}

	@Override
	public List<Map<String, Object>> getAllotToDepartList(Integer pageIndex,
			Integer pageSize, List<Integer> allottodepartments, Integer id,String keyStr) throws Exception {
		String sqlName = "getAllotList";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		//sql = sql + " and addType != 2";
		
		if(id!=null){
			sql = sql.replace("#{where}", "  and addPersonId = :addPersonId");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		
		if (StringUtils.isNotBlank(keyStr)) {
			sql = sql+" and (c.name like '%"+keyStr+"%' or c.loanType like '%"+keyStr+"%' or c.phone like '%"+keyStr+"%')";
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setParameterList("states", allottodepartments);
		
		if(id!=null){
			query.setInteger("addPersonId",id);
		}
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getAllotToDepartCount(List<Integer> allottodepartments, Integer id,String keyStr)
			throws Exception {
		String sqlName = "getAllotCount";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		//sql = sql + " and addType != 2";
		String appendSql = "";
		if(id!=null){
			appendSql = appendSql+"  and addPersonId = :addPersonId";
		}
		
		if (StringUtils.isNotBlank(keyStr)) {
			appendSql = appendSql+" and (c.name like '%"+keyStr+"%' or c.loanType like '%"+keyStr+"%' or c.phone like '%"+keyStr+"%')";
		}
		sql = sql.replace("#{where}", appendSql);
		
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql.replace("#{companyId}", "  and c.companyId="+LoginUserUtil.getCompanyId());
		}else {
			sql = sql.replace("#{companyId}", " ");
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("states", allottodepartments);
		
		if(id!=null){
			query.setInteger("addPersonId",id);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public List<Map<String, Object>> getAllotToEmplList(Integer pageIndex,
			Integer pageSize, List<Integer> allottoemployees, Integer dId,String keyStr,Integer companyId,Integer departmentId)
			throws Exception {
		String sqlName = "getAllotList";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		if (LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			if (companyId!=null && companyId>0) {
				sql = sql+" and c.companyId = "+companyId;
			}
			if (departmentId!=null && departmentId>0) {
				sql = sql+" and c.departmentId = "+departmentId;
			}
		}
		
		if(dId!=null){
			sql = sql.replace("#{where}", " and d.id = :departmentId");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		if (StringUtils.isNotBlank(keyStr)) {
			sql = sql+" and (c.name like '%"+keyStr+"%' or c.loanType like '%"+keyStr+"%' or c.phone like '%"+keyStr+"%')";
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setParameterList("states", allottoemployees);
		
		if(dId!=null){
			query.setInteger("departmentId",dId);
		}
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getAllotToEmplCount(List<Integer> allottoemployees,
			Integer dId,String keyStr,Integer companyId,Integer departmentId) throws Exception {
		String sqlName = "getAllotCount";
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		String appendSql = "";
		if (dId!=null) {
			appendSql = appendSql+" and d.id = :departmentId";
		}

		if (LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			if (companyId!=null && companyId>0) {
				sql = sql.replace("#{companyId}", "  and c.companyId="+companyId);
			}else{
				sql = sql.replace("#{companyId}", " ");
			}
			
			if (departmentId!=null && departmentId>0) {
				sql = sql.replace("#{departmentId}", " and c.departmentId = "+departmentId);
			}else{
				sql = sql.replace("#{departmentId}", " ");
			}
		}

		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql.replace("#{departmentId}", "");
			sql = sql.replace("#{companyId}", "  and c.companyId="+LoginUserUtil.getCompanyId());
		}else {
			sql = sql.replace("#{companyId}", " ");
		}

		if (StringUtils.isNotBlank(keyStr)) {
			appendSql = appendSql+" and (c.name like '%"+keyStr+"%' or c.loanType like '%"+keyStr+"%' or c.phone like '%"+keyStr+"%')";
		}
		sql = sql.replace("#{where}", appendSql);
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("states", allottoemployees);
		
		if(dId!=null){
			query.setInteger("departmentId",dId);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public List<Map<String, Object>> getAssignedOrderList(Integer pageIndex,
			Integer pageSize, Integer id, String keyStr) throws Exception {
		//获取到SQL字符串
		String sql = "SELECT distinct(c.id),c.customerAsset,c.customerAssetTitile,d.name dname,"
				+ "e.name ename,c.phone,c.name cname,cs.sourceName,c.attentionLevel,c.loanType,c.state,c.turnDetail"
				+ " from customer c inner join "
				+ " allotcustomer a on c.id=a.customerId"
				+ " left join department d on c.departmentId=d.id"
				+ " left join employee e on c.employeeId=e.id"
				+ "	left join customersource cs on c.customerSourceId=cs.id"
				+ " WHERE 1=1 ";
		
		if(id!=null){
			sql = sql+" and a.operationId="+id;
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql+" and (c.name like :name or c.loanType like :loanType or c.phone "
					+ "like :phone or c.customerLevel like :customerLevel)";
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getAssignedCount(Integer id, String keyStr) throws Exception {
		//获取到SQL字符串
		String sql = "SELECT count(distinct c.id)"+
						"from customer c,"+
							"allotcustomer a"+
								" WHERE a.customerId=c.id ";
		if(id!=null){
			sql = sql+" and a.operationId="+id;
		}
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql+" and (c.name like :name or c.loanType like :loanType or c.phone "
					+ "like :phone or c.customerLevel like :customerLevel)";
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public List<Map<String, Object>> getReceiveOrderList(Integer pageIndex,
			Integer pageSize, Integer[] ids, String keyStr) throws Exception {
		//获取到SQL字符串
		String sql = "SELECT distinct(c.id),c.customerAsset,c.customerAssetTitile,d.name dname,"
				+ "e.name ename,c.phone,c.name cname,cs.sourceName,c.attentionLevel,c.loanType,c.state,c.turnDetail"
				+ " from customer c inner join "
				+ " allotcustomer a on c.id=a.customerId"
				+ " left join department d on c.departmentId=d.id"
				+ " left join employee e on c.employeeId=e.id"
				+ "	left join customersource cs on c.customerSourceId=cs.id"
				+ " WHERE 1=1 ";
		
		if(ids!=null){
			sql = sql+" and a.employeeId in(:ids) ";
		}
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql+" and (c.name like :name or c.loanType like :loanType or c.phone "
					+ "like :phone or c.customerLevel like :customerLevel)";
		}
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (ids!=null) {
			query.setParameterList("ids", ids);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getReceiveOrderCount(Integer[] ids, String keyStr)
			throws Exception {
		//获取到SQL字符串
		String sql = "SELECT count(distinct c.id) "+
						"from customer c,"+
							"allotcustomer a "+
								"WHERE a.customerId=c.id ";

		if(ids!=null){
			sql = sql+" and a.employeeId in(:ids) ";
		}
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql+" and (c.name like :name or c.loanType like :loanType or c.phone "
					+ "like :phone or c.customerLevel like :customerLevel)";
		}
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		if (ids!=null) {
			query.setParameterList("ids", ids);
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
