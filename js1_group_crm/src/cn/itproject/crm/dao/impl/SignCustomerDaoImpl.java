package cn.itproject.crm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.dao.SignCustomerDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.db.utils.OrderByType;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

/**
 * 签单客户DAO实现类
 * 
 * @author MrLiu
 *
 */
@Repository
public class SignCustomerDaoImpl extends BaseDaoSupport<SignCustomer> implements SignCustomerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSignCustomerList(Integer pageIndex, Integer pageSize, List<Integer> employeeIds,
			String keyword, String orderColumn, OrderByType orderByType, Integer type) throws Exception {
		System.out.println("employeeIds:" + employeeIds);
		String sqlName = "getSignCustomerList";
		// 获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		// 获取到SQL字符串
		String sql = sqlQuery.getQueryString();

		if (employeeIds == null) {
			sql = sql.replace("#{employeeIds}", "");
		} else {
			sql = sql.replace("#{employeeIds}", " and h.nowEmployeeId in (:employeeIds) ");
		}

		if (type == 1) {// 放款客户列表
			sql = sql.replace("#{where}", " where s.outLoanCondition = '已放款' ");
		} else {
			sql = sql.replace("#{where}", "");
		}

		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId=" + LoginUserUtil.getCompanyId();
		}

		// 拼接排序
		sql += " order by s.contractNo desc ";

		// 创建查询对象
		Query query = getSession().createSQLQuery(sql);

		if (employeeIds != null) {
			query.setParameterList("employeeIds", employeeIds);
		}
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<Map<String, Object>> list = query.list();
		return list;
	}

	@Override
	public Integer getSignCustomerListCount(List<Integer> employeeIds, String keyword, Integer type) throws Exception {
		System.out.println("employeeIds:" + employeeIds);
		String sqlName = "getSignCustomerListCountByEmployees";
		// 获取到SQL命名查询
		Query query = getSession().getNamedQuery(sqlName);
		String sql = query.getQueryString();

		if (employeeIds == null) {
			sql = sql.replace("#{employeeIds}", "");
		} else {
			sql = sql.replace("#{employeeIds}", " and h.nowEmployeeId in (:employeeIds) ");
		}

		if (type == 1) {// 放款客户列表
			sql = sql.replace("#{where}", " and s.outLoanCondition = '已放款' ");
		} else {
			sql = sql.replace("#{where}", "");
		}

		query = getSession().createSQLQuery(sql);
		if (employeeIds != null) {
			query.setParameterList("employeeIds", employeeIds);
		}

		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getSignCustomerIdsByCId(Integer cId) throws Exception {
		String hql = "select s.id from SignCustomer s where s.customer.id = :cId order by s.id";
		return getSession().createQuery(hql).setInteger("cId", cId).list();
	}

	@Override
	public List<Map<String, Object>> getMaintainList(Integer pageIndex, Integer pageSize, String keyword)
			throws Exception {
		String sqlName = "signCustomerServeRatingList";
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		// 获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		if (keyword != null && !keyword.equals("")) {
			sql = sql + " and (c.name like :name or s.loanType like :loanType or c.phone "
					+ "like :phone or c.customerLevel like :customerLevel)";
		}
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId=" + LoginUserUtil.getCompanyId();
		}
		// 拼接排序
		sql += " order by s.signTime";

		// 创建查询对象
		Query query = getSession().createSQLQuery(sql);
		if (keyword != null && !keyword.equals("")) {
			query.setString("loanType", "%" + keyword + "%");
			query.setString("phone", "%" + keyword + "%");
			query.setString("customerLevel", "%" + keyword + "%");
			query.setString("name", "%" + keyword + "%");
		}
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<Map<String, Object>> list = query.list();
		return list;
	}

	@Override
	public Integer getMaintainCount(String keyword) throws Exception {
		String sqlName = "signCustomerServeRatingListCount";
		// 获取到SQL命名查询
		Query query = getSession().getNamedQuery(sqlName);
		String sql = query.getQueryString();
		if (keyword != null && !keyword.equals("")) {
			sql = sql.replace("#{whereKey}", " and (c.name like :name or s.loanType like :loanType or c.phone "
					+ "like :phone or c.customerLevel like :customerLevel)");
		} else {
			sql = sql.replace("#{whereKey}", " ");
		}
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql.replace("#{companyId}", "  and c.companyId=" + LoginUserUtil.getCompanyId());
		} else {
			sql = sql.replace("#{companyId}", " ");
		}

		query = getSession().createSQLQuery(sql);
		if (keyword != null && !keyword.equals("")) {
			query.setString("loanType", "%" + keyword + "%");
			query.setString("phone", "%" + keyword + "%");
			query.setString("customerLevel", "%" + keyword + "%");
			query.setString("name", "%" + keyword + "%");
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public void updateWarrant(Integer[] idArray, Integer companyId, Integer dId, Integer eId) throws Exception {
		String sql = "update signcustomer s set ";

		if (eId != null && eId > 0) {
			sql = sql + " s.listType =3";
		} else if (dId != null && dId > 0) {
			sql = sql + " s.listType =2";
		} else if (companyId != null && companyId > 0) {
			sql = sql + " s.listType =1";
		}

		if (companyId != null && companyId > 0) {
			sql = sql + " ,s.warrantCompanyId =" + companyId;
		}
		if (dId != null && dId > 0) {
			sql = sql + " ,s.warrantDepartmentId =" + dId;
		}
		if (eId != null && eId > 0) {
			sql = sql + " ,s.warrantEmployeeId =" + eId;
			sql = sql + " ,s.orderType = 0";
		}
		sql = sql + " ,s.updateTime=:updateTime";
		sql = sql + " where s.id in (:ids)";
		Query query = getSession().createSQLQuery(sql);
		query.setString("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		query.setParameterList("ids", idArray);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSignCustomerSummaryInfo(Integer customerId) throws Exception {
		String sql = "select s.id,s.contractNO,sc.name,s.warrantCompanyId,s.warrantDepartmentId,s.warrantEmployeeId,";
		sql += " DATE_FORMAT(s.signTime,'%Y-%m-%d %H:%i') signTime";
		sql += " from signcustomer s";
		sql += " inner join signcontacts sc";
		sql += " on s.id = sc.signCustomerId";
		sql += " where s.customerId = " + customerId;
		sql += " order by s.id desc";
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public SignCustomer getSignCustomer(Integer customerId, Integer signCustomerId) throws Exception {
		String hql = "from SignCustomer s where ";
		if (signCustomerId != null && signCustomerId > 0) {
			hql += "s.id = " + signCustomerId;
		} else {
			hql += "s.customer.id = " + customerId;
			hql += " order by s.id desc";
		}
		System.out.println(hql);
		Query query = getSession().createQuery(hql);
		if (!(signCustomerId != null && signCustomerId > 0)) {
			query.setFirstResult(0);
			query.setMaxResults(1);
		}
		return (SignCustomer) query.uniqueResult();
	}

	@Override
	public void updateOrderType(Integer signCustomerId, Integer type) throws Exception {
		String sql = "update signCustomer s set s.orderType=:orderType" + " where id=:signCustomerId";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger("orderType", type);
		query.setInteger("signCustomerId", signCustomerId);
		query.executeUpdate();
	}

	@Override
	public SignCustomer getSignCustomerByContactNo(String contractNO) throws Exception {
		if (StringUtils.isBlank(contractNO)) {
			return null;
		} else {
			String hql = "from SignCustomer s where s.contractNO = " + "'" + contractNO + "'";
			System.out.println(hql);
			Query query = getSession().createQuery(hql);
			SignCustomer result = (SignCustomer) query.uniqueResult();
			return result;
		}
	}
}
