package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.LoanProduct;
import cn.itproject.crm.dao.LoanProductDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.LoanProductService;

@Service
public class LoanProductServiceImpl extends BaseServiceSupport<LoanProduct> implements LoanProductService {
	
	@Resource
	private LoanProductDao loanProductDao;
	
	@Override
	protected BaseDao<LoanProduct> getBaseDao() {
		return loanProductDao;
	}

}
