package cn.itproject.crm.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.dao.DepartmentDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoSupport<Department> implements DepartmentDao{

	@Override
	@Cacheable("departmentCache")
	public List<Department> queryListByName(String type,Integer companyId) throws Exception {
		String hql = "from Department where type like :type ";
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			hql = hql + " and companyId="+companyId;
		}
		hql = hql+" order by id";
		
		Query query = getSession().createQuery(hql);
		query.setString("type", type+"%");
		return query.list();
	}

	@Override
	public List<Department> getByCompanyId(Integer companyId) throws Exception{
		String hql = "";
		if (companyId!=null) {
			hql = "from Department where companyId = "+companyId+" order by id";
		}else{
			hql = "from Department order by id";
		}
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Cacheable("departmentCache")
	public List<Department> getDepartmentListByCompanyId(Integer companyId) throws Exception {
		String hql = "from Department where companyId = :companyId";
		Query query = getSession().createQuery(hql);
		query.setInteger("companyId", companyId);
		return query.list();
	}

	@Override
	@Cacheable("departmentCache")
	public List<Department> getDepartmentList() throws Exception {
		String hql = "from Department";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAllDepartmentIdAndName(Integer companyId,Integer type) throws Exception {
		String sql = "select id,name from department";
		if (companyId != null) {
			sql += " where companyId = :companyId";
		}
		if (type != null && type == 1) {
			sql += " and type = :type";
		}
		sql += " order by id";
		Query query = getSession().createSQLQuery(sql);
		if (companyId != null) {
			query.setInteger("companyId", companyId);
		}
		if (type != null && type == 1) {
			query.setString("type", "业务");
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<Department> getByCompanyIds(Integer[] idArray,String type) throws Exception {
		if (idArray==null || idArray.length==0) {
			return null;
		}
		String hql = "";
		if (type==null) {
			hql = "from Department where companyId in(:ids)";
		}else{
			hql = "from Department where companyId in(:ids) and type like :type";
		}
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", idArray);
		if (type!=null && !type.equals("")) {
			query.setParameter("type", type);
		}
		return query.list();
	}

	@Override
	public List<Department> getDeparmentByCompanyType(Integer companyType) throws Exception {
		String sql = "select d.id,d.name from department d left join company c on d.companyId=c.id where c.type="+companyType;
		sql += " order by c.id";
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}
