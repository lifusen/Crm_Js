package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;
import cn.itproject.crm.db.BaseDao;

public interface CenterDao extends BaseDao<Customer>{

	List<Map<String, Object>> orderList(WarrantOrderQuery query) throws Exception;

	Integer getOrderCount(WarrantOrderQuery query) throws Exception;
}