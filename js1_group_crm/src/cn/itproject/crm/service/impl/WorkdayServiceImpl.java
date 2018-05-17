package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Workday;
import cn.itproject.crm.dao.WorkdayDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.WorkdayService;

@Service
public class WorkdayServiceImpl extends BaseServiceSupport<Workday> implements WorkdayService{

	@Resource
	private WorkdayDao workdayDao;
	@Override
	protected BaseDao<Workday> getBaseDao() {
		return workdayDao;
	}
	@Override
	public Workday getCurrentMonthWorkDay() throws Exception {
		return workdayDao.getCurrentMonthWorkDay();
	}
}
