package cn.itproject.crm.dao;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.ApplyPerson;
import cn.itproject.crm.db.BaseDao;

public interface ApplyPersonListDao extends BaseDao<ApplyPerson> {
	/**
	 * 获取面试人员列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param eIds
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex, Integer pageSize, Integer[] eIds,
			String keyStr, String fLevel, String cLevel) throws Exception;

	public List<Map<String, Object>> getApplyPersonByStateAndEId(Integer pageIndex, Integer pageSize, Integer[] eIds,
			String keyStr, String beginDate, String endDate, String sqlName, String failureMessage, Integer fllowType,
			String fLevel, String cLevel) throws Exception;

}
