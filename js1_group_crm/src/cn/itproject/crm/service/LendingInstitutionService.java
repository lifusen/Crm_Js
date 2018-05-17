package cn.itproject.crm.service;

import java.util.List;
import java.util.Map;

import cn.itproject.crm.bean.LendingInstitution;

/**
 * 贷款机构业务接口
 * @author MrLiu
 *
 */
public interface LendingInstitutionService extends BaseService<LendingInstitution>{

	Map<String, List<LendingInstitution>> getLendingInstitutionMap() throws Exception;
}
