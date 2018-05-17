package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.BankProduct;
import cn.itproject.crm.dao.BankProductDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.BankProductService;

@Service
public class BankProductServiceImpl extends BaseServiceSupport<BankProduct> implements BankProductService{
	@Resource
	private BankProductDao bankProductDao;
	@Override
	protected BaseDao<BankProduct> getBaseDao() {
		return bankProductDao;
	}

}
