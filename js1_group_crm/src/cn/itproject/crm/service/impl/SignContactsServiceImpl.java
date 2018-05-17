package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import cn.itproject.crm.bean.SignContacts;
import cn.itproject.crm.dao.SignContactsDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.SignContactsService;

public class SignContactsServiceImpl extends BaseServiceSupport<SignContacts> implements SignContactsService {
	
	@Resource
	private SignContactsDao signContactsDao;
	
	@Override
	protected BaseDao<SignContacts> getBaseDao() {
		return signContactsDao;
	}

}
