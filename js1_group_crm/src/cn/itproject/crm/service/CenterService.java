package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.controller.viewbean.WarrantOrderQuery;

public interface CenterService {

	List<Map<String, Object>> orderList(WarrantOrderQuery query)throws Exception;

	Integer getOrderCount(WarrantOrderQuery query) throws Exception;

}
