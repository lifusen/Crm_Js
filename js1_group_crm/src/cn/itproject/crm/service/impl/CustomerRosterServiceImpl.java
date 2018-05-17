package cn.itproject.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.CustomerRoster;
import cn.itproject.crm.bean.CustomerRoster.RosterState;
import cn.itproject.crm.dao.CustomerRosterDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.CustomerRosterService;

@Service
public class CustomerRosterServiceImpl extends BaseServiceSupport<CustomerRoster> implements CustomerRosterService{
	@Resource
	private CustomerRosterDao customerRosterDao;
	@Override
	protected BaseDao<CustomerRoster> getBaseDao() {
		return customerRosterDao;
	}
	@Override
	public List<CustomerRoster> getListByState(Integer state,Integer eId) throws Exception {
		return customerRosterDao.getListByState(state,eId);
	}
	@Override
	public void updateCount(Integer id,Integer count) throws Exception {
		if (count==0) {
			customerRosterDao.updateState(RosterState.old, id);
		}else {
			customerRosterDao.updateCount(id,count);
		}
	}
}
