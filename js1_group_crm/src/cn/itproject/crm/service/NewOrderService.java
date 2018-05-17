package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

public interface NewOrderService {

	/**
	 * 新订单列表
	 * @param pageIndex
	 * @param pageSize
	 * @param ids
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getNewOrderList(Integer pageIndex,
			Integer pageSize, Integer[] ids, String keyStr) throws Exception;

	/**
	 * 新订单数量
	 * @param ids
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	Integer getNewOrderCount(Integer[] ids, String keyStr)throws Exception;

}
