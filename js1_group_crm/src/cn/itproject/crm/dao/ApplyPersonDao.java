package cn.itproject.crm.dao;

import cn.itproject.crm.bean.ApplyPerson;
import cn.itproject.crm.db.BaseDao;

public interface ApplyPersonDao extends BaseDao<ApplyPerson> {

	String getPersonByIdDao(Integer id) throws Exception;

	String getPersonNameById(Integer id) throws Exception;

}
