package cn.itproject.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.ContractManager;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.dao.ContractManagerDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.ContractManagerService;

@Service
public class ContractManagerServiceImpl extends BaseServiceSupport<ContractManager> implements ContractManagerService {

	@Resource
	private ContractManagerDao contractManagerDao;

	@Override
	public List<ContractManager> getListByState(Integer pageIndex, Integer pageSize, Integer state, Integer eId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addManagerContract(ContractManager contractManager, Employee employee) throws Exception {
		// 设置录入人ID和姓名
		contractManager.setAddPersonId(employee.getId());
		contractManager.setAddPersonName(employee.getName());

		// 保存合同信息
		contractManagerDao.save(contractManager);
	}

	@Override
	public void updateManagerContract(ContractManager contractManager) throws Exception {
		contractManagerDao.update(contractManager);
	}

	@Override
	protected BaseDao<ContractManager> getBaseDao() {
		return null;
	}

	@Override
	public ContractManager getContManaById(Integer id) throws Exception {
		return contractManagerDao.get(id);

	}

}
