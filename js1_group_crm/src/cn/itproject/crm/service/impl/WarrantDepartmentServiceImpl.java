package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.dao.CenterDao;
import cn.itproject.crm.dao.WarrantDepartmentDao;
import cn.itproject.crm.service.WarrantDepartmentService;

@Service
public class WarrantDepartmentServiceImpl implements WarrantDepartmentService{

	@Resource
	private WarrantDepartmentDao warrantDepartmentDao;
	
	@Override
	public List<Map<String, Object>> orderList(WarrantOrderQuery query) throws Exception{
		List<Map<String, Object>> list = warrantDepartmentDao.orderList(query);
		return list;
	}

	@Override
	public Integer getOrderCount(WarrantOrderQuery query) throws Exception {
		Integer count = warrantDepartmentDao.getOrderCount(query);
		return count;
	}

}