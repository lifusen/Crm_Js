package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.FailureMessage;
import cn.itproject.crm.dao.FailureMessageDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;
import cn.itproject.crm.service.FailureMessageService;

@Service
public class FailureMessageServiceImpl extends BaseServiceSupport<FailureMessage> implements FailureMessageService {
	
	@Resource
	private FailureMessageDao failureMessageDao;
	
	@Override
	protected BaseDao<FailureMessage> getBaseDao() {
		return failureMessageDao;
	}

	@Override
	public void addFailureMessage(FailureMessage failureMessage)
			throws Exception {
		if (failureMessage!=null) {
			failureMessageDao.save(failureMessage);
		}
	}

	@Override
	public List<FailureMessage> getFailureMessageBySignCustomerId(Integer signCustomerId) throws Exception {
		String hql = "from FailureMessage where signCustomerId = ? order by id";
		return failureMessageDao.getList(hql, signCustomerId);
	}

	@Override
	public List<Map<String, Object>> getRejectCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds, String keyword,
			String orderColumn, OrderByType orderByType) throws Exception {
		return failureMessageDao.getRejectCustomerList(pageIndex, pageSize, employeeIds, keyword, orderColumn, orderByType);
	}

	@Override
	public Integer getRejectCustomerListCount(List<Integer> employeeIds,
			String keyword) throws Exception {
		return failureMessageDao.getRejectCustomerListCount(employeeIds, keyword);
	}

}
