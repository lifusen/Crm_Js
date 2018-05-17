package cn.itproject.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.OutLoanBelong;
import cn.itproject.crm.bean.SignCustomer;
import cn.itproject.crm.bean.WarrantAllotRecord;
import cn.itproject.crm.dao.OutLoanBelongDao;
import cn.itproject.crm.dao.SignCustomerDao;
import cn.itproject.crm.dao.WarrantAllotRecordDao;
import cn.itproject.crm.service.NotificationService;
import cn.itproject.crm.service.WarrantAllotService;
import cn.itproject.crm.util.LoginUserUtil;

@Service
public class WarrantAllotServiceImpl implements WarrantAllotService{

	@Resource
	private SignCustomerDao signCustomerDao;
	@Resource
	private WarrantAllotRecordDao warrantAllotRecordDao;
	@Resource
	private OutLoanBelongDao outLoanBelongDao;
	@Resource
	private NotificationService notificationService;
	
	@Override
	public void allot(Employee employee,Integer companyId,Integer dId, Integer eId, String ids) throws Exception {
		String[] idList = ids.split(",");
		Integer[] idArray = new Integer[idList.length];
		for(int i=0;i<idList.length;i++){
			idArray[i] = Integer.parseInt(idList[i]);
		}
		
		// 修改对应签单的权证部门id或权证专员id
		signCustomerDao.updateWarrant(idArray,companyId,dId,eId);
		for(int i=0;i<idArray.length;i++){
//			SignCustomer customer = signCustomerDao.get(idArray[i]);
			WarrantAllotRecord warrantAllotRecord = new WarrantAllotRecord();
			warrantAllotRecord.setAllotTime(new Date());
			warrantAllotRecord.setCompanyId(employee.getCompanyId());
			warrantAllotRecord.setCustomerId(null); // TODO
			Department department = employee.getDepartment();
			if (department!=null) {
				warrantAllotRecord.setDepartmentId(department.getId());
			}
			warrantAllotRecord.setEmployeeId(employee.getId());
			warrantAllotRecord.setSignCustomerId(idArray[i]);
			if (companyId!=null) {
				warrantAllotRecord.setWarrantCompanyId(companyId);
			}else {
				warrantAllotRecord.setWarrantCompanyId(employee.getCompanyId());
			}
			warrantAllotRecord.setWarrantDepartmentId(dId);
			warrantAllotRecord.setWarrantEmployeeId(eId);
			warrantAllotRecord.setOperateType(2);
			// 1:分配给权证中心/2:分配给权证部门/3.分配给权证专员
			if (eId!=null && eId>0) { // 分配到员工
				warrantAllotRecord.setAllotType(3);
				//跟新签单和权证专员关系表outloanbelong
				OutLoanBelong outLoanBelong = new OutLoanBelong(idArray[i], eId, dId, companyId, 0);//主贷权证：0、辅贷权证1
				outLoanBelongDao.save(outLoanBelong);
				//发送消息
				SignCustomer signCustomer = signCustomerDao.get(idArray[i]);
				notificationService.addNotification(employee.getId(), signCustomer);
			}else if(dId!=null && dId>0){
				warrantAllotRecord.setAllotType(2);
			}else if(companyId!=null && companyId>0){
				warrantAllotRecord.setAllotType(1);
			}
			warrantAllotRecordDao.save(warrantAllotRecord);
		}
	}

	@Override
	public boolean addAssistLoaner(OutLoanBelong outLoanBelong)
			throws Exception {
		//添加辅贷人员
		outLoanBelong.setType(1);//主贷权证：0、辅贷权证1
		Integer type = outLoanBelongDao.getType(outLoanBelong.getSignCustomerId(), outLoanBelong.getWarranterId());
		if(type==null || "".equals(type)){
			outLoanBelongDao.save(outLoanBelong);
			//发送消息
			SignCustomer signCustomer = signCustomerDao.get(outLoanBelong.getSignCustomerId());
			Integer senderId = LoginUserUtil.getEmployeeId();
			notificationService.addNotification(outLoanBelong, signCustomer);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeAssistLoaner(OutLoanBelong outLoanBelong) throws Exception {
		//移除辅贷人员
		return outLoanBelongDao.removeAssistLoaner(outLoanBelong);
	}
}