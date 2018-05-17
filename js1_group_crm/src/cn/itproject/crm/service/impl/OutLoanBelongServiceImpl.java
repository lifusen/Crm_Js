package cn.itproject.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.dao.OutLoanBelongDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.OutLoanBelongService;

@Service
public class OutLoanBelongServiceImpl extends BaseServiceSupport<OutLoanBelong> implements OutLoanBelongService {

	@Resource
	private OutLoanBelongDao outLoanBelongDao;
	

	@Override
	protected BaseDao<OutLoanBelong> getBaseDao() {
		return outLoanBelongDao;
	}
	
	@Override
	public Integer getType(Integer signCustomerId, Integer employeeId) throws Exception {
		return outLoanBelongDao.getType(signCustomerId, employeeId);
	}

	@Override
	public void addOutLoanBelong(OutLoanBelong outLoanBelong) throws Exception {
		outLoanBelongDao.save(outLoanBelong);
	}

	@Override
	public List<OutLoanBelong> getOutLoanBelong(Integer signCustomerId) throws Exception{
		return outLoanBelongDao.getOutLoanBelong(signCustomerId);
	}


}
