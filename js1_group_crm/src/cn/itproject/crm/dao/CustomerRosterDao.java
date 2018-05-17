package cn.itproject.crm.dao;

import java.util.List;

import cn.itproject.crm.bean.CustomerRoster;
import cn.itproject.crm.bean.CustomerRoster.RosterState;
import cn.itproject.crm.db.BaseDao;

/**
 * 客户名单DAO接口
 * @author MrLiu
 *
 */
public interface CustomerRosterDao extends BaseDao<CustomerRoster>{

	List<CustomerRoster> getListByState(Integer state,Integer eId) throws Exception;

	void updateState(RosterState old, Integer rId) throws Exception;

	void updateCount(Integer id,Integer count) throws Exception;

}
