package cn.itproject.crm.service;


import java.util.List;

import cn.itproject.crm.bean.Role;

public interface RoleService extends BaseService<Role>{

	List<Role> getRoleList() throws Exception;

	/**
	 * 获取指定角色的持有客户数量上限
	 * @param id
	 * @return
	 * @throws Exception
	 * 2017年1月19日 下午5:43:43 by SwordLiu
	 */
	Integer getCustomerNO(Integer id) throws Exception;

	/**
	 * 修改指定角色持有客户数量上限
	 * @param id
	 * @param customerNO 
	 * @return
	 * @throws Exception
	 * 2017年1月20日 上午9:55:21 by SwordLiu
	 */
	void saveCustomerNO(Integer id, Integer customerNO) throws Exception;

}