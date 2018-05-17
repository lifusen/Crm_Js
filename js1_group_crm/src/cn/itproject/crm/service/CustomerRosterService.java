package cn.itproject.crm.service;

import java.util.List;

import cn.itproject.crm.bean.CustomerRoster;

/**
 * 客户来源业务接口
 * @author MrLiu
 *
 */
public interface CustomerRosterService extends BaseService<CustomerRoster>{

	List<CustomerRoster> getListByState(Integer state,Integer eId) throws Exception;
	
	void updateCount(Integer id,Integer count) throws Exception;

}
