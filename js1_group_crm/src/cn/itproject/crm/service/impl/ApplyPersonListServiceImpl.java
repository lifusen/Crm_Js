package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.ApplyPerson;
import cn.itproject.crm.dao.ApplyPersonListDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.ApplyPersonListService;

@Service("applyPersonListService")
public class ApplyPersonListServiceImpl extends BaseServiceSupport<ApplyPerson> implements ApplyPersonListService {

	@Resource
	private ApplyPersonListDao applyPersonListDao;

	@Override
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex, Integer pageSize, Integer[] eIds,
			String keyStr) throws Exception {
		return applyPersonListDao.getCustomersByStateAndEId(pageIndex, pageSize, eIds, keyStr, null, null);
	}

	@Override
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex, Integer pageSize, Integer[] eIds,
			List<Integer> states, String keyStr, String beginDate, String endDate, String sqlName,
			String failureMessage, Integer fllowType) throws Exception {
		return null;
	}

	@Override
	public Integer getCountByStateAndEId(List<Integer> states, Integer[] eIds, String keys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountByStateAndEId(List<Integer> states, Integer[] eIds, String keys, String beginDate,
			String endDate, String sqlName, String failureMessage, Integer fllowType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> searchList(Integer pageIndex, Integer pageSize, Integer[] dids, Integer[] eids,
			Integer[] statesIds, Integer[] sIds, String[] customerL, String[] loanIds, String beginDate, String endDate,
			String orderKey, String[] follow, String[] wage, String[] unit, Integer[] vIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer searchCount(Integer[] dids, Integer[] eids, Integer[] statesIds, Integer[] sIds, String[] customerL,
			String[] loanTypes, String beginDate, String endDate, String[] follow, String[] wage, String[] unit,
			Integer[] vIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseDao<ApplyPerson> getBaseDao() {
		return applyPersonListDao;
	}

}
