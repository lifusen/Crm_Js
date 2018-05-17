package cn.itproject.crm.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.support.logging.Log;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.dao.CustomerListDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository("customerListDao")
public class CustomerListDaoImpl extends BaseDaoSupport<Customer> implements CustomerListDao{

	@Override
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex,
			Integer pageSize, Integer[] eIds,List<Integer> states,String keyStr,String fLevel,String cLevel) throws Exception {
		String sqlName = "getValidCustomerList";
		return getCustomersByStateAndEId(pageIndex, pageSize, eIds, states, keyStr,
				null,null,sqlName,null,null,fLevel,cLevel);
	}

	@Override
	public Integer getCountByteStateAndEId(List<Integer> states, Integer[] eIds,String keys,String fLevel,String cLevel)
			throws Exception {
		String sqlName = "getCustomerCountByState";
		return getCountByteStateAndEId(states, eIds, keys,null,null, sqlName,null,null,fLevel,cLevel);
	}

	@Override
	public List<Map<String, Object>> srarchList(String sqlName,Integer pageIndex,
			Integer pageSize,Integer[] dids, Integer[] eids,
			Integer[] statesIds, Integer[] sIds, String[] customerL,
			String[] loanIds, String beginDate, String endDate, String orderKey,
			String[] follow,String[] wage,String[] unit,Integer[] vIds)
			throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		Integer[] companyIds = null;
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}else{
			companyIds = LoginUserUtil.getCompanyIds();
			if (companyIds!=null) {
				sql = sql + " and c.companyId in (:companyIds)";
			}
		}
		
		// 员工
		if(eids!=null && eids.length>0){
			sql = sql.replace("#{where}", " and c.employeeId in (:employeeIds)");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		
		// 部门
		if(dids!=null && dids.length>0){
			sql = sql + " and c.departmentId in (:departmentId)";
		}
		
		// 客户等级
		if (customerL!=null && customerL.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < customerL.length; i++) {
				if (i==customerL.length-1) {
					sql = sql + " c.customerLevel = '"+customerL[i]+"')";
				}else {
					sql = sql + " c.customerLevel = '"+customerL[i]+"' or";
				}
			}
		}
		
		// 贷款类型
		if (loanIds!=null && loanIds.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < loanIds.length; i++) {
				if (i==loanIds.length-1) {
					sql = sql + " c.loanType = '"+loanIds[i]+"')";
				}else {
					sql = sql + " c.loanType = '"+loanIds[i]+"' or";
				}
			}
		}
		// 关注等级
		if (follow!=null && follow.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < follow.length; i++) {
				if (i==follow.length-1) {
					sql = sql + " c.attentionLevel = '"+follow[i]+"')";
				}else {
					sql = sql + " c.attentionLevel = '"+follow[i]+"' or";
				}
			}
		}
		
		// 工资体现
		if (wage!=null && wage.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < wage.length; i++) {
				if (i==wage.length-1) {
					sql = sql + " c.embodiment = '"+wage[i]+"')";
				}else {
					sql = sql + " c.embodiment = '"+wage[i]+"' or";
				}
			}
		}
		
		// 单位性质
		if (unit!=null && unit.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < unit.length; i++) {
				if (i==unit.length-1) {
					sql = sql + " c.enterpriseNature = '"+unit[i]+"')";
				}else {
					sql = sql + " c.enterpriseNature = '"+unit[i]+"' or";
				}
			}
		}
		
		// 是否自留
		if(vIds!=null && vIds.length>0){
			sql = sql + " and c.visibility in (:vIds)";
		}
		
		// 客户来源
		
		if (sIds!=null && sIds.length>0) {
			sql = sql.replace("#{customerSourceId}", "  and cs.id in(:sid) ");
		}else {
			sql = sql.replace("#{customerSourceId}", " ");
		}
		if (beginDate!=null && !beginDate.equals("") && endDate!=null && !endDate.equals("")) {
			sql = sql + " and c.createDate >='"+beginDate+ "' and c.createDate<='"+endDate+"'";
		}
		if(orderKey!=null && !orderKey.equals("")){
			orderKey = orderKey.replace("_", " ");
			sql = sql + " order by "+orderKey;
		}else {
			if("getCustomerReminderListByEmployeeIds".equals(sqlName)){
				sql = sql + " order by c.lastRemindTime";
			}else {
				sql = sql + " order by c.updateTime,c.id desc";
			}
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setParameterList("states", statesIds);
		if (companyIds!=null) {
			query.setParameterList("companyIds", companyIds);
		}
		if(eids!=null && eids.length>0){
			query.setParameterList("employeeIds", eids);
		}
		if(dids!=null && dids.length>0){
			query.setParameterList("departmentId", dids);
		}
		
		if (sIds!=null && sIds.length>0) {
			query.setParameterList("sid", sIds);
		}
		if(vIds!=null && vIds.length>0){
			query.setParameterList("vIds", vIds);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer searchCount(String sqlName,Integer[] dids, Integer[] eids,
			Integer[] statesIds, Integer[] sIds, String[] customerL,
			String[] loanTypes, String beginDate, String endDate,
			String[] follow,String[] wage,String[] unit,Integer[] vIds)
			throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		Integer[] companyIds = null;
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}else{
			companyIds = LoginUserUtil.getCompanyIds();
			if (companyIds!=null) {
				sql = sql + " and c.companyId in (:companyIds)";
			}
		}
		
		// 员工
		if(eids!=null && eids.length>0){
			sql = sql + " and c.employeeId in (:employeeIds)";
		}
		
		// 部门
		if(dids!=null && dids.length>0){
			sql = sql+ " and c.departmentId in (:departmentId)";
		}
		
		// 客户等级
		if (customerL!=null && customerL.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < customerL.length; i++) {
				if (i==customerL.length-1) {
					sql = sql + " c.customerLevel = '"+customerL[i]+"')";
				}else {
					sql = sql + " c.customerLevel = '"+customerL[i]+"' or";
				}
			}
		}
		
		// 贷款类型
		if (loanTypes!=null && loanTypes.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < loanTypes.length; i++) {
				if (i==loanTypes.length-1) {
					sql = sql + " c.loanType = '"+loanTypes[i]+"')";
				}else {
					sql = sql + " c.loanType = '"+loanTypes[i]+"' or";
				}
			}
		}
		
		if (beginDate!=null && !beginDate.equals("") && endDate!=null && !endDate.equals("")) {
			sql = sql + " and c.createDate >='"+beginDate+ "' and c.createDate<='"+endDate+"'";
		}
		
		// 客户来源
		if (sIds!=null && sIds.length>0) {
			sql = sql.replace("#{customerSourceId}", "  and cs.id in(:sid) ");
		}else {
			sql = sql.replace("#{customerSourceId}", " ");
		}
		
		// 关注等级
		if (follow!=null && follow.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < follow.length; i++) {
				if (i==follow.length-1) {
					sql = sql + " c.attentionLevel = '"+follow[i]+"')";
				}else {
					sql = sql + " c.attentionLevel = '"+follow[i]+"' or";
				}
			}
		}
		
		// 工资体现
		if (wage!=null && wage.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < wage.length; i++) {
				if (i==wage.length-1) {
					sql = sql + " c.embodiment = '"+wage[i]+"')";
				}else {
					sql = sql + " c.embodiment = '"+wage[i]+"' or";
				}
			}
		}
		
		// 单位性质
		if (unit!=null && unit.length>0) {
			sql = sql + " and (";
			for (int i = 0; i < unit.length; i++) {
				if (i==unit.length-1) {
					sql = sql + " c.enterpriseNature = '"+unit[i]+"')";
				}else {
					sql = sql + " c.enterpriseNature = '"+unit[i]+"' or";
				}
			}
		}
		
		// 是否自留
		if(vIds!=null && vIds.length>0){
			sql = sql + " and c.visibility in (:vIds)";
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("states", statesIds);
		if (companyIds!=null) {
			query.setParameterList("companyIds", companyIds);
		}
		if(eids!=null && eids.length>0){
			query.setParameterList("employeeIds", eids);
		}
		if(dids!=null && dids.length>0){
			query.setParameterList("departmentId", dids);
		}
		
		if (sIds!=null && sIds.length>0) {
			query.setParameterList("sid", sIds);
		}
		
		if(vIds!=null && vIds.length>0){
			query.setParameterList("vIds", vIds);
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public List<Map<String, Object>> getCustomersByStateAndEId(
			Integer pageIndex, Integer pageSize, Integer[] eIds,
			List<Integer> states, String keyStr,String beginDate,
			String endDate, String sqlName,String failureMessage,Integer fllowType,String fLevel,String cLevel)
			throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if (sqlName.equals("getValidCustomerList") || sqlName.equals("getCustomerReminderListByEmployeeIds") || 
				sqlName.equals("getWarrantReminderListByEmployeeIds")
						|| sqlName.equals("getRejectCustomerListByEmployeeIds") || 
						sqlName.equals("getOutLoanCustomerList") || sqlName.equals("getSignCustomerList")) {
			if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)) {
				if (states!=null && states.size()==1 && states.get(0)==9) {
				}else{
					if (sqlName.equals("getWarrantReminderListByEmployeeIds")) {
						sql = sql + " and s.warrantCompanyId="+LoginUserUtil.getCompanyId();
					}else if(LoginUserUtil.isWarrantRole()){
						sql = sql + " and s.warrantCompanyId="+LoginUserUtil.getCompanyId();
					}else {
						sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
					}
				}
			}
		}
		String superRole = LoginUserUtil.getUserType();
		if(eIds!=null && !superRole.equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)){
			if (sqlName.equals("getWarrantReminderListByEmployeeIds")) {
				sql = sql.replace("#{where}", " and s.warrantEmployeeId in (:employeeIds)");
			}else if(LoginUserUtil.isWarrantRole()){
				sql = sql.replace("#{where}", " and s.warrantEmployeeId in (:employeeIds)");
			}else {
				sql = sql.replace("#{where}", " and c.employeeId in (:employeeIds)");
			}
		}else if(eIds!=null && superRole.equals(Constant.SUPER_ROLE) && LoginUserUtil.getEmployeeId()!=eIds[0]){
			sql = sql.replace("#{where}", " and c.employeeId in (:employeeIds)");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		if (fLevel!=null && !"0".equals(fLevel)) {
			sql = sql+" and c.attentionLevel = '"+fLevel+"'";
		}
		if (cLevel!=null && !"0".equals(cLevel)) {
			sql = sql+" and c.customerLevel = '"+cLevel+"'";
		}
		if (keyStr!=null && !keyStr.equals("")) {
			if (sqlName.equals("getSignCustomerList")) {
				sql = sql.replace("#{whereKey}", " and (c.name like :name or s.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)");
			}else {
				sql = sql.replace("#{whereKey}", " and (c.name like :name or c.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)");
			}
		}else {
			sql = sql.replace("#{whereKey}", " ");
		}
		if (sqlName.equals("getCustomerReminderListByEmployeeIds") || sqlName.equals("getWarrantReminderListByEmployeeIds")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and c.lastRemindTime>=:beginDate and c.lastRemindTime<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
			
			if(fllowType!=null && fllowType>0){
				sql = sql.replace("#{followType}", " and c.lastFollowType=:fllowType ");
			}else {
				sql = sql.replace("#{followType}", " ");
			}
		}
		
		if (sqlName.equals("getSignCustomerList")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and s.signDate>=:beginDate and s.signDate<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
		}
		
		if (sqlName.equals("getOutLoanCustomerList")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and o.createTime>=:beginDate and o.createTime<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
		}
		
		if (sqlName.equals("getRejectCustomerListByEmployeeIds")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and s.lastFailureMessageTime>=:beginDate and s.lastFailureMessageTime<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
			
			
			if(failureMessage!=null && !"".equals(failureMessage)){
				sql = sql.replace("#{failureMessage}", " and s.lastFailureMessageCause=:lastFailureMessageCause ");
			}else {
				sql = sql.replace("#{failureMessage}", " ");
			}
		}
		
		
		if("getCustomerReminderListByEmployeeIds".equals(sqlName) || sqlName.equals("getWarrantReminderListByEmployeeIds")){
			sql = sql + " order by c.lastRemindTime";
		}else {
			sql = sql + " order by c.updateTime,c.id desc";
		}
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		query.setParameterList("states", states);

		System.out.println("pageIndex:"+pageIndex);
		System.out.println("pageSize:"+pageSize);
		System.out.println("states:"+states);
		
		if(eIds!=null&&!superRole.equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)){
			query.setParameterList("employeeIds", eIds);
			System.out.println("employeeIds:"+Arrays.toString(eIds));
		}else if(eIds!=null && superRole.equals(Constant.SUPER_ROLE) && LoginUserUtil.getEmployeeId()!=eIds[0]){
			query.setParameterList("employeeIds", eIds);
			System.out.println("employeeIds:"+Arrays.toString(eIds));
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
			System.out.println("keyStr:"+keyStr);
		}
		if (sqlName.equals("getCustomerReminderListByEmployeeIds") 
				|| sqlName.equals("getSignCustomerList") 
				|| sqlName.equals("getRejectCustomerListByEmployeeIds") 
				|| sqlName.equals("getOutLoanCustomerList") || sqlName.equals("getWarrantReminderListByEmployeeIds")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				query.setString("beginDate", beginDate);
				query.setString("endDate", endDate);
				System.out.println("beginDate:"+beginDate);
				System.out.println("endDate:"+endDate);
			}
			
		}
		if (sqlName.equals("getRejectCustomerListByEmployeeIds")) {
			if(failureMessage!=null && !"".equals(failureMessage)){
				query.setString("lastFailureMessageCause", failureMessage);
				System.out.println("lastFailureMessageCause:"+failureMessage);
			}
		}
		if (sqlName.equals("getCustomerReminderListByEmployeeIds") || sqlName.equals("getWarrantReminderListByEmployeeIds")){
			if(fllowType!=null && fllowType>0){
				query.setInteger("fllowType", fllowType);
				System.out.println("fllowType:"+fllowType);
			}
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list =query.list();
		return list;
	}

	@Override
	public Integer getCountByteStateAndEId(List<Integer> states,
			Integer[] eIds, String keys,String beginDate,
			String endDate, String sqlName,
			String failureMessage,Integer fllowType,String fLevel,String cLevel) throws Exception {
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		
		if (sqlName.equals("getCustomerCountByState")) {
			if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)) {
				if (states!=null && states.size()==1 && states.get(0)==9) {
				}else{
					sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
				}
			}
		}
		
		if (sqlName.equals("getOutLoanCustomerListCount") || sqlName.equals("getSignCustomerListCount") 
				|| sqlName.equals("getCustomerReminderListCountByEmployeeIds") || 
				sqlName.equals("getWarrantReminderListCountByEmployeeIds") || 
				sqlName.equals("getRejectCustomerListCountByEmployees")) {
			if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)) {
				if (sqlName.equals("getWarrantReminderListCountByEmployeeIds")) {
					sql = sql.replace("#{companyId}", " and s.warrantCompanyId="+LoginUserUtil.getCompanyId());
				}else if(LoginUserUtil.isWarrantRole()){
					sql = sql.replace("#{companyId}", " and s.warrantCompanyId="+LoginUserUtil.getCompanyId());
				}else{
					sql = sql.replace("#{companyId}", "  and c.companyId="+LoginUserUtil.getCompanyId());
				}
			}else {
				sql = sql.replace("#{companyId}", " ");
			}
		}
		
		
		
		
		
		
		if (fLevel!=null && !"0".equals(fLevel)) {
			sql = sql+" and c.attentionLevel = '"+fLevel+"'";
		}
		if (cLevel!=null && !"0".equals(cLevel)) {
			sql = sql+" and c.customerLevel = '"+cLevel+"'";
		}
		//后期必须处理逻辑，要知道访问的菜单，角色权限等信息，不能用不明确标志限制逻辑关系
		if(eIds!=null && LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper) 
				&& LoginUserUtil.getEmployeeId()!=eIds[0] && sqlName.equals("getCustomerCountByState")){
			sql = sql.replace("#{where}", " and c.employeeId in (:employeeIds)");
		}else if(eIds!=null && LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper) && LoginUserUtil.getEmployeeId()!=eIds[0] ){
			sql = sql.replace("#{where}", " and s.warrantEmployeeId in (:employeeIds)");
		}else if(LoginUserUtil.isWarrantRole() && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)){
			sql = sql.replace("#{where}", " and s.warrantEmployeeId in (:employeeIds)");
		}else if(eIds!=null && !LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)){
			sql = sql.replace("#{where}", " and c.employeeId in (:employeeIds)");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		
		if (keys!=null && !keys.equals("")) {
			if (sqlName.equals("getSignCustomerListCount")) {
				sql = sql.replace("#{whereKey}", " and (c.name like :name or s.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)");
			}else {
				sql = sql.replace("#{whereKey}", " and (c.name like :name or c.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)");
			}
		}else {
			sql = sql.replace("#{whereKey}", " ");
		}
		
		if (sqlName.equals("getCustomerReminderListCountByEmployeeIds") || 
				sqlName.equals("getWarrantReminderListCountByEmployeeIds")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and c.lastRemindTime>=:beginDate and c.lastRemindTime<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
			
			if(fllowType!=null && fllowType>0){
				sql = sql.replace("#{followType}", " and c.lastFollowType=:fllowType ");
			}else {
				sql = sql.replace("#{followType}", " ");
			}
		}
		if (sqlName.equals("getSignCustomerListCount")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and s.signDate>=:beginDate and s.signDate<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
		}
		if (sqlName.equals("getOutLoanCustomerListCount")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and o.createTime>=:beginDate and o.createTime<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
		}
		if (sqlName.equals("getRejectCustomerListCountByEmployees")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				sql = sql.replace("#{dateScope}", " and s.lastFailureMessageTime>=:beginDate and s.lastFailureMessageTime<=:endDate ");
			}else {
				sql = sql.replace("#{dateScope}", " ");
			}
			
			if(failureMessage!=null && !"".equals(failureMessage)){
				sql = sql.replace("#{failureMessage}", " and s.lastFailureMessageCause=:lastFailureMessageCause ");
			}else {
				sql = sql.replace("#{failureMessage}", " ");
			}
		}
		
		//创建查询对象
		Query query =getSession().createSQLQuery(sql);
		query.setParameterList("states", states);
		
		if(eIds!=null && !LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)){
			query.setParameterList("employeeIds", eIds);
		}else if(eIds!=null && LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE) && LoginUserUtil.getEmployeeId()!=eIds[0]){
			query.setParameterList("employeeIds", eIds);
		}
		if (keys!=null && !keys.equals("")) {
			query.setString("loanType", "%"+keys+"%");
			query.setString("phone", "%"+keys+"%");
			query.setString("customerLevel", "%"+keys+"%");
			query.setString("name", "%"+keys+"%");
		}
		if (sqlName.equals("getCustomerReminderListCountByEmployeeIds") 
				|| sqlName.equals("getSignCustomerListCount") 
				|| sqlName.equals("getRejectCustomerListCountByEmployees") 
				|| sqlName.equals("getOutLoanCustomerListCount") || sqlName.equals("getWarrantReminderListCountByEmployeeIds")) {
			if(beginDate!=null && endDate!=null && !beginDate.equals("") && !endDate.equals("")){
				query.setString("beginDate", beginDate);
				query.setString("endDate", endDate);
			}
		}
		
		if (sqlName.equals("getRejectCustomerListCountByEmployees")) {
			if(failureMessage!=null && !"".equals(failureMessage)){
				query.setString("lastFailureMessageCause", failureMessage);
			}
		}
		if (sqlName.equals("getCustomerReminderListCountByEmployeeIds") || 
				sqlName.equals("getWarrantReminderListCountByEmployeeIds")) {
			if(fllowType!=null && fllowType>0){
				query.setInteger("fllowType", fllowType);
			}
		}
		Object count = query.uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	@Override
	public List<Integer> getIdsByteStateAndEId(List<Integer> states, Integer[] ids, String keyStr, String fLevel,
			String cLevel) throws Exception{
		//获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery("getIdsByteStateAndEId");
		//获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		if (fLevel!=null && !"0".equals(fLevel)) {
			sql = sql+" and c.attentionLevel = '"+fLevel+"'";
		}
		if (cLevel!=null && !"0".equals(cLevel)) {
			sql = sql+" and c.customerLevel = '"+cLevel+"'";
		}
		if(ids!=null){
			sql = sql.replace("#{where}", " and c.employeeId in (:employeeIds)");
		}else {
			sql = sql.replace("#{where}", " ");
		}
		
		if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)) {
			sql = sql + " and c.companyId="+LoginUserUtil.getCompanyId();
		}
		
		if (keyStr!=null && !keyStr.equals("")) {
			sql = sql.replace("#{whereKey}", " and (c.name like :name or c.loanType like :loanType or c.phone "
					+ "like :phone or c.customerLevel like :customerLevel)");
		}else {
			sql = sql.replace("#{whereKey}", " ");
		}
		
		//创建查询对象
		
		Query query =getSession().createSQLQuery(sql).addScalar("id", StandardBasicTypes.INTEGER);
		query.setParameterList("states", states);
		
		if(ids!=null){
			query.setParameterList("employeeIds", ids);
		}
		if (keyStr!=null && !keyStr.equals("")) {
			query.setString("loanType", "%"+keyStr+"%");
			query.setString("phone", "%"+keyStr+"%");
			query.setString("customerLevel", "%"+keyStr+"%");
			query.setString("name", "%"+keyStr+"%");
		}
		return query.list();
	}
}
