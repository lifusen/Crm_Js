package cn.itproject.crm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.dao.OutLoanBelongDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class OutLoanBelongDaoImpl extends BaseDaoSupport<OutLoanBelong> implements OutLoanBelongDao {

	@Override
	public Integer getType(Integer signCustomerId, Integer employeeId) throws Exception {
		String sql = "select type from outloanbelong where signCustomerId=:signCustomerId and warranterId=:employeeId";
		Query query = getSession().createSQLQuery(sql);
		query.setInteger("signCustomerId", signCustomerId);
		query.setInteger("employeeId", employeeId);
		Integer type = (Integer) query.uniqueResult();
		return type;
	}

	@Override
	public List<OutLoanBelong> getOutLoanBelong(Integer signCustomerId) throws Exception {
		String sql = "from OutLoanBelong where signCustomerId=:signCustomerId";
		Query query = getSession().createQuery(sql);
		query.setInteger("signCustomerId", signCustomerId);
		return query.list();
	}

	@Override
	public boolean removeAssistLoaner(OutLoanBelong outLoanBelong) throws Exception {
		String sql = "delete from OutLoanBelong where signCustomerId=:signCustomerId "
				+ "and companyId=:companyId and departmentId=:departmentId and warranterId=:employeeId";
		Query query = getSession().createQuery(sql);
		query.setInteger("signCustomerId", outLoanBelong.getSignCustomerId());
		query.setInteger("companyId", outLoanBelong.getCompanyId());
		query.setInteger("departmentId", outLoanBelong.getDepartmentId());
		query.setInteger("employeeId", outLoanBelong.getWarranterId());
		query.executeUpdate();
		return true;
	}

	@Override
	public OutLoanBelong getOutLoanBelong(Integer signCustomerId, Integer employeeId) throws Exception {
		String sql = "from OutLoanBelong where signCustomerId=:signCustomerId and warranterId=:employeeId";
		Query query = getSession().createQuery(sql);
		query.setInteger("signCustomerId", signCustomerId);
		query.setInteger("employeeId", employeeId);
		return (OutLoanBelong) query.uniqueResult();
	}

	@Override
	public Integer updateState(Integer signCustomerId, Integer warranterId) throws Exception {
		String sql = "update OutLoanBelong o set o.outLoanState=1 where signCustomerId=:signCustomerId and warranterId=:employeeId and outLoanState=0";
		Query query = getSession().createQuery(sql);
		query.setInteger("signCustomerId", signCustomerId);
		query.setInteger("employeeId", warranterId);
		return query.executeUpdate();
		
	}

}
