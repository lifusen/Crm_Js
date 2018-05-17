package cn.itproject.crm.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.viewbean.CDEVo;
import cn.itproject.crm.dao.EmployeeDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository
public class EmployeeDaoImpl extends BaseDaoSupport<Employee> implements EmployeeDao{

	@Override
	@Cacheable("employeeCache")
	public List<Employee> getList(Integer pageIndex, Integer pageSize,Integer companyId) throws Exception{
		String hql = "from Employee where status != 2 and (type is null or type != 'SUPER_ROLE')";
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			hql = hql + " and companyId="+companyId;
		}
		
		hql = hql+" order by id desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Employee> getListByDId(Integer eId, Integer dId)
			throws Exception {
//		String hql = "from Employee where id != :eId and department.id=:dId and status = 1";
		String hql = "from Employee where department.id=:dId and status = 1";
		Query query = getSession().createQuery(hql);
//		query.setInteger("eId", eId);
		query.setInteger("dId", dId);
		return query.list();
	}

	@Override
	public List<Employee> getListByDIds(Integer[] ids) throws Exception {
		if (ids==null || ids.length==0) {
			return null;
		}
//		String hql = "from Employee where department.id in (:dIds) and role.id != 3 and status = 1";	// 角色为3的是部门经理不用查出来
		String hql = "from Employee where department.id in (:dIds) and status = 1";
		Query query = getSession().createQuery(hql);
		query.setParameterList("dIds", ids);
		return query.list();
	}

	@Override
	@Cacheable("employeeCache")
	public Integer getValidCount(Integer companyId) throws Exception {
		String hql="select count(o) from Employee o where  status = 1";
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			hql = hql + " and companyId="+companyId;
		}
		Query query = getSession().createQuery(hql);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public Employee getEmployeeInfoByCustomerId(Integer customerId) throws Exception {
		//根据客户ID在历史状态表中查询出操作人员ID
		String hql = "select c.employee from Customer c.id = :customerId";
		Integer employeeId = (Integer) getSession().createQuery(hql)
			.setInteger("customerId", customerId)
			.setFirstResult(0)
			.setMaxResults(1)
			.uniqueResult();
		System.out.println("employeeId:"+employeeId);
		if (employeeId==1) {
			hql = "select new Employee(e.name) from Employee e where e.id = :employeeId and e.status = 1";
		}else {
			hql = "select new Employee(e.name,e.department.name) from Employee e where e.id = :employeeId and e.status = 1";
		}
		return (Employee) getSession().createQuery(hql)
				.setInteger("employeeId", employeeId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("employeeCache")
	public List<Integer> getEmployeeIds(Integer companyId) throws Exception {
		String hql = "select e.id from Employee e where status = 1";
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			hql = hql + " and companyId="+companyId;
		}
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("employeeCache")
	public List<Integer> getEmployeeIdsByDId(Integer departmentId)
			throws Exception {
		String hql = "select e.id from Employee e where e.department.id = :departmentId and status = 1";
		return getSession().createQuery(hql)
			.setInteger("departmentId", departmentId)
			.list();
	}

	@Override
	public int getCountByRoleAnd(Integer roleId, Integer departmentId) throws Exception{
		String hql="select count(o) from Employee o where roleId = :roleId and departmentId = :departmentId";
		Query query = getSession().createQuery(hql).setInteger("roleId", roleId).setInteger("departmentId", departmentId);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAllEmployeeIdAndName(Integer departmentId) throws Exception {
		String sql = "select id,name from employee";
		if (departmentId != null) {
			sql += " where departmentId = :departmentId";
			sql += " and status = 1";
		}
		sql += " order by id";
		Query query = getSession().createSQLQuery(sql);
		if (departmentId != null) {
			query.setInteger("departmentId", departmentId);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	@Override
	public List<CDEVo> getCDEList(Integer... roleIds) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select ");
		buffer.append("  d.id departmentId,");
		buffer.append("  d.name departmentName,");
		buffer.append("  e.id employeeId,");
		buffer.append("  e.name employeeName,");
		buffer.append("  c.id companyId,");
		buffer.append("  c.name companyName");
		buffer.append(" from employee e");
		buffer.append(" inner join department d");
		buffer.append(" on e.departmentId = d.id");
		buffer.append(" inner join company c");
		buffer.append(" on e.companyId = c.id");
		buffer.append(" where status = 1 ");
		String sql = buffer.toString();
		if (roleIds != null && roleIds.length > 0) {
			sql += " and roleId in (:roleIds)";
		}
 		Query query = getSession().createSQLQuery(sql);
 		if (roleIds != null && roleIds.length > 0) {
			query.setParameterList("roleIds", roleIds);
		}
 		query.setResultTransformer(Transformers.aliasToBean(CDEVo.class));
		return query.list();
	}
	
	@Override
	public void updateEmail(Integer employeeId,String email) throws Exception {
		String sql = "update employee set email = :email where id = :employeeId";
		Query query = getSession().createSQLQuery(sql);
		query.setString("email", email);
		query.setInteger("employeeId", employeeId);
		query.executeUpdate();
	}
	
	@Override
	public String getEmail(Integer employeeId) throws Exception {
		String sql = "select email from employee where id = :employeeId";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger("employeeId", employeeId);
		Object object = query.uniqueResult();
		if (object == null) {
			return null;
		}
		return (String) object;
	}
	
	@Override
	public Long getEmailCount(String email) throws Exception {
		String sql = "select count(id) from employee where email = :email";
		Query query = getSession().createSQLQuery(sql);
		query.setString("email", email);
		BigInteger object = (BigInteger) query.uniqueResult();
		return object.longValue();
	}
	
	@Override
	public String getAccountByEmail(String email) throws Exception {
		String sql = "select account from employee where email = :email";
		Query query = getSession().createSQLQuery(sql);
		query.setString("email", email);
		Object object = query.uniqueResult();
		if (object == null) {
			return null;
		}
		return (String) object;
	}
	
	@Override
	public void updatePasswordByEmail(String email,String password) throws Exception {
		String sql = "update employee set password = :password where email = :email";
		Query query = getSession().createSQLQuery(sql);
		query.setString("password", password);
		query.setString("email", email);
		query.executeUpdate();
	}
	
	@Override
	public List<Map<String, Object>> getAccountListByEmail(String email) throws Exception {
		String sql = "select id,account from employee where email = :email order by id";
		Query query = getSession().createSQLQuery(sql);
		query.setString("email", email);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	@Override
	public void updatePasswordByEmployeeId(Integer employeeId, String password) throws Exception {
		String sql = "update employee set password = :password where id = :employeeId";
		Query query = getSession().createSQLQuery(sql);
		query.setString("password", password);
		query.setInteger("employeeId", employeeId);
		query.executeUpdate();
	}

	@Override
	public List<Employee> getEmployyeByCompanyId(Integer companyId) throws Exception {
		String hql = "from Employee e where status = 1";
			hql = hql + " and companyId="+companyId;
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Map<String, Object>> getCustomerNO(Integer employeeId) throws Exception {
		String sql = "select e.customerNO,r.customerNO as roleCustomerNO,r.roleName"
				+ " from employee e left join role r on e.roleId=r.id where e.id=:id";
		Query query = getSession().createSQLQuery(sql).setInteger("id", employeeId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public void saveCustomerNO(Integer eId, Integer currentNO) throws Exception {
		String sql = "update employee set customerNO=:currentNO where id=:eId";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger("currentNO", currentNO);
		query.setInteger("eId", eId);
		query.executeUpdate();
		
	}
}
