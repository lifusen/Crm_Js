package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;

public interface WarrantDepartmentDao {

	List<Map<String, Object>> orderList(WarrantOrderQuery query) throws Exception;

	Integer getOrderCount(WarrantOrderQuery query) throws Exception;

}
