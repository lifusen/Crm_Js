package cn.itproject.crm.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.ContractManager;
import cn.itproject.crm.dao.ContractManagerDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;

@Repository
public class ContractManagerDaoImpl extends BaseDaoSupport<ContractManager> implements ContractManagerDao {

	@Override
	public ContractManager getContractManagerById(Integer id) throws Exception {

		return null;
	}

	@Override
	public void updateContractManager(ContractManager contractManager) throws Exception {

	}

	@Override
	public void addContractManager(ContractManager contractManager) throws Exception {

	}

}
