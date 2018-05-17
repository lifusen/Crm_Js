package cn.itproject.crm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.ReportDetail;
import cn.itproject.crm.controller.viewbean.ReportInfo;
import cn.itproject.crm.controller.viewbean.SeriesInfo;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.EmployeeService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.DateUtil;

/**
 * 业务反馈报表
 * @author ham
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/report")
public class ReportController extends BaseController{

	@Resource
	CustomerFollowService customerFollowService;
	@Resource
	DepartmentService departmentService;
	@Resource
	EmployeeService employeeService;
	@Resource
	private  CompanyService companyService;
	
	@RequestMapping("report")
	public String report(Integer initPage,Model model,String edtDateBegin,String edtDateEnd,Integer dId,Integer eId,String defaultShow) throws Exception{
		// 根据提供的日期范围，列举出每一天
		String beginDate = null;
		String endDate = null;
		if ("week".equals(defaultShow)) {//第一次加载时,默认显示本周数据
			String[] strings = DateUtil.getCurrentWeekDay();
			beginDate = strings[0];
			endDate = strings[1];
		}else {
			if (formatNull(edtDateBegin)==null && formatNull(edtDateEnd)==null) {
				beginDate = formatDate(new Date());
				endDate = beginDate;
			}else if(formatNull(edtDateBegin)!=null && formatNull(edtDateEnd)!=null){
				beginDate = edtDateBegin;
				endDate = edtDateEnd;
			}else {
				if (formatNull(edtDateBegin)!=null) {
					beginDate = edtDateBegin;
					endDate = beginDate;
				}else {
					beginDate = edtDateEnd;
					endDate = beginDate;
				}
			}
		}
		
		List<String> dates = new ArrayList<>();
		dates.add(beginDate);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dempDate = format.parse(beginDate);
		Date endDate2 = format.parse(endDate);
		while (true) {
			c.setTime(dempDate);
			int day=c.get(Calendar.DATE);
			c.set(Calendar.DATE,day+1);
			Date nowDate = c.getTime();
			if (DateUtils.isSameDay(nowDate, endDate2)) {
				dates.add(formatDate(nowDate));
				dempDate = nowDate;
				break;
			}else if (!nowDate.before(endDate2)){
				break;
			}
			dates.add(formatDate(nowDate));
			dempDate = nowDate;
		}
		Employee employee = (Employee) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
		Integer roleId = employee.getRole().getId();
		String userType = employee.getType();
		List<Department> departments = null;
		if ((StringUtils.isNotBlank(userType) && userType.equals(Constant.SUPER_ROLE))) {
			departments = departmentService.getDeparmentByName("业务");
		}else{
			Integer[] ids = new Integer[1];
			ids[0] = employee.getCompanyId();
			departments = departmentService.getDepartmentByCompanyIds(ids,"业务");
		}
		model.addAttribute("dates", dates);
		model.addAttribute("roleId", roleId);
		model.addAttribute("departments", departments);	
		List<Department> searchDepartments = new ArrayList<>();
		List<Employee> employees = new ArrayList<>();
		List<Map<String,Object>> list = null;
		Map<String, Map<Integer, ReportInfo>> reportMap = new LinkedHashMap<String, Map<Integer, ReportInfo>>();
		List<Integer> eIds = null; 
		if (Constant.managerRoleIds.contains(roleId) || (StringUtils.isNotBlank(userType) && userType.equals(Constant.SUPER_ROLE))) {		// 管理层
			if (eId!=null && eId>0) {		// 统计某个员工
				employees.add(employeeService.getEmployee(eId));
				eIds = getEIds(employees);
				list = customerFollowService.getReport(eIds,beginDate, endDate);
			}else if(dId!=null && dId>0){	// 统计某个部门
				Integer[] ids = new Integer[1];ids[0]=dId;
				employees = employeeService.getEmployeeByDIds(ids);
				eIds = getEIds(employees);
				list = customerFollowService.getReport(eIds,beginDate, endDate);
			}else {					// 统计所有部门
				searchDepartments=departments;
				list = customerFollowService.getReport(beginDate,endDate);
			}
		}else if(Constant.businessManagerRoleId==roleId){	// 部门经理
			if (eId!=null && eId>0) {		// 统计某个员工
				employees.add(employeeService.getEmployee(eId));
				eIds = getEIds(employees);
				list = customerFollowService.getReport(eIds,beginDate, endDate);
			}else {							// 统计该部门员工信息
				Department currDepartment = employee.getDepartment();
				Integer[] ids = new Integer[1];ids[0]=currDepartment.getId();
				employees = employeeService.getEmployeeByDIds(ids);
				eIds = getEIds(employees);
				list = customerFollowService.getReport(eIds,beginDate, endDate);
			}
		}else {
			employees.add(employeeService.getEmployee(employee.getId()));
			eIds = getEIds(employees);
			list = customerFollowService.getReport(eIds,beginDate, endDate);
		}
		if (eIds!=null && eIds.size()>0) {
			model.addAttribute("dSize", eIds.size());	
		}else{
			model.addAttribute("dSize", departments.size());	
		}
		
		for (String date:dates) {
			Map<Integer, ReportInfo> dMap = new LinkedHashMap<Integer, ReportInfo>();
			if (searchDepartments.size()>0) {
				for (Department d:searchDepartments) {
					dMap.put(d.getId(), new ReportInfo());
				}
				reportMap.put(date, dMap);
			}else if(employees.size()>0){
				for (Employee e:employees) {
					dMap.put(e.getId(), new ReportInfo());
				}
				reportMap.put(date, dMap);
			}
		}
		for (Map<String, Object> map:list) {
			Date edDate = (Date) map.get("edtDate");
			String edStr = formatDate(edDate);
			if (reportMap.containsKey(edStr)) {
				Map<Integer, ReportInfo> infoMap = reportMap.get(edStr);
				Integer rId = Integer.parseInt(map.get("rid").toString());
				if (infoMap.containsKey(rId)) {
					ReportInfo info = infoMap.get(rId);
					Integer type = (Integer)map.get("type");
					Integer count = Integer.parseInt(map.get("count").toString());
					setCount(info, type, count);
				}
			}
		}
		model.addAttribute("report", reportMap);
		if (employees!=null && employees.size()>0) {
			model.addAttribute("reportTitle", employees);
			model.addAttribute("e_or_d", "E");
		}else {
			model.addAttribute("reportTitle", searchDepartments);
			model.addAttribute("e_or_d", "D");
		}
		model.addAttribute("employees", employees);
		
		model.addAttribute("defaultShow", defaultShow);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		
		if (initPage==null) {
			return "page/report/report";
		}else {
			return "page/report/reportDetail";
		}
	}

	
	@RequestMapping("reportDetail")
	@ResponseBody
	public ReportDetail reportDetail(String edtDateBegin,String edtDateEnd,String dId,String eId) throws Exception{
		String beginDate = null;
		String endDate = null;
		if (formatNull(edtDateBegin)==null && formatNull(edtDateEnd)==null) {
			beginDate = formatDate(new Date());
			endDate = beginDate;
		}else if(formatNull(edtDateBegin)!=null && formatNull(edtDateEnd)!=null){
			beginDate = edtDateBegin;
			endDate = edtDateEnd;
		}else {
			if (formatNull(edtDateBegin)!=null) {
				beginDate = edtDateBegin;
				endDate = beginDate;
			}else {
				beginDate = edtDateEnd;
				endDate = beginDate;
			}
		}
		List<String> dates = new ArrayList<>();
		dates.add(beginDate);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dempDate = format.parse(beginDate);
		Date endDate2 = format.parse(endDate);
		while (true) {
			c.setTime(dempDate);
			int day=c.get(Calendar.DATE);
			c.set(Calendar.DATE,day+1);
			Date nowDate = c.getTime();
			if (DateUtils.isSameDay(nowDate, endDate2)) {
				dates.add(formatDate(nowDate));
				dempDate = nowDate;
				break;
			}else if (!nowDate.before(endDate2)){
				break;
			}
			dates.add(formatDate(nowDate));
			dempDate = nowDate;
		}
		List<Map<String,Object>> list = null;
		if (eId!=null && !eId.equals("")) {		// 统计某个员工
			list = customerFollowService.getReport(getIds(eId),beginDate, endDate);
		}else if(dId!=null && !dId.equals("")){	// 统计某个部门
			list = customerFollowService.getDepartmentReport(getIds(dId),beginDate, endDate);
		}
		
		ReportDetail reportDetail = new ReportDetail();
		reportDetail.setDates(dates);
		List<SeriesInfo> infos = new ArrayList<>();
		infos.add(bulidSeries(1,list,dates));
		infos.add(bulidSeries(2,list,dates));
		infos.add(bulidSeries(3,list,dates));
		infos.add(bulidSeries(4,list,dates));
		infos.add(bulidSeries(5,list,dates));
		reportDetail.setSeriesInfos(infos);
		return reportDetail;
	}
	
	
	private void setCount(ReportInfo info, Integer type,Integer count) {
		if (count==null) {
			count=0;
		}
		if (type==1) {
			info.setPhoneCount(count);
		}else if(type==2){
			info.setMessageCount(count);
		}else if(type==3){
			info.setVisitCount(count);
		}else if(type==4){
			info.setSignCount(count);
		}else if(type==5){
			info.setAfterSaleCount(count);
		}
	}
	
	private String formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	private List<Integer> getEIds(List<Employee> employees){
		List<Integer> ids = new ArrayList<>();
		for (Employee employee:employees) {
			ids.add(employee.getId());
		}
		return ids;
	}
	private String formatNull(String str){
		if (str==null || "".equals(str)) {
			return null;
		}
		return str;
	}
	
	private SeriesInfo bulidSeries(Integer type,List<Map<String,Object>> list,List<String> dates){
		SeriesInfo info = new SeriesInfo();
		if (type==1) {
			// 电话
			info.setName("电话");
		}else if(type==2){
			// 短信
			info.setName("短信");
		}else if(type==3){
			// 上门
			info.setName("上门");
		}else if(type==4){
			// 签约
			info.setName("签约");
		}else if(type==5){
			// 售后
			info.setName("售后");
		}
		setCount(info,type,list,dates);
		return info;
	}
	private void setCount(SeriesInfo info,Integer type,List<Map<String,Object>> list,List<String> dates){
		List<Integer> data = new ArrayList<>();
		if (list.size()<1) {
			for (int i=0;i<dates.size();i++) {
				data.add(0);
			}
			info.setData(data);
			return ;
		}
		for (String date:dates) {
			int count = 0;
			for (Map<String, Object> map:list) {
				Date edDate = (Date) map.get("edtDate");
				String edStr = formatDate(edDate);
				if (edStr.equals(date)) {
					Integer dempType = (Integer)map.get("type");
					if (type==dempType) {
						count = count + Integer.parseInt(map.get("count").toString());
					}
				}
			}
			data.add(count);
		}
		info.setData(data);
	}

	private List<Integer> getIds(String idstring){
		String[] idStrs = idstring.split(",");
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < idStrs.length; i++) {
			ids.add(Integer.parseInt(idStrs[i]));
		}
		return ids;
	}
	@RequestMapping("/reportCustomerList")
	public String reportCustomerList(String types,String dateStr,Model model,String eOrd,Integer pageIndex,String whereKey) throws Exception{
		whereKey = (whereKey==null?"":whereKey);
		pageIndex = initPageIndex(pageIndex);
		String[] typeArray = types.split("-");
		Integer reportType = Integer.parseInt(typeArray[0]);
		Integer id = Integer.parseInt(typeArray[1]);
		List<Map<String, Object>> customers = customerFollowService.getReportList(reportType,
				id,eOrd,dateStr,pageIndex,pageSize,whereKey);
		
		Integer count = customerFollowService.getReportListCount(reportType,
				id,eOrd,dateStr,whereKey);
		builderParam(model, pageIndex, pageSize, count, 
				"report/reportCustomerList.do?types="+types+"&dateStr="+dateStr+
				"&eOrd="+eOrd+"&whereKey="+whereKey,"tableList");
		model.addAttribute("customers", customers);
		model.addAttribute("types",types);
		model.addAttribute("dateStr",dateStr);
		model.addAttribute("eOrd",eOrd);
		return "page/report/reportCustomer-List";
	}
}