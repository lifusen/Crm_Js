package cn.itproject.crm.service;

import cn.itproject.crm.bean.Workday;

public interface WorkdayService extends BaseService<Workday>{

	/**
	 * 获取当月工作日对象
	 * @return
	 */
	Workday getCurrentMonthWorkDay() throws Exception;
	
}
