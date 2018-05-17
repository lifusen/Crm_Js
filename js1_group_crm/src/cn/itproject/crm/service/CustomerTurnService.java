package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

public interface CustomerTurnService {

	List<Map<String, Object>> getTurnList(Integer pageIndex, Integer pageSize,
			Integer[] ids, String keyStr,String fLevel,String cLevel) throws Exception;

	Integer getTurnCount(Integer[] ids, String keyStr,String fLevel,String cLevel) throws Exception;

	void doTurn(Integer[] cIds, Integer[] eIds, Integer toEmployeeId,
			String cause, String otherCause) throws Exception;

	List<Integer> getTurnIds(Integer[] ids, String keyStr, String fLevel, String cLevel) throws Exception;

	void batchTurn(List<Integer> cIds, Integer eId, Integer toEmployeeId, String cause, String otherCause) throws Exception;

}
