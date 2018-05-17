package cn.itproject.crm.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.dao.CenterDao;
import cn.itproject.crm.service.CenterService;

@Service
public class CenterServiceImpl implements CenterService{

	@Resource
	private CenterDao centerDao;
	
	@Override
	public List<Map<String, Object>> orderList(WarrantOrderQuery query) throws Exception{
		List<Map<String, Object>> list = centerDao.orderList(query);
		return list;
	}

	@Override
	public Integer getOrderCount(WarrantOrderQuery query) throws Exception {
		Integer count = centerDao.getOrderCount(query);
		return count;
	}
}