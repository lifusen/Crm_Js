package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Loaning;
import cn.itproject.crm.dao.LoaningDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.LoaningService;

@Service
public class LoaningServiceImpl extends BaseServiceSupport<Loaning> implements LoaningService{
	@Resource
	private LoaningDao loaningDao;
	@Override
	protected BaseDao<Loaning> getBaseDao() {
		return loaningDao;
	}

}
