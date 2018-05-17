package cn.itproject.crm.dao;

import cn.itproject.crm.bean.Workday;
import cn.itproject.crm.db.BaseDao;

public interface WorkdayDao extends BaseDao<Workday>{

	Workday getCurrentMonthWorkDay() throws Exception;

}
