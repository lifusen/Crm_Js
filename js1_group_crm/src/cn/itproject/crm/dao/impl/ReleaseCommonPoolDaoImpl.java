package cn.itproject.crm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.dao.ReleaseCommonPoolDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

/**
 * 批量释放客户到公共池实现
 * 
 * @author MrLiu
 *
 */
@Repository
public class ReleaseCommonPoolDaoImpl extends BaseDaoSupport<Customer>implements ReleaseCommonPoolDao {

	@Override
	public void release(List<Integer> ids, Integer releaseType, Integer releaseId) throws Exception {
		Query query = getSession().getNamedQuery("releaseCommonPoolByIds");
		query.setDate("releaseDate", new Date());
		query.setTimestamp("releaseTime", new Date());
		query.setInteger("releaseType", releaseType);
		query.setInteger("releaseId", releaseId);
		query.setParameterList("customerIds", ids);
		query.executeUpdate();
	}

}
