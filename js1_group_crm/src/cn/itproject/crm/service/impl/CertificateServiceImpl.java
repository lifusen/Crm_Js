package cn.itproject.crm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Certificate;
import cn.itproject.crm.dao.CertificateDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.CertificateService;

@Service
public class CertificateServiceImpl extends BaseServiceSupport<Certificate> implements CertificateService {
	
	@Resource
	private CertificateDao certificateDao;
	
	@Override
	protected BaseDao<Certificate> getBaseDao() {
		return certificateDao;
	}

}
