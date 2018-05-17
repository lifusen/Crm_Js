package cn.itproject.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.dao.CustomerDataDao;
import cn.itproject.crm.dao.ReleaseCommonPoolDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.ReleaseCommonPoolService;

@Service
public class ReleaseCommonPoolServiceImpl extends BaseServiceSupport<Customer>implements ReleaseCommonPoolService {

	@Resource
	private ReleaseCommonPoolDao releaseCommonPoolDao;
	@Resource
	private CustomerDataDao customerDataDao;

	@Override
	protected BaseDao<Customer> getBaseDao() {
		return releaseCommonPoolDao;
	}

	@Override
	public void batchRelease(Integer departmentId, Integer employeeId, String customerLevel, String attentionLevel,
			Integer releaseId) throws Exception {
		List<Integer> customerIds = customerDataDao.getCustomerList(departmentId, employeeId, customerLevel,
				attentionLevel);
		releaseCommonPoolDao.release(customerIds, 2, releaseId);
	}

	public Integer getBatchReleaseCount(Integer departmentId, Integer employeeId, String customerLevel,
			String attentionLevel) throws Exception {
		List<Integer> customerIds = customerDataDao.getCustomerList(departmentId, employeeId, customerLevel,
				attentionLevel);
		if (customerIds != null) {
			return customerIds.size();
		}
		return 0;
	}
}
