package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.dao.CustomerSourceDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

/**
 * 客户来源DAO实现类
 * @author MrLiu
 *
 */
@Repository
public class CustomerSourceDaoImpl extends BaseDaoSupport<CustomerSource> implements CustomerSourceDao{

	@Override
	public Integer getIdByName(String sourceName) throws Exception {
		String hql = "select c.id from CustomerSource c where c.sourceName = :name";
		return (Integer) getSession().createQuery(hql)
			.setString("name", sourceName)
			.uniqueResult();
	}

	@Override
	public List<Map<String, Object>> getAllCustomerSourceIdAndName() throws Exception {
		String sql = "select id,sourceName as name from customerSource";
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	

	@Override
	public List<Map<String, Object>> getCustomerSourcePie(String order) throws Exception {
		String sql = "select temp.customersourceId id,cs.sourceName name,IFNULL(temp.total,0) value";
			sql += " from ( ";
			sql += " select customersourceId,count(customersourceId) as total from customer";
			sql += " group by customersourceId";
			sql += " ) temp";
			sql += " right join customersource cs";
			sql += " on temp.customerSourceId = cs.id";
			sql += " order by "+order;
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
}
