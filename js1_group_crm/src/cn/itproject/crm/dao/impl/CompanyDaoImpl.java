package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.dao.CompanyDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class CompanyDaoImpl extends BaseDaoSupport<Company> implements CompanyDao{

	@Override
	public List<Company> getList(Integer pageIndex, Integer pageSize) throws Exception{
		String hql = "from Company order by id desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(pageSize * (pageIndex - 1));
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public Integer getCount() throws Exception{
		String hql="select count(c) from Company c";
		Query query = getSession().createQuery(hql);
		return Integer.parseInt(String.valueOf(query.uniqueResult()));
	}

	@Override
	public List<Map<String, Object>> getAllCompanyIdAndName() throws Exception {
		String sql = "select id,name from company";
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}
