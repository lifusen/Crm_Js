package cn.itproject.crm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.LendingInstitution;
import cn.itproject.crm.dao.LendingInstitutionDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.LendingInstitutionService;

@Service
public class LendingInstitutionServiceImpl extends BaseServiceSupport<LendingInstitution> implements LendingInstitutionService{
	@Resource
	private LendingInstitutionDao lendingInstitutionDao;
	@Override
	protected BaseDao<LendingInstitution> getBaseDao() {
		return lendingInstitutionDao;
	}
	@Override
	public Map<String, List<LendingInstitution>> getLendingInstitutionMap()
			throws Exception {
		List<LendingInstitution> lendingInstitutions = lendingInstitutionDao.queryList();
		Map<String, List<LendingInstitution>> map = new HashMap<String, List<LendingInstitution>>();
		String bank = "bank";
		String smallLand = "smallLand";
		String personal = "personal";
		
		for (LendingInstitution lendingInstitution : lendingInstitutions) {
			if (lendingInstitution.getType()==LendingInstitution.BANK) {	//银行
				if (map.containsKey(bank)) {
					map.get(bank).add(lendingInstitution);
				}else {
					List<LendingInstitution> list = new ArrayList<LendingInstitution>();
					list.add(lendingInstitution);
					map.put(bank, list);
				}
			}else if (lendingInstitution.getType()==LendingInstitution.SMALL_LAND) {
				if (map.containsKey(smallLand)) {
					map.get(smallLand).add(lendingInstitution);
				}else {
					List<LendingInstitution> list = new ArrayList<LendingInstitution>();
					list.add(lendingInstitution);
					map.put(smallLand, list);
				}
			}else if (lendingInstitution.getType()==LendingInstitution.PERSONAL) {
				List<LendingInstitution> list = new ArrayList<LendingInstitution>();
				list.add(lendingInstitution);
				map.put(personal, list);
			}
		}
		
		System.out.println(map);
		
		return map;
	}

}
