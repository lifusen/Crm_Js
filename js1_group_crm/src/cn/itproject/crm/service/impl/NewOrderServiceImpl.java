package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.dao.NewOrderDao;
import cn.itproject.crm.service.NewOrderService;
import cn.itproject.crm.util.Constant;

@Service("newOrderService")
public class NewOrderServiceImpl implements NewOrderService {

	@Resource
	private NewOrderDao newOrderDao;

	@Override
	public List<Map<String, Object>> getNewOrderList(Integer pageIndex,
			Integer pageSize, Integer[] ids, String keyStr) throws Exception {
		return newOrderDao.getNewOrderList(pageIndex,
				pageSize,Constant.newOrders, ids, keyStr);
	}

	@Override
	public Integer getNewOrderCount(Integer[] ids, String keyStr)
			throws Exception {
		return newOrderDao.getNewOrderCount(Constant.newOrders, ids, keyStr);
	}
}
