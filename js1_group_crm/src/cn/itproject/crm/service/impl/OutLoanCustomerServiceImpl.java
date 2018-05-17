package cn.itproject.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.FailureMessage;
import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.bean.OutLoanCustomer;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.controller.viewbean.CertificateViewBean;
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.dao.CustomerListDao;
import cn.itproject.crm.dao.OutLoanBelongDao;
import cn.itproject.crm.dao.OutLoanCustomerDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.db.utils.OrderByType;
import cn.itproject.crm.service.FailureMessageService;
import cn.itproject.crm.service.LoaningService;
import cn.itproject.crm.service.OutLoanCustomerService;
import cn.itproject.crm.service.SignCustomerService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.LoginUserUtil;

@Service
public class OutLoanCustomerServiceImpl extends BaseServiceSupport<OutLoanCustomer> implements OutLoanCustomerService{
	@Resource
	private OutLoanCustomerDao outLoanCustomerDao;
	
	@Resource
	private CustomerDao customerDao;
	
	@Resource
	private FailureMessageService failureMessageService;
	
	@Resource
	private LoaningService loaningService;

	@Resource
	private SignCustomerService signCustomerService;
	@Resource
	private CustomerListDao customerListDao;
	@Resource
	private OutLoanBelongDao outLoanBelongDao;
	
	@Override
	protected BaseDao<OutLoanCustomer> getBaseDao() {
		return outLoanCustomerDao;
	}

	@Override
	public void addOutLoanCustomer(OutLoanCustomer outLoanCustomer,
			Employee employee, Integer warranterId) throws Exception {

		//处理抵押物
		outLoanCustomer.setCollateralString(signCustomerService.convertCollateralToJSONString(outLoanCustomer.getCollaterals()));
		
		//处理证件
		outLoanCustomer.setCertificateString(signCustomerService.convertCertificateToJSONString(outLoanCustomer.getCertificates()));
		
		//添加放款客户
		outLoanCustomerDao.save(outLoanCustomer);
		
		//修改签单客户的放款情况为已放款
		signCustomerService.updateOutLoanCondition(outLoanCustomer.getSignCustomer().getId(),"已放款");
		
		//修改签单-权证关系的状态为放款：默认0未放款，1放款
		Integer result = outLoanBelongDao.updateState(outLoanCustomer.getSignCustomer().getId(), warranterId);
		
		if(result!=1){
			outLoanBelongDao.save(new OutLoanBelong(outLoanCustomer.getSignCustomer().getId(), warranterId, employee.getDepartment().getId(), employee.getCompanyId(), 1, 1));
		}
		
		//customerDao.updateState(outLoanCustomer.getSignCustomer().getCustomer().getId(), CustomerState.outLoan.ordinal());
		
	}

	@Override
	public List<Map<String, Object>> getOutLoanCustomerList(Integer pageIndex,
			Integer pageSize, List<Integer> employeeIds, String keyword,String beginDate,String endDate)
			throws Exception {
		Integer[] es = null;
		if (employeeIds!=null) {
			es = (Integer[]) employeeIds.toArray(new Integer[employeeIds.size()]);
		}
		return customerListDao.getCustomersByStateAndEId(pageIndex, pageSize, es, Constant.outLoanList, keyword,
				beginDate,endDate, "getOutLoanCustomerList",null,null,null,null);
	}

	@Override
	public Integer getOutLoanCustomerListCount(List<Integer> employeeIds,
			String keyword,String beginDate,String endDate) throws Exception {
		Integer[] es = null;
		if (employeeIds!=null) {
			es = (Integer[]) employeeIds.toArray(new Integer[employeeIds.size()]);
		}
		return customerListDao.getCountByteStateAndEId(Constant.outLoanList, es, keyword,beginDate,endDate, 
				"getOutLoanCustomerListCount",null,null,null,null);
	}

	@Override
	public List<OutLoanCustomer> getOutLoanCustomerByCustomerId(
			Integer customerId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOutLoanCustomer(OutLoanCustomer outLoanCustomer,
			Employee loginEmployee,Integer customerId) throws Exception {
		//处理抵押物
		outLoanCustomer.setCollateralString(signCustomerService.convertCollateralToJSONString(outLoanCustomer.getCollaterals()));
		//处理证件
		outLoanCustomer.setCertificateString(signCustomerService.convertCertificateToJSONString(outLoanCustomer.getCertificates()));
		
		//获取修改对象
		OutLoanCustomer updateOutLoanCustomer = outLoanCustomerDao.get(outLoanCustomer.getId());
		//修改
		updateOutLoanCustomer.setName(outLoanCustomer.getName()); 									//客户姓名
		updateOutLoanCustomer.setLoanAmount(outLoanCustomer.getLoanAmount()); 						//贷款金额
		updateOutLoanCustomer.setHandleTime(outLoanCustomer.getHandleTime()); 						//办理时间
		updateOutLoanCustomer.setHasCertificate(outLoanCustomer.getHasCertificate()); 				//是否压证件
		updateOutLoanCustomer.setHasFullDelegate(outLoanCustomer.getHasFullDelegate()); 			//是否办全委托
		updateOutLoanCustomer.setWarrantName(outLoanCustomer.getWarrantName()); 					//权证人员
		updateOutLoanCustomer.setRealPrice(outLoanCustomer.getRealPrice()); 						//实收费用
		updateOutLoanCustomer.setReceivedDeposit(outLoanCustomer.getReceivedDeposit()); 			//已收定金
		updateOutLoanCustomer.setCost(outLoanCustomer.getCost()); 									//后台成本
		updateOutLoanCustomer.setNetEarnings(outLoanCustomer.getNetEarnings()); 					//净业绩
		updateOutLoanCustomer.setLoanProducts(outLoanCustomer.getLoanProducts()); 					//贷款产品
		updateOutLoanCustomer.setPaymentAmount(outLoanCustomer.getPaymentAmount()); 				//还款金额
		updateOutLoanCustomer.setPaymenttime(outLoanCustomer.getPaymenttime()); 					//还款时间
		updateOutLoanCustomer.setRepaymentType(outLoanCustomer.getRepaymentType()); 				//还款方式
		updateOutLoanCustomer.setRemark(outLoanCustomer.getRemark());  								//备注
		updateOutLoanCustomer.setCollateralString(outLoanCustomer.getCollateralString()); 			//抵押物JSON字符串
		updateOutLoanCustomer.setCertificateString(outLoanCustomer.getCertificateString()); 		//证件JSON字符串
		updateOutLoanCustomer.setLoanType(outLoanCustomer.getLoanProducts().get(0).getLoanType()); 	//放款类型
		//---------2017.1.3 新增   by SwordLiu   start-----------------//
		updateOutLoanCustomer.setOutLoanNum(outLoanCustomer.getOutLoanNum());						//放款编号
		updateOutLoanCustomer.setApproveTime(outLoanCustomer.getApproveTime());						//审过时间
		updateOutLoanCustomer.setAssessCompany(outLoanCustomer.getAssessCompany());					//评估公司
		updateOutLoanCustomer.setAssessCost(outLoanCustomer.getAssessCost());						//评估费用
		updateOutLoanCustomer.setAssessMoney(outLoanCustomer.getAssessMoney());						//评估金额
		updateOutLoanCustomer.setPledgeCost(outLoanCustomer.getPledgeCost());						//抵押费用
		updateOutLoanCustomer.setContractNotarizationCharge(outLoanCustomer.getContractNotarizationCharge());	//合同公证费
		updateOutLoanCustomer.setAloneNotarizationCharge(outLoanCustomer.getAloneNotarizationCharge());			//单身公证费
		updateOutLoanCustomer.setEntrustNotarizationCharge(outLoanCustomer.getEntrustNotarizationCharge());		//委托公证费
		updateOutLoanCustomer.setPurpose(outLoanCustomer.getPurpose());								//用途
		updateOutLoanCustomer.setVisitCharge(outLoanCustomer.getVisitCharge());						//上门费
		updateOutLoanCustomer.setPostage(outLoanCustomer.getPostage());								//快递费
		updateOutLoanCustomer.setOtherCost(outLoanCustomer.getOtherCost());							//其他费
		updateOutLoanCustomer.setRebate(outLoanCustomer.getRebate());								//返点
		
		updateOutLoanCustomer.setChannelCost(outLoanCustomer.getChannelCost());						//渠道成本
		updateOutLoanCustomer.setCreditCost(outLoanCustomer.getCreditCost());						//征信费
		updateOutLoanCustomer.setElectricCost(outLoanCustomer.getElectricCost());					//电票
		updateOutLoanCustomer.setWorkProve(outLoanCustomer.getWorkProve());							//工作证明
		updateOutLoanCustomer.setCheckCost(outLoanCustomer.getCheckCost());							//面签
		updateOutLoanCustomer.setTelephoneCost(outLoanCustomer.getTelephoneCost());					//座机费
		updateOutLoanCustomer.setPhoneCard(outLoanCustomer.getPhoneCard());							//电话卡
		updateOutLoanCustomer.setOnlyHandle(outLoanCustomer.getOnlyHandle());						//纯处理
		updateOutLoanCustomer.setHouseAge(outLoanCustomer.getHouseAge());							//房龄
		updateOutLoanCustomer.setInsuranceCost(outLoanCustomer.getInsuranceCost());					//保险费
		
		//---------2017.1.3 新增   by SwordLiu   end  -----------------//
		
		//-------------------------------------------- 垫资消息 start --------------------------------------------  
		updateOutLoanCustomer.setHasLoaning(outLoanCustomer.getHasLoaning()); 						//是否垫资
		updateOutLoanCustomer.setLoaningAmount(outLoanCustomer.getLoaningAmount()); 				//垫资金额
		updateOutLoanCustomer.setLoaningRate(outLoanCustomer.getLoaningRate()); 					//垫资费率
		updateOutLoanCustomer.setLoaningDate(outLoanCustomer.getLoaningDate()); 					//垫资时间
		updateOutLoanCustomer.setLoaningCycle(outLoanCustomer.getLoaningCycle()); 					//垫资周期
		updateOutLoanCustomer.setLoaningType(outLoanCustomer.getLoaningType()); 					//是否在我公司做单
		updateOutLoanCustomer.setLoaningRisk(outLoanCustomer.getLoaningRisk()); 					//预估风险
		
		//-------------------------------------------- 垫资消息 end ----------------------------------------------  
		
		outLoanCustomerDao.update(updateOutLoanCustomer);
		
		System.out.println(customerId);
		//设置客户最后一次修改时间
		customerDao.updateTime(customerId);
	}

	@Override
	public List<Integer> getOutLoanCustomerIdsByCId(Integer cId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OutLoanCustomer> getOutLoanCustomerByCustomerId(
			Integer pageIndex, Integer pageSize, Integer customerId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getOutLoanCustomerSummaryInfo(Integer signCustomerId) throws Exception {
		return outLoanCustomerDao.getOutLoanCustomerSummaryInfo(signCustomerId);
	}

	@Override
	public OutLoanCustomer getOutLoanCustomerById(Integer oId) throws Exception {
		//String sql = "from OutLoanCustomer o where o.id=" + oId;
		OutLoanCustomer outLoanCustomer = outLoanCustomerDao.get(oId);System.out.println(outLoanCustomer+"=======");
		//获取到抵押物的JSON字符串
		String string = outLoanCustomer.getSignCustomer().getCollateralString();
		//获取到证件的JSON字符串
		String certificateString = outLoanCustomer.getSignCustomer().getCertificateString();
		if (string!=null&&!string.trim().equals("")) {
			//将JSON字符串转换为抵押物视图Bean集合
			outLoanCustomer.getSignCustomer().setCollateralViewBeans(JSON.parseArray(outLoanCustomer.getSignCustomer().getCollateralString(),CollateralViewBean.class));
		}
		if (certificateString!=null&&!certificateString.trim().equals("")) {
			//将JSON字符串转换为证件视图Bean集合
			outLoanCustomer.getSignCustomer().setCertificateViewBeans(JSON.parseArray(outLoanCustomer.getSignCustomer().getCertificateString(), CertificateViewBean.class));
		}
		
		//获取失败消息
		List<FailureMessage> failureMessages = failureMessageService.getFailureMessageBySignCustomerId(outLoanCustomer.getSignCustomer().getId());
		outLoanCustomer.getSignCustomer().setFailureMessages(failureMessages);
		
		//获取到抵押物的JSON字符串
		string = outLoanCustomer.getCollateralString();
		//获取到证件的JSON字符串
		certificateString = outLoanCustomer.getCertificateString();
		if (string!=null&&!string.trim().equals("")) {
			//将JSON字符串转换为抵押物视图Bean集合
			outLoanCustomer.setCollateralViewBeans(JSON.parseArray(outLoanCustomer.getCollateralString(),CollateralViewBean.class));
		}
		if (certificateString!=null&&!certificateString.trim().equals("")) {
			//将JSON字符串转换为证件视图Bean集合
			outLoanCustomer.setCertificateViewBeans(JSON.parseArray(outLoanCustomer.getCertificateString(), CertificateViewBean.class));
		}		
		
		return outLoanCustomer;
	}

}
