package cn.itproject.crm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import cn.itproject.crm.bean.CustomerRoster;
import cn.itproject.crm.bean.CustomerRoster.RosterState;
import cn.itproject.crm.dao.CustomerRosterDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class CustomerRosterDaoImpl extends BaseDaoSupport<CustomerRoster> implements CustomerRosterDao{

	@Override
	public List<CustomerRoster> getListByState(Integer state,Integer eId) throws Exception {
		String hql = "";
		Query query = null;
		if(state==null){
			hql = "from CustomerRoster where state is null and successCount>0";
			if (eId!=null) {
				hql = hql+" and employeeId  = "+eId;
			}
			query = getSession().createQuery(hql);
		}else {
			hql = "from CustomerRoster where state = :state and successCount>0";
			if (eId!=null) {
				hql = hql+" and employeeId  = "+eId;
			}
			query = getSession().createQuery(hql);
			query.setInteger("state", state);
		}
		return query.list();
	}

	@Override
	public void updateState(RosterState old, Integer rId) throws Exception {
		String hql = "update CustomerRoster set state = :state where id = :id";
		Query query = getSession().createQuery(hql);
		query.setInteger("state", old.ordinal());
		query.setInteger("id", rId);
		query.executeUpdate();
	}

	@Override
	public void updateCount(Integer id,Integer count) throws Exception {
		String sql ="update CustomerRoster set `count` = :count where id = :id";
		getSession().createSQLQuery(sql)
			.setInteger("count", count)
			.setInteger("id", id)
			.executeUpdate();
	}
}
