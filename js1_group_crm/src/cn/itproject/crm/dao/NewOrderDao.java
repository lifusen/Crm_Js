package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

public interface NewOrderDao {

	List<Map<String, Object>> getNewOrderList(Integer pageIndex,
			Integer pageSize, List<Integer> neworders, Integer[] ids,
			String keyStr) throws Exception;

	Integer getNewOrderCount(List<Integer> neworders, Integer[] ids,
			String keyStr)throws Exception;

}
