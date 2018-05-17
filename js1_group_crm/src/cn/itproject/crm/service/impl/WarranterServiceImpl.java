package cn.itproject.crm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.dao.WarrantDepartmentDao;
import cn.itproject.crm.dao.WarranterDao;
import cn.itproject.crm.service.WarranterService;

@Service
public class WarranterServiceImpl implements WarranterService{

	@Resource
	private WarranterDao warranterDao;
	
	@Override
	public List<Map<String, Object>> orderList(WarrantOrderQuery query) throws Exception{
		List<Map<String, Object>> list = warranterDao.orderList(query);
		return list;
	}

	@Override
	public Integer getOrderCount(WarrantOrderQuery query) throws Exception {
		Integer count = warranterDao.getOrderCount(query);
		return count;
	}

	@Override
	public List<Map<String, Object>> search(Integer[] companyIdArray, Integer[] depIdArray, Integer[] empIdArray,
			Integer[] typeArray, String[] loanTypeArray, Date signDateStart, Date signDateEnd,Integer pageIndex,Integer pageSize) throws Exception {
		
		return warranterDao.search(companyIdArray, depIdArray, empIdArray,
			typeArray, loanTypeArray, signDateStart, signDateEnd,pageIndex,pageSize);
	}

	@Override
	public Integer searchCount(Integer[] companyIdArray, Integer[] depIdArray, Integer[] empIdArray,
			Integer[] typeArray, String[] loanTypeArray, Date signDateStart, Date signDateEnd) throws Exception {
		return warranterDao.searchCount(companyIdArray, depIdArray, empIdArray, typeArray, 
				loanTypeArray, signDateStart, signDateEnd);
	}
}