package cn.itproject.crm.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.itproject.crm.bean.ContractManager;
import cn.itproject.crm.dao.ContractManaListDao;
import cn.itproject.crm.db.impl.BaseDaoSupport;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Repository
public class ContractManaListDaoImpl extends BaseDaoSupport<ContractManager> implements ContractManaListDao {

	@Override
	public List<Map<String, Object>> getCustomersByStateAndEId(Integer pageIndex, Integer pageSize, Integer[] eIds,
			String keyStr, String fLevel, String cLevel) throws Exception {
		String sqlName = "getValidContractManagerList";
		return getContractManagerByStateAndEId(pageIndex, pageSize, eIds, keyStr, null, null, sqlName, null, null,
				fLevel, cLevel);
	}

	@Override
	public List<Map<String, Object>> getContractManagerByStateAndEId(Integer pageIndex, Integer pageSize,
			Integer[] eIds, String keyStr, String beginDate, String endDate, String sqlName, String failureMessage,
			Integer fllowType, String fLevel, String cLevel) throws Exception {

		// 获取到SQL命名查询
		Query sqlQuery = getSession().getNamedQuery(sqlName);
		System.out.println(sqlQuery + "1111111111122222222222222");
		// 获取到SQL字符串
		String sql = sqlQuery.getQueryString();
		System.out.println(sql + "11111111111111");

		if (sqlName.equals("getValidContractManagerList") || sqlName.equals("getCustomerReminderListByEmployeeIds")
				|| sqlName.equals("getWarrantReminderListByEmployeeIds")
				|| sqlName.equals("getRejectCustomerListByEmployeeIds") || sqlName.equals("getOutLoanCustomerList")
				|| sqlName.equals("getSignCustomerList")) {
			if (!LoginUserUtil.getUserType().equals(Constant.SUPER_ROLE)
					&& !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)) {
				if (sqlName.equals("getWarrantReminderListByEmployeeIds")) {
					sql = sql + " and s.warrantCompanyId=" + LoginUserUtil.getCompanyId();
				} else if (LoginUserUtil.isWarrantRole()) {
					sql = sql + " and s.warrantCompanyId=" + LoginUserUtil.getCompanyId();
				}

			}
		}
		String superRole = LoginUserUtil.getUserType();
		if (eIds != null && !superRole.equals(Constant.SUPER_ROLE)
				&& !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)) {
			if (sqlName.equals("getWarrantReminderListByEmployeeIds")) {
				sql = sql.replace("#{where}", " and s.warrantEmployeeId in (:employeeIds)");
			} else if (LoginUserUtil.isWarrantRole()) {
				sql = sql.replace("#{where}", " and s.warrantEmployeeId in (:employeeIds)");
			} else {
				sql = sql.replace("#{where}", "c.addPersonId in (:employeeIds)");
			}
		} else if (eIds != null && superRole.equals(Constant.SUPER_ROLE) && LoginUserUtil.getEmployeeId() != eIds[0]) {
			sql = sql.replace("#{where}", " and c.employeeId in (:employeeIds)");
		} else {
			sql = sql.replace("#{where}", " ");
		}
		if (fLevel != null && !"0".equals(fLevel)) {
			sql = sql + " and c.attentionLevel = '" + fLevel + "'";
		}
		if (cLevel != null && !"0".equals(cLevel)) {
			sql = sql + " and c.customerLevel = '" + cLevel + "'";
		}
		if (keyStr != null && !keyStr.equals("")) {
			if (sqlName.equals("getSignCustomerList")) {
				sql = sql.replace("#{whereKey}", " and (c.name like :name or s.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)");
			} else {
				sql = sql.replace("#{whereKey}", " and (c.name like :name or c.loanType like :loanType or c.phone "
						+ "like :phone or c.customerLevel like :customerLevel)");
			}
		} else {
			sql = sql.replace("#{whereKey}", " ");
		}
		if (sqlName.equals("getCustomerReminderListByEmployeeIds")
				|| sqlName.equals("getWarrantReminderListByEmployeeIds")) {
			if (beginDate != null && endDate != null && !beginDate.equals("") && !endDate.equals("")) {
				sql = sql.replace("#{dateScope}", " and c.lastRemindTime>=:beginDate and c.lastRemindTime<=:endDate ");
			} else {
				sql = sql.replace("#{dateScope}", " ");
			}

			if (fllowType != null && fllowType > 0) {
				sql = sql.replace("#{followType}", " and c.lastFollowType=:fllowType ");
			} else {
				sql = sql.replace("#{followType}", " ");
			}
		}

		if (sqlName.equals("getSignCustomerList")) {
			if (beginDate != null && endDate != null && !beginDate.equals("") && !endDate.equals("")) {
				sql = sql.replace("#{dateScope}", " and s.signDate>=:beginDate and s.signDate<=:endDate ");
			} else {
				sql = sql.replace("#{dateScope}", " ");
			}
		}

		if (sqlName.equals("getOutLoanCustomerList")) {
			if (beginDate != null && endDate != null && !beginDate.equals("") && !endDate.equals("")) {
				sql = sql.replace("#{dateScope}", " and o.createTime>=:beginDate and o.createTime<=:endDate ");
			} else {
				sql = sql.replace("#{dateScope}", " ");
			}
		}

		if (sqlName.equals("getRejectCustomerListByEmployeeIds")) {
			if (beginDate != null && endDate != null && !beginDate.equals("") && !endDate.equals("")) {
				sql = sql.replace("#{dateScope}",
						" and s.lastFailureMessageTime>=:beginDate and s.lastFailureMessageTime<=:endDate ");
			} else {
				sql = sql.replace("#{dateScope}", " ");
			}

			if (failureMessage != null && !"".equals(failureMessage)) {
				sql = sql.replace("#{failureMessage}", " and s.lastFailureMessageCause=:lastFailureMessageCause ");
			} else {
				sql = sql.replace("#{failureMessage}", " ");
			}
		}

		if ("getCustomerReminderListByEmployeeIds".equals(sqlName)
				|| sqlName.equals("getWarrantReminderListByEmployeeIds")) {
			sql = sql + " order by c.lastRemindTime";
		} else {
			sql = sql + " order by c.id desc";
		}
		// 创建查询对象
		Query query = getSession().createSQLQuery(sql);
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);

		System.out.println("pageIndex:" + pageIndex);
		System.out.println("pageSize:" + pageSize);

		if (eIds != null && !superRole.equals(Constant.SUPER_ROLE)
				&& !LoginUserUtil.getLoginRoleId().equals(Constant.warrantSuper)) {
			query.setParameterList("employeeIds", eIds);
			System.out.println("employeeIds:" + Arrays.toString(eIds));
		} else if (eIds != null && superRole.equals(Constant.SUPER_ROLE) && LoginUserUtil.getEmployeeId() != eIds[0]) {
			query.setParameterList("employeeIds", eIds);
			System.out.println("employeeIds:" + Arrays.toString(eIds));
		}
		if (keyStr != null && !keyStr.equals("")) {
			query.setString("loanType", "%" + keyStr + "%");
			query.setString("phone", "%" + keyStr + "%");
			query.setString("customerLevel", "%" + keyStr + "%");
			query.setString("name", "%" + keyStr + "%");
			System.out.println("keyStr:" + keyStr);
		}
		if (sqlName.equals("getCustomerReminderListByEmployeeIds") || sqlName.equals("getSignCustomerList")
				|| sqlName.equals("getRejectCustomerListByEmployeeIds") || sqlName.equals("getOutLoanCustomerList")
				|| sqlName.equals("getWarrantReminderListByEmployeeIds")) {
			if (beginDate != null && endDate != null && !beginDate.equals("") && !endDate.equals("")) {
				query.setString("beginDate", beginDate);
				query.setString("endDate", endDate);
				System.out.println("beginDate:" + beginDate);
				System.out.println("endDate:" + endDate);
			}

		}
		if (sqlName.equals("getRejectCustomerListByEmployeeIds")) {
			if (failureMessage != null && !"".equals(failureMessage)) {
				query.setString("lastFailureMessageCause", failureMessage);
				System.out.println("lastFailureMessageCause:" + failureMessage);
			}
		}
		if (sqlName.equals("getCustomerReminderListByEmployeeIds")
				|| sqlName.equals("getWarrantReminderListByEmployeeIds")) {
			if (fllowType != null && fllowType > 0) {
				query.setInteger("fllowType", fllowType);
				System.out.println("fllowType:" + fllowType);
			}
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		return list;

	}

}
