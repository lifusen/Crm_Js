package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.EmailLog;
import cn.itproject.crm.dao.EmailLogDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.EmailLogService;

@Service
public class EmailLogServiceImpl extends BaseServiceSupport<EmailLog> implements EmailLogService {
	
	@Resource
	private EmailLogDao emailLogDao;
	
	@Override
	protected BaseDao<EmailLog> getBaseDao() {
		return emailLogDao;
	}

}
