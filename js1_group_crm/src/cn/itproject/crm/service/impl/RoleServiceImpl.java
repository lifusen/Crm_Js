package cn.itproject.crm.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Role;
import cn.itproject.crm.dao.RoleDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceSupport<Role> implements RoleService{

	@Resource
	private RoleDao roleDao;
	
	@Override
	protected BaseDao<Role> getBaseDao() {
		return roleDao;
	}

	@Override
	public List<Role> getRoleList() throws Exception{
		return roleDao.getAll();
	}

	@Override
	public Integer getCustomerNO(Integer id) throws Exception {
		return roleDao.getCustomerNO(id);
	}

	@Override
	public void saveCustomerNO(Integer id,Integer customerNO) throws Exception {
		roleDao.saveCustomerNO(id, customerNO);
	}
	
}