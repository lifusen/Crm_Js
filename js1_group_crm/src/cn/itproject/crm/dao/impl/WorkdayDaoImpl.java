package cn.itproject.crm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Workday;
import cn.itproject.crm.dao.WorkdayDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class WorkdayDaoImpl extends BaseDaoSupport<Workday> implements WorkdayDao{

	@Override
	public Workday getCurrentMonthWorkDay() throws Exception {
		String hql = "from Workday where EXTRACT(YEAR_MONTH FROM date) = EXTRACT(YEAR_MONTH FROM now())";
		return (Workday) getSession().createQuery(hql).uniqueResult();
	}
}
