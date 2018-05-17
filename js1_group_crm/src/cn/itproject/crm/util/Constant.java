package cn.itproject.crm.util;

import java.util.Arrays;
import java.util.List;

import cn.itproject.crm.bean.CustomerState;


public interface Constant {

	/**
	 * 操作结果消息
	 */
	public String MSG = "msg";				

	/**
	 * 登录用户
	 */
	public String LOGIN_USER = "loginUser";
	
	/**
	 * 登录公司对象
	 */
	public String LOGIN_COMPANY = "loginCompany";

	/**
	 * 初始化密码
	 */
	public String INIT_PASSWORD = "123456";
	
	// 员工状态
	public Integer NORMAL=1;		// 正常
	public Integer DIMISSION=0;		// 离职
	public Integer DELETE=2;		// 删除

	public String LOGIN_FLAG = "login_flag";
	public String SUPER_ROLE = "SUPER_ROLE";
	
	// 传输的百分比
	public String PROGRESS_VALUE = "progressValue";
	// 文件总大小
	public String TOTAL_VALUE = "totalValue";
	// 已传输传送的大小
	public String CURRENT_VALUE = "currentValue";
	/**访问上传文件的路径**/
	public String UPLOAD_PATH = "/crmupload";
	/**证件字符串**/
	public String CERTIFICATES = "certificates";
	/**Excel字符串**/
	public String EXCELS = "excels";
	
	/**多少天自动释放到公共池**/
	public Integer RELEASE_DAY = 55;

	/**
	 * 公共池天数
	 */
	public String COMMON_POOL_DAY = "commonPoolDay";
	
	/**自留人数**/
	public Integer VISIBILITY_COUNT = 50;
	
	

	/**每日基础工作量**/
	public String TODAY_BASIC_WORK_TOTAL = "todayBasicWorkTotal";
	
	/**
	 * 1:admin
	 * 2:总经理
	 * 5:市场部经理
	 * 7:销售总监
	 */
	List<Integer> managerRoleIds = Arrays.asList(1,2,5,7,1000);
	/**
	 * 业务部门经理角色ID
	 */
	Integer businessManagerRoleId = 3;
	
	/**
	 * 超级角色
	 */
	Integer superAdmin = 999;
	/**
	 * 所有权证中心老大(权证总监)
	 */
	Integer warrantSuper = 2000;
	
	/**
	 * 某个权证中心老大(权证中心管理员)
	 */
	Integer warrantAdmin = 2001;
	
	/**
	 * 权证部经理
	 */
	Integer warrantManager = 2002;
	
	/**
	 * 权证专员
	 */
	Integer warranter = 2003;
	
	/**
	 * 行政人员角色ID
	 */
	List<Integer> administrationRoleIds = Arrays.asList(4);
	
	
	/**分配到部门*/
	List<Integer> allotToDepartments = Arrays.asList(
			CustomerState.unassigned.ordinal());
	/**分配到员工*/
	List<Integer> allotToEmployees = Arrays.asList(
			CustomerState.allocatedToDept.ordinal());
	/**我的新订单*/
	List<Integer> newOrders = Arrays.asList(
			CustomerState.allocatedToEmp.ordinal());
	/**有效客户列表*/
	List<Integer> validList = Arrays.asList(
			CustomerState.follow.ordinal(),
			CustomerState.sign.ordinal(),
			CustomerState.outLoan.ordinal(),
			CustomerState.chargeback.ordinal(),
			CustomerState.reject.ordinal());
	/**签单客户*/
	List<Integer> signList = Arrays.asList(
			CustomerState.sign.ordinal(),
			CustomerState.outLoan.ordinal(),
			CustomerState.chargeback.ordinal(),
			CustomerState.reject.ordinal());
	
	/**退单退件客户*/
	List<Integer> rejectOrChargebackList = Arrays.asList(
			CustomerState.sign.ordinal(),
			CustomerState.chargeback.ordinal(),
			CustomerState.reject.ordinal());
	
	/**放款客户*/
	List<Integer> outLoanList = Arrays.asList(
			CustomerState.sign.ordinal(),
			CustomerState.outLoan.ordinal(),
			CustomerState.chargeback.ordinal(),
			CustomerState.reject.ordinal());
	
	/**审核无效客户列表*/
	List<Integer> auditList = Arrays.asList(
			CustomerState.auditInvalid.ordinal());
	
	/**公共池列表*/
	List<Integer> commonPoolList = Arrays.asList(
			CustomerState.commonPool.ordinal());
	
	/**无效列表*/
	List<Integer> invalidList = Arrays.asList(
			CustomerState.invalid.ordinal());

	/**工作提醒列表*/
	List<Integer> reminderList = Arrays.asList(
			CustomerState.allocatedToEmp.ordinal(),
			CustomerState.follow.ordinal(),
			CustomerState.sign.ordinal(),
			CustomerState.chargeback.ordinal(),
			CustomerState.reject.ordinal(),
			CustomerState.outLoan.ordinal());
	
	/**
	 * 部门经理角色ID
	 */
	Integer[] departmentManagerRoles = new Integer[]{3};
	
	/**
	 * 可以放款和退单退件的角色ID集合
	 * 超级管理员,超级权证,权证中心管理员,权证部门经理,权证专员,可以放款,退单退件
	 */
	List<Integer> canLoanRoles = Arrays.asList(999,1001,2000,2001,2002,2003);
	
	/**
	 * 权证角色ID集合
	 * 超级权证,权证中心管理员,权证部门经理,权证专员的客户跟进类型与业务部不一样
	 */
	List<Integer> warrantRoles = Arrays.asList(1001,2000,2001,2002,2003);
	
	/**
	 * 是否有放款和退单退件的权限
	 */
	String CAN_LOAN_PERMISSION = "canLoanPermission";

	/**
	 * 是否是权证角色
	 */
	String WARRANT_ROLE = "warrantRole";
	
	/**
	 * 业务员角色ID集合
	 */
	List<Integer> salesmanRoleIds = Arrays.asList(8,9,10,11,12);
	
	/**
	* 客户详情token,只对当前登录用户有效
	 */
	String CUSTOMER_DETAIL_TOKEN = "customerDetailToken";
	
	/**
	 * 系统头部LOGO
	 */
	String SYSTEM_HEADER_LOGO = "systemHeaderLogo";

	/**
	 * 当前登录用户的角色ID
	 */
	String LOGIN_ROLE_ID = "loginRoleId";
}