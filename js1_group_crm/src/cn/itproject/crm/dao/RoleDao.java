package cn.itproject.crm.dao;


import java.util.List;

import cn.itproject.crm.bean.Role;
import cn.itproject.crm.db.BaseDao;

public interface RoleDao extends BaseDao<Role>{

	List<Role> getAll() throws Exception;

	Integer getCustomerNO(Integer id) throws Exception;

	void saveCustomerNO(Integer id, Integer customerNO) throws Exception;

}