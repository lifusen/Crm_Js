package cn.itproject.crm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.dao.CustomerDataDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class CustomerDataDaoImpl extends BaseDaoSupport<Customer>implements CustomerDataDao {

	@Override
	public List<Integer> getCustomerList(Integer departmentId, Integer employeeId, String customerLevel,
			String attentionLevel) throws Exception {
		String sql = "select c.id from customer c where state = 3 and signState = :signState ";
		// 部门id
		if (departmentId != null) {
			sql += " and c.departmentId = :departmentId";
		}
		// 员工id
		if (employeeId != null) {
			sql += " and c.employeeId = :employeeId";
		}
		// 客户等级
		if (customerLevel != null) {
			sql += " and c.customerLevel = :customerLevel";
		}
		// 关注等级
		if (attentionLevel != null) {
			sql += " and c.attentionLevel = :attentionLevel";
		}

		Query query = getSession().createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER);
		// 设置参数
		query.setString("signState", "未签单");
		// 部门id
		if (departmentId != null) {
			query.setInteger("departmentId", departmentId);
		}
		// 员工id
		if (employeeId != null) {
			query.setInteger("employeeId", employeeId);
		}
		// 客户等级
		if (customerLevel != null) {
			query.setString("customerLevel", customerLevel);
		}
		// 关注等级
		if (attentionLevel != null) {
			query.setString("attentionLevel", attentionLevel);
		}

		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();
		return list;
	}

}
