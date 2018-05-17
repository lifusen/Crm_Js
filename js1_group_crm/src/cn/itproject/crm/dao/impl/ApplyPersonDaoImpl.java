package cn.itproject.crm.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.ApplyPerson;
import cn.itproject.crm.dao.ApplyPersonDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class ApplyPersonDaoImpl extends BaseDaoSupport<ApplyPerson> implements ApplyPersonDao {

	@Override
	public String getPersonByIdDao(Integer id) throws Exception {
		String sql = "select a.situation from ApplyPerson a where a.id = :id";
		Query query = getSession().createQuery(sql);
		query.setInteger("id", id);
		String result = (String) query.uniqueResult();
		return result;
	}

	@Override
	public String getPersonNameById(Integer id) throws Exception {
		String sql = "select a.name from ApplyPerson a where a.id = :id";
		Query query = getSession().createQuery(sql);
		query.setInteger("id", id);
		String name = (String) query.uniqueResult();
		return name;
	}

}
