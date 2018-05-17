package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.OutLoanCustomer;
import cn.itproject.crm.dao.OutLoanCustomerDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.db.utils.OrderByType;

/**
 * 放款客户DAO实现类
 * @author MrLiu
 *
 */
@Repository
public class OutLoanCustomerDaoImpl extends BaseDaoSupport<OutLoanCustomer> implements OutLoanCustomerDao{

	@Override
	public List<Map<String, Object>> getOutLoanCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds, String keyword,
			String orderColumn, OrderByType orderByType, Integer type)
			throws Exception {
		System.out.println("employeeIds:"+employeeIds);
		String sqlName = "getOutLoanCustomerListByEmployeeIds";
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
	public Integer getOutLoanCustomerListCount(List<Integer> employeeIds,
			String keyword, Integer type) throws Exception {
		System.out.println("employeeIds:"+employeeIds);
		String sqlName = "getOutLoanCustomerListCountByEmployees";
		//获取到SQL命名查询
		Query query = getSession().getNamedQuery(sqlName);
		query.setParameterList("employeeIds", employeeIds);
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getOutLoanCustomerIdsByCId(Integer cId) throws Exception {
		String hql = "select s.id from OutLoanCustomer s where s.customer.id = :cId order by s.id";
		return getSession().createQuery(hql)
				.setInteger("cId", cId)
				.list();
	}

	@Override
	public OutLoanCustomer getOutloanCustomerBySId(Integer sId)
			throws Exception {
		String hql = "from OutLoanCustomer where signCustomer.id = :sId";
		return (OutLoanCustomer) getSession().createQuery(hql)
			.setInteger("sId", sId)
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getOutLoanCustomerSummaryInfo(Integer signCustomerId) throws Exception {
		String sql = "select o.id,o.outLoanNum,o.name,o.warrantName,";
		sql += " DATE_FORMAT(o.createTime,'%Y-%m-%d %H:%i') createTime";
		sql += " from outloancustomer o";
		sql += " where o.signCustomerId = "+signCustomerId;
		sql += " order by o.id desc";
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public OutLoanCustomer getOutloanCustomerById(Integer oId, String sql) throws Exception {
		
		return null;
	}

}
