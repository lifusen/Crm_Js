package cn.itproject.crm.service.impl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.FailureMessage;
import cn.itproject.crm.bean.OutLoanCustomer;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.bean.SignCustomer.SignCustomerStatus;
import cn.itproject.crm.controller.viewbean.CertificateViewBean;
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.dao.CustomerListDao;
import cn.itproject.crm.dao.OutLoanCustomerDao;
import cn.itproject.crm.dao.SignCustomerDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.FailureMessageService;
import cn.itproject.crm.service.LoaningService;
import cn.itproject.crm.service.SignCustomerService;

@Service
public class SignCustomerServiceImpl extends BaseServiceSupport<SignCustomer> implements SignCustomerService {
	@Resource
	private SignCustomerDao signCustomerDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private FailureMessageService failureMessageService;

	@Resource
	private LoaningService loaningService;

	@Resource
	private OutLoanCustomerDao outLoanCustomerDao;

	@Resource
	private CustomerListDao customerListDao;

	@Resource
	private CustomerFollowService customerFollowService;

	@Override
	protected BaseDao<SignCustomer> getBaseDao() {
		return signCustomerDao;
	}

	@Override
	public void addSignCustomer(SignCustomer signCustomer, Employee employee) throws Exception {

		// 处理抵押物
		signCustomer.setCollateralString(convertCollateralToJSONString(signCustomer.getCollaterals()));

		// 处理证件
		signCustomer.setCertificateString(convertCertificateToJSONString(signCustomer.getCertificates()));

		// 添加签单客户
		Serializable id = signCustomerDao.save(signCustomer);
		System.out.println(id);
		// 生成合同号
		String contractNO = null;
		String contractNO2 = null;
		String contractNO3 = null;
		String contractNO4 = null;
		String contractNO5 = null;
		String contractNO6 = null;
		String contractNO7 = null;
		String contractNO8 = null;
		String contractNO9 = null;
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		contractNO = "JS" + date.format(now) + "A";
		if (signCustomerDao.getSignCustomerByContactNo(contractNO) == null) {
			signCustomer.setContractNO(contractNO);
		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO) == null) {
			contractNO2 = "JS" + date.format(now) + "B";
			signCustomer.setContractNO(contractNO2);
		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO2) == null) {
			contractNO3 = "JS" + date.format(now) + "C";
			signCustomer.setContractNO(contractNO3);
		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO3) == null) {
			contractNO4 = "JS" + date.format(now) + "D";
			signCustomer.setContractNO(contractNO4);

		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO4) == null) {
			contractNO4 = "JS" + date.format(now) + "E";
			signCustomer.setContractNO(contractNO5);

		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO5) == null) {
			contractNO4 = "JS" + date.format(now) + "F";
			signCustomer.setContractNO(contractNO6);

		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO6) == null) {
			contractNO4 = "JS" + date.format(now) + "G";
			signCustomer.setContractNO(contractNO7);

		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO7) == null) {
			contractNO4 = "JS" + date.format(now) + "H";
			signCustomer.setContractNO(contractNO8);

		}
		if (signCustomerDao.getSignCustomerByContactNo(contractNO8) == null) {
			contractNO4 = "JS" + date.format(now) + "I";
			signCustomer.setContractNO(contractNO9);

		}

		// 设置贷款类型
		signCustomer.setLoanType(signCustomer.getLoanProducts().get(0).getLoanType());

		// 客户ID
		Integer customerId = signCustomer.getCustomer().getId();

		// 如果是第一次签单,则修改客户的签单状态为:已签单
		if ("未签单".equals(customerDao.getSignState(customerId))) {
			customerDao.updateSignState(customerId, "已签单");
			// 修改客户的状态
			customerDao.updateState(customerId, CustomerState.sign.ordinal());
		}

		// 添加默认的跟进记录,类型为:成功签约
		customerFollowService.addDefaultSignCustomerFollow(customerId, employee.getId());

	}

	@Override
	public List<Map<String, Object>> getSignCustomerList(Integer pageIndex, Integer pageSize, List<Integer> employeeIds,
			String keyword, List<Integer> states, String beginDate, String endDate) throws Exception {
		Integer[] es = null;
		if (employeeIds != null) {
			es = (Integer[]) employeeIds.toArray(new Integer[employeeIds.size()]);
		}
		return customerListDao.getCustomersByStateAndEId(pageIndex, pageSize, es, states, keyword, beginDate, endDate,
				"getSignCustomerList", null, null, null, null);
	}

	@Override
	public Integer getSignCustomerListCount(List<Integer> employeeIds, String keyword, List<Integer> states,
			String beginDate, String endDate) throws Exception {
		Integer[] es = null;
		if (employeeIds != null) {
			es = (Integer[]) employeeIds.toArray(new Integer[employeeIds.size()]);
		}
		return customerListDao.getCountByteStateAndEId(states, es, keyword, beginDate, endDate,
				"getSignCustomerListCount", null, null, null, null);
	}

	@Override
	public List<SignCustomer> getSignCustomerByCustomerId(Integer customerId) throws Exception {
		String hql = "from SignCustomer s where s.customer.id = ? order by s.contractNO desc";
		List<SignCustomer> signCustomers = signCustomerDao.getList(hql, customerId);

		for (SignCustomer signCustomer : signCustomers) {
			// 获取到抵押物的JSON字符串
			String string = signCustomer.getCollateralString();
			String certificateString = signCustomer.getCertificateString();
			if (string != null && !string.trim().equals("")) {
				// 将JSON字符串转换为抵押物视图Bean集合
				signCustomer.setCollateralViewBeans(
						JSON.parseArray(signCustomer.getCollateralString(), CollateralViewBean.class));
			}
			if (certificateString != null && !certificateString.trim().equals("")) {
				// 将JSON字符串转换为证件视图Bean集合
				signCustomer.setCertificateViewBeans(
						JSON.parseArray(signCustomer.getCertificateString(), CertificateViewBean.class));
			}
		}
		return signCustomers;
	}

	@Override
	public void updateSignCustomer(SignCustomer signCustomer, Employee loginEmployee) throws Exception {
		// 处理抵押物
		signCustomer.setCollateralString(convertCollateralToJSONString(signCustomer.getCollaterals()));
		// 处理证件
		signCustomer.setCertificateString(convertCertificateToJSONString(signCustomer.getCertificates()));

		// 获取修改对象
		SignCustomer updateSignCustomer = signCustomerDao.get(signCustomer.getId());
		// 修改
		updateSignCustomer.setSignContacts(signCustomer.getSignContacts()); // 签单联系人
		updateSignCustomer.setCustomerStatus(signCustomer.getCustomerStatus()); // 客户状态
		// updateSignCustomer.setOutLoanCondition(signCustomer.getOutLoanCondition());
		// //放款情况
		// updateSignCustomer.setHasLoanWish(signCustomer.getHasLoanWish());
		// //是否再贷
		updateSignCustomer.setPlanNextLoanTime(signCustomer.getPlanNextLoanTime()); // 预计时间
		updateSignCustomer.setCostOfContract(signCustomer.getCostOfContract()); // 合同费用
		updateSignCustomer.setLoanAmount(signCustomer.getLoanAmount()); // 贷款金额
		updateSignCustomer.setHandsel(signCustomer.getHandsel()); // 已收定金
		updateSignCustomer.setNegotiatorId(signCustomer.getNegotiatorId()); // 谈判师
		// updateSignCustomer.setReceivedCertificate(signCustomer.getReceivedCertificate());
		// //已收客户证件
		updateSignCustomer.setLoanProducts(signCustomer.getLoanProducts()); // 贷款产品
		updateSignCustomer.setRemark(signCustomer.getRemark()); // 备注
		// updateSignCustomer.setRepaymentType(signCustomer.getRepaymentType());
		// //还款方式
		// updateSignCustomer.setCollateralString(signCustomer.getCollateralString());
		// //抵押物JSON字符串
		// updateSignCustomer.setCertificateString(signCustomer.getCertificateString());
		// //证件JSON字符串
		// updateSignCustomer.setLoanType(signCustomer.getLoanProducts().get(0).getLoanType());
		// //设置贷款类型
		updateSignCustomer.setUpdateTime(signCustomer.getUpdateTime()); // 修改时间

		// -------------------------------------------- 垫资消息 start
		// --------------------------------------------
		// updateSignCustomer.setHasLoaning(signCustomer.getHasLoaning());
		// //是否垫资
		// updateSignCustomer.setLoaningAmount(signCustomer.getLoaningAmount());
		// //垫资金额
		// updateSignCustomer.setLoaningRate(signCustomer.getLoaningRate());
		// //垫资费率
		// updateSignCustomer.setLoaningDate(signCustomer.getLoaningDate());
		// //垫资时间
		// updateSignCustomer.setLoaningCycle(signCustomer.getLoaningCycle());
		// //垫资周期
		// updateSignCustomer.setLoaningType(signCustomer.getLoaningType());
		// //是否在我公司做单
		// updateSignCustomer.setLoaningRisk(signCustomer.getLoaningRisk());
		// //预估风险
		//
		// -------------------------------------------- 垫资消息 end
		// ----------------------------------------------

		// 修改客户的状态
		Integer customerId = signCustomer.getCustomer().getId();
		if (signCustomer.getCustomerStatus() == SignCustomerStatus.underway.ordinal()) { // 进行中
			customerDao.updateState(customerId, CustomerState.sign.ordinal());
		} else if (signCustomer.getCustomerStatus() == SignCustomerStatus.bankChargeback.ordinal()) { // 银行退件单
			customerDao.updateState(customerId, CustomerState.chargeback.ordinal());
		} else if (signCustomer.getCustomerStatus() == SignCustomerStatus.warrantReject.ordinal()) { // 权证退件
			customerDao.updateState(customerId, CustomerState.reject.ordinal());
		}

		// 记录最后一次的退单退件消息
		FailureMessage failureMessage = signCustomer.getFailureMessage();
		if (failureMessage != null) {
			Date date = failureMessage.getTime();
			if (date == null) {
				failureMessage.setTime(new Date());
			}
			updateSignCustomer.setLastFailureMessageTime(failureMessage.getTime()); // 时间
			updateSignCustomer.setLastFailureMessageCause(failureMessage.getCause()); // 原因
			updateSignCustomer.setLastFailureMessageType(failureMessage.getType()); // 类型
		}

		// 修改蜀创三中心中介信息
		updateSignCustomer.setIntermediaryName(signCustomer.getIntermediaryName());
		; // 中介名称
		updateSignCustomer.setIntermediaryPhone(signCustomer.getIntermediaryPhone()); // 中介电话
		updateSignCustomer.setIntermediaryRebate(signCustomer.getIntermediaryRebate()); // 中介返点

		signCustomerDao.update(updateSignCustomer);

		// 添加失败消息
		if (failureMessage != null) {
			failureMessage.setSignCustomer(updateSignCustomer);
			failureMessageService.addEntity(failureMessage);
		}
		// 设置客户最后修改的时间
		customerDao.updateTime(customerId);

	}

	/**
	 * 将抵押物的字符串集合转换为JSON字符串
	 * 
	 * @param collaterals
	 *            抵押物的字符串集合
	 * @return JSON字符串
	 */
	public String convertCollateralToJSONString(List<String> collaterals) {
		if (collaterals == null) {
			return null;
		}
		// 创建抵押物视图Bean集合
		List<CollateralViewBean> cvbs = new ArrayList<CollateralViewBean>();
		CollateralViewBean cvb = null;
		// 将抵押物字符串转换为抵押物视图Bean集合
		for (String string : collaterals) {
			if (string != null && !string.trim().equals("")) {
				String[] strings = string.split("-");
				cvb = new CollateralViewBean(Integer.parseInt(strings[0]), strings[1]);
				cvbs.add(cvb);
			}
		}
		System.out.println(cvbs);
		return JSON.toJSONString(cvbs);
	}

	/**
	 * 将证件的字符串集合转换成JSON字符串
	 * 
	 * @return JSON字符串
	 */
	public String convertCertificateToJSONString(List<String> certificates) {
		if (certificates == null) {
			return null;
		}
		// 创建证件的视图Bean集合
		List<CertificateViewBean> certificateViewBeans = new ArrayList<CertificateViewBean>();
		CertificateViewBean certificateViewBean = null;
		for (String string : certificates) {
			if (string != null && !string.trim().equals("")) {
				String[] strings = string.split("-");
				certificateViewBean = new CertificateViewBean(Integer.parseInt(strings[0]), strings[1], strings[2],
						strings[3]);
				certificateViewBeans.add(certificateViewBean);
			}
		}
		System.out.println(certificateViewBeans);
		return JSON.toJSONString(certificateViewBeans);
	}

	@Override
	public List<Integer> getSignCustomerIdsByCId(Integer cId) throws Exception {
		return signCustomerDao.getSignCustomerIdsByCId(cId);
	}

	@Deprecated
	@Override
	public SignCustomer getSignCustomerByCustomerId(Integer pageIndex, Integer pageSize, Integer customerId)
			throws Exception {
		String hql = "from SignCustomer s where s.customer.id = ? order by s.id asc";
		// 获取分页列表
		List<SignCustomer> signCustomers = signCustomerDao.getList(pageIndex, pageSize, hql, customerId);
		SignCustomer signCustomer = null;
		if (signCustomers != null && signCustomers.size() > 0) {
			signCustomer = signCustomers.get(0);
			// 获取到抵押物的JSON字符串
			String string = signCustomer.getCollateralString();
			// 获取到证件的JSON字符串
			String certificateString = signCustomer.getCertificateString();
			if (string != null && !string.trim().equals("")) {
				// 将JSON字符串转换为抵押物视图Bean集合
				signCustomer.setCollateralViewBeans(
						JSON.parseArray(signCustomer.getCollateralString(), CollateralViewBean.class));
			}
			if (certificateString != null && !certificateString.trim().equals("")) {
				// 将JSON字符串转换为证件视图Bean集合
				signCustomer.setCertificateViewBeans(
						JSON.parseArray(signCustomer.getCertificateString(), CertificateViewBean.class));
			}

			// 获取失败消息
			List<FailureMessage> failureMessages = failureMessageService
					.getFailureMessageBySignCustomerId(signCustomer.getId());
			signCustomer.setFailureMessages(failureMessages);
			/*
			 * if ("已放款".equals(signCustomer.getOutLoanCondition())) {
			 * //获取签单客户对象 OutLoanCustomer outLoanCustomer =
			 * outLoanCustomerDao.getOutloanCustomerBySId(signCustomer.getId());
			 * //获取到抵押物的JSON字符串 string = outLoanCustomer.getCollateralString();
			 * //获取到证件的JSON字符串 certificateString =
			 * outLoanCustomer.getCertificateString(); if
			 * (string!=null&&!string.trim().equals("")) {
			 * //将JSON字符串转换为抵押物视图Bean集合
			 * outLoanCustomer.setCollateralViewBeans(JSON.parseArray(
			 * outLoanCustomer.getCollateralString(),CollateralViewBean.class));
			 * } if
			 * (certificateString!=null&&!certificateString.trim().equals("")) {
			 * //将JSON字符串转换为证件视图Bean集合
			 * outLoanCustomer.setCertificateViewBeans(JSON.parseArray(
			 * outLoanCustomer.getCertificateString(),
			 * CertificateViewBean.class)); } //设置放款客户
			 * signCustomer.setOutLoanCustomer(outLoanCustomer); }
			 */
		}
		return signCustomer;
	}

	@Override
	public SignCustomer getSignCustomer(Integer customerId, Integer signCustomerId) throws Exception {
		SignCustomer signCustomer = signCustomerDao.getSignCustomer(customerId, signCustomerId);
		// 获取到抵押物的JSON字符串
		String string = signCustomer.getCollateralString();
		// 获取到证件的JSON字符串
		String certificateString = signCustomer.getCertificateString();
		if (string != null && !string.trim().equals("")) {
			// 将JSON字符串转换为抵押物视图Bean集合
			signCustomer.setCollateralViewBeans(
					JSON.parseArray(signCustomer.getCollateralString(), CollateralViewBean.class));
		}
		if (certificateString != null && !certificateString.trim().equals("")) {
			// 将JSON字符串转换为证件视图Bean集合
			signCustomer.setCertificateViewBeans(
					JSON.parseArray(signCustomer.getCertificateString(), CertificateViewBean.class));
		}

		// 获取失败消息
		List<FailureMessage> failureMessages = failureMessageService
				.getFailureMessageBySignCustomerId(signCustomer.getId());
		signCustomer.setFailureMessages(failureMessages);
		/*
		 * 2017.1.3 by SwordLiu 因放款改为many to one，加载签单，不再同步加载放款信息，改由页面按钮请求对应放款信息
		 * if ("已放款".equals(signCustomer.getOutLoanCondition())) { //获取签单客户对象
		 * OutLoanCustomer outLoanCustomer =
		 * outLoanCustomerDao.getOutloanCustomerBySId(signCustomer.getId());
		 * //获取到抵押物的JSON字符串 string = outLoanCustomer.getCollateralString();
		 * //获取到证件的JSON字符串 certificateString =
		 * outLoanCustomer.getCertificateString(); if
		 * (string!=null&&!string.trim().equals("")) { //将JSON字符串转换为抵押物视图Bean集合
		 * outLoanCustomer.setCollateralViewBeans(JSON.parseArray(
		 * outLoanCustomer.getCollateralString(),CollateralViewBean.class)); }
		 * if (certificateString!=null&&!certificateString.trim().equals("")) {
		 * //将JSON字符串转换为证件视图Bean集合
		 * outLoanCustomer.setCertificateViewBeans(JSON.parseArray(
		 * outLoanCustomer.getCertificateString(), CertificateViewBean.class));
		 * } //设置放款客户 signCustomer.setOutLoanCustomer(outLoanCustomer); }
		 */
		return signCustomer;
	}

	@Override
	public void updateOutLoanCondition(Integer id, String string) throws Exception {
		String hql = "update SignCustomer set outLoanCondition = ?,orderType=3 where id = ?";
		signCustomerDao.update(hql, string, id);
	}

	@Override
	public List<Map<String, Object>> getMaintainList(Integer pageIndex, Integer pageSize, String keyword)
			throws Exception {
		return signCustomerDao.getMaintainList(pageIndex, pageSize, keyword);
	}

	@Override
	public Integer getMaintainCount(String keyword) throws Exception {
		return signCustomerDao.getMaintainCount(keyword);
	}

	@Override
	public void evaluate(String level, Integer sId) throws Exception {
		SignCustomer signCustomer = signCustomerDao.get(sId);
		signCustomer.setServeRating(level);
		signCustomerDao.update(signCustomer);
	}

	@Override
	public void updateWarrant(Integer[] idArray, Integer companyId, Integer dId, Integer eId) throws Exception {
		signCustomerDao.updateWarrant(idArray, companyId, dId, eId);
	}

	@Override
	public List<Map<String, Object>> getSignCustomerSummaryInfo(Integer customerId) throws Exception {
		return signCustomerDao.getSignCustomerSummaryInfo(customerId);
	}

}
