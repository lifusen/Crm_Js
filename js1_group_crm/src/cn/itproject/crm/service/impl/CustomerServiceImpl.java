package cn.itproject.crm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itproject.crm.bean.AllotCustomer;
import cn.itproject.crm.bean.Amountliability;
import cn.itproject.crm.bean.Car;
import cn.itproject.crm.bean.CreditCard;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Customer.CustomerAddType;
import cn.itproject.crm.bean.CustomerAssetType;
import cn.itproject.crm.bean.CustomerRoster;
import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.bean.CustomerState;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Enterprise;
import cn.itproject.crm.bean.House;
import cn.itproject.crm.bean.Land;
import cn.itproject.crm.controller.viewbean.CollateralViewBean;
import cn.itproject.crm.controller.viewbean.ShowCustomer;
import cn.itproject.crm.dao.AllotCustomerDao;
import cn.itproject.crm.dao.CustomerDao;
import cn.itproject.crm.dao.CustomerSourceDao;
import cn.itproject.crm.dao.CustomerTurnDao;
import cn.itproject.crm.dao.EmployeeDao;
import cn.itproject.crm.dao.ReleaseCommonPoolDao;
import cn.itproject.crm.db.BaseDao;
import cn.itproject.crm.service.ConfigService;
import cn.itproject.crm.service.CustomerFollowService;
import cn.itproject.crm.service.CustomerRosterService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.util.DoubleUtil;

@Service
public class CustomerServiceImpl extends BaseServiceSupport<Customer> implements CustomerService {
	@Resource
	private CustomerDao customerDao;

	@Resource
	private AllotCustomerDao allotCustomerDao;

	@Resource
	private CustomerSourceDao customerSourceDao;

	@Resource
	private CustomerFollowService customerFollowService;
	@Resource
	private EmployeeDao employeeDao;
	@Resource
	private CustomerTurnDao customerTurnDao;

	@Resource
	private ReleaseCommonPoolDao releaseCommonPoolDao;
	@Resource
	private ConfigService configService;

	@Override
	protected BaseDao<Customer> getBaseDao() {
		return customerDao;
	}

	@Resource
	private CustomerRosterService customerRosterService;

	@Override
	public List<Customer> getListByState(Integer pageIndex, Integer pageSize, Integer state, Integer eId)
			throws Exception {

		List<Customer> customers = customerDao.getListByState(pageIndex, pageSize, state, eId);

		return customers;
	}

	@Override
	public void updateState(Integer cId, Integer state) throws Exception {
		customerDao.updateState(cId, state);
	}

	@Override
	public void updateVisibility(Integer cId, Integer visibility) throws Exception {
		customerDao.updateVisibility(cId, visibility);
	}

	@Override
	public List<Customer> getAllotToEmployeeList(Integer roleId, Integer dId, Integer pageIndex, Integer pageSize)
			throws Exception {
		List<Customer> customers = customerDao.getAllotToEmployeeList(roleId, dId, pageIndex, pageSize);
		return customers;
	}

	@Override
	public List<ShowCustomer> getNewList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception {
		List<Customer> customers = customerDao.getNewList(pageIndex, pageSize, id, keyStr);
		Integer[] ids = new Integer[pageSize];
		int i = 0;
		for (Customer customer : customers) {
			ids[i] = customer.getId();
			i++;
		}
		List<AllotCustomer> allotCustomers = allotCustomerDao.getListByCId(ids);
		List<ShowCustomer> newCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			ShowCustomer newCustomer = new ShowCustomer();
			newCustomer.setCustomer(customer);
			for (AllotCustomer allotCustomer : allotCustomers) {
				if (customer.getId() == allotCustomer.getCustomer().getId()) {
					newCustomer.setAllotCustomer(allotCustomer);
					break;
				}
			}
			newCustomers.add(newCustomer);
		}
		return newCustomers;
	}

	@Override
	public Integer getCountByState(Integer state, Integer eId) throws Exception {
		return customerDao.getCountByState(state, eId);
	}

	@Override
	public Integer getAllotToEmployeeCount(Integer roleId, Integer dId) throws Exception {
		return customerDao.getAllotToEmployeeCount(roleId, dId);
	}

	@Override
	public Integer getNewOrderCount(Integer[] id, String keyStr) throws Exception {
		return customerDao.getNewOrderCount(id, keyStr);
	}

	@Override
	public List<ShowCustomer> getAssignedOrderList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception {
		Integer[] states = new Integer[2];
		states[0] = 1;
		states[1] = 2;
		List<Customer> customers = customerDao.getByState(pageIndex, pageSize, id, keyStr, states);
		Integer[] ids = new Integer[pageSize];
		int i = 0;
		for (Customer customer : customers) {
			ids[i] = customer.getId();
			i++;
		}
		return null;
	}

	@Override
	public Integer getAssignedOrderCount(Integer[] id, String keyStr) throws Exception {
		return customerDao.getAssignedOrderCount(id, keyStr);
	}

	@Override
	public List<ShowCustomer> getReceiveOrderList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception {
		Integer[] states = new Integer[1];
		states[0] = CustomerState.allocatedToEmp.ordinal();
		List<Customer> customers = customerDao.getByState(pageIndex, pageSize, id, keyStr, states);
		Integer[] ids = new Integer[pageSize];
		int i = 0;
		for (Customer customer : customers) {
			ids[i] = customer.getId();
			i++;
		}
		List<ShowCustomer> newCustomers = new ArrayList<>();
		return newCustomers;
	}

	@Override
	public Integer getReceiveOrderCount(Integer[] id, String keyStr) throws Exception {
		return customerDao.getReceiveOrderCount(id, keyStr);
	}

	@Override
	@Deprecated
	public List<ShowCustomer> getReceiveOrderList(Integer pageIndex, Integer pageSize, Integer departmentId,
			Integer employeeId) throws Exception {
		return null;
	}

	@Override
	@Deprecated
	public Integer getReceiveOrderCount(Integer departmentId, Integer employeeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void batchAddCustomer(List<Customer> customers, CustomerRoster customerRoster, Employee employee)
			throws Exception {
		// 保存客户名单记录
		customerRosterService.addEntity(customerRoster);

		// 获取所有的客户来源信息
		List<CustomerSource> customerSources = customerSourceDao.queryList();
		Map<String, Integer> customerSourceMap = new HashMap<String, Integer>();
		for (CustomerSource customerSource : customerSources) {
			customerSourceMap.put(customerSource.getSourceName(), customerSource.getId());
		}
		System.out.println(customerSourceMap);
		CustomerSource customerSource = null;
		// 批量添加客户
		for (Customer customer : customers) {
			// 获取客户来源ID
			Integer customerSourceId = customerSourceMap.get(customer.getCustomerSource().getSourceName());
			// 客户来源对象
			if (customerSourceId != null) {
				customerSource = new CustomerSource(customerSourceId);
			}
			// 设置客户来源
			customer.setCustomerSource(customerSource);
			// 设置批量导入客户名单
			customer.setCustomerRoster(customerRoster);
			// 保存
			customerDao.save(customer);

			// 添加跟进记录
			customerFollowService.addDefaultCustomerFollow(CustomerAddType.excel.ordinal(), customer.getId(),
					employee.getId());
		}
	}

	public void addExcelCustomer(Customer customer, CustomerRoster customerRoster, Employee employee,
			CustomerSource customerSource, Integer operationType) throws Exception {
		// 设置录入类型:批量添加
		Integer addType = null;
		if (operationType == 1) {// 批量导入并分配
			addType = CustomerAddType.excel.ordinal();
		} else if (operationType == 2) {// 批量导入到公共池
			addType = CustomerAddType.excelCommonPool.ordinal();
		}
		// 添加人id
		Integer employeeId = employee.getId();
		// 添加人name
		String employeeName = employee.getName();

		// 设置添加类型
		customer.setAddType(addType);
		// 设置公司id
		customer.setCompanyId(employee.getCompanyId());
		// 设置添加人id
		customer.setAddPersonId(employeeId);
		// 设置添加人name
		customer.setAddPersonName(employeeName);

		// 设置客户来源
		customer.setCustomerSource(customerSource);
		// 设置批量导入客户名单
		customer.setCustomerRoster(customerRoster);
		// 设置状态:当批量导入到公共池时,设置state为9
		if (operationType == 2) {
			customer.setState(CustomerState.commonPool.ordinal());
		}
		// 保存客户
		customerDao.save(customer);
		// 添加跟进记录
		customerFollowService.addDefaultCustomerFollow(addType, customer.getId(), employee.getId());
	}

	@Override
	public List<ShowCustomer> getValidCustomerList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception {
		Integer[] states = new Integer[5];
		states[0] = CustomerState.allocatedToEmp.ordinal();
		states[2] = CustomerState.follow.ordinal();
		List<Customer> customers = customerDao.getByState(pageIndex, pageSize, id, keyStr, states);
		Integer[] ids = new Integer[pageSize];
		int i = 0;
		for (Customer customer : customers) {
			ids[i] = customer.getId();
			i++;
		}
		List<ShowCustomer> newCustomers = new ArrayList<>();
		return newCustomers;
	}

	@Override
	public Integer getValidCustomerCount(Integer[] id, String keyStr) throws Exception {
		Integer[] states = new Integer[5];
		states[0] = CustomerState.allocatedToEmp.ordinal();
		states[2] = CustomerState.follow.ordinal();
		return customerDao.getCountByState(id, keyStr, states);
	}

	/**
	 * 添加客户的公共方法
	 * 
	 * @param customer
	 * @param employee
	 * @throws Exception
	 */
	private void addCustomer(Customer customer, Employee employee) throws Exception {
		// 设置录入人ID和姓名
		customer.setAddPersonId(employee.getId());
		customer.setAddPersonName(employee.getName());
		// 去掉电话号码的空格
		System.out.println(customer.getPhone()+"~~~~~~~~~~~~~~~");
		customer.setPhone(customer.getPhone().trim());

		// 计算负债总金额
		calcDebtTotal(customer);
		// 构建客户资产信息
		buildAssetInfo(customer);
		// 保存客户
		customerDao.save(customer);
		// 添加默认的跟进记录
		customerFollowService.addDefaultCustomerFollow(customer.getAddType(), customer.getId(), employee.getId());
	}

	@Override
	public void addSalesmanCustomer(Customer customer, Employee employee) throws Exception {
		// 业务员添加
		customer.setAddType(CustomerAddType.salesman.ordinal());
		customer.setCompanyId(employee.getCompanyId());
		// 修改客户的依属关系
		customer.setDepartment(employee.getDepartment()); // 属于的部门
		customer.setEmployee(employee); // 属于的员工
		customer.setState(CustomerState.follow.ordinal()); // 客户的状态为跟进中,直接进入到有效客户列表

		addCustomer(customer, employee);
	}

	/**
	 * 计算负债总金额
	 * 
	 * @param customer
	 */
	private void calcDebtTotal(Customer customer) {
		List<Amountliability> amountliabilities = customer.getAmountliabilitys();
		List<Amountliability> nullList = Arrays.asList(new Amountliability(null, null));
		amountliabilities.removeAll(nullList);
		Double sum = 0d;
		for (Amountliability amountliability : amountliabilities) {
			Double debtMoney = amountliability.getDebtMoney();
			if (debtMoney != null) {
				sum = DoubleUtil.add(sum, debtMoney);
			}
		}
		if (sum != null && sum != 0) {
			customer.setDebtTotal(sum);
		}
	}

	/**
	 * 构建客户资产
	 * 
	 * @param customer
	 */
	private void buildAssetInfo(Customer customer) {
		// 客户资产标题字符串
		StringBuffer assetTitle = new StringBuffer();
		// 客户资产详细信息字符串
		StringBuffer asset = new StringBuffer();
		// 资产名称
		String assetName = null;
		// 房产
		List<House> houses = customer.getHouses();
		if (houses != null && houses.size() > 0) {
			// 清除全部为null的对象
			List<House> nullList = Arrays.asList(new House(null, "", "", "", "", "", "", "", "", "", "", ""));
			houses.removeAll(nullList);

			Integer h = 0, s = 0, o = 0;
			for (House house : houses) {
				if (house.getType() == House.HOUSE) { // 住房
					assetName = CustomerAssetType.house.getValue();
					h++;
				} else if (house.getType() == House.STORE) { // 商铺
					assetName = CustomerAssetType.store.getValue();
					s++;
				} else if (house.getType() == House.OFFICE) { // 写字楼
					assetName = CustomerAssetType.office.getValue();
					o++;
				}

				asset.append(assetName + ":");
				asset.append(house.toTagString());
				asset.append("<br/><br/>");
			}

			if (h > 0) {
				assetTitle.append(CustomerAssetType.house.getValue() + "(" + h + ") ");
			}
			if (s > 0) {
				assetTitle.append(CustomerAssetType.store.getValue() + "(" + s + ") ");
			}
			if (o > 0) {
				assetTitle.append(CustomerAssetType.office.getValue() + "(" + o + ") ");
			}

		}

		// 土地
		List<Land> lands = customer.getLands();
		if (lands != null && lands.size() > 0) {
			// 清除全部为null的对象
			List<Land> nullList = Arrays.asList(new Land(null, ""));
			lands.removeAll(nullList);

			assetName = CustomerAssetType.land.getValue();
			for (Land land : lands) {
				asset.append(assetName + ":");
				asset.append(land.toTagString());
				asset.append("<br/><br/>");
			}
			if (lands.size() > 0) {
				assetTitle.append("土地(" + lands.size() + ") ");
			}
		}

		// 汽车
		List<Car> cars = customer.getCars();
		if (cars != null && cars.size() > 0) {
			// 清除全部为null的对象
			List<Car> nullList = Arrays.asList(new Car(null, "", "", "", "", "", ""));
			cars.removeAll(nullList);

			assetName = CustomerAssetType.car.getValue();
			for (Car car : cars) {
				asset.append(assetName + ":");
				asset.append(car.toTagString());
				asset.append("<br/><br/>");
			}
			if (cars.size() > 0) {
				assetTitle.append("汽车(" + cars.size() + ") ");
			}
		}

		// 企业
		List<Enterprise> enterprises = customer.getEnterprises();
		if (enterprises != null && enterprises.size() > 0) {
			// 清除全部为null的对象
			List<Enterprise> nullList = Arrays.asList(new Enterprise(null, "", "", "", "", "", "", "", "", "", "", ""));
			enterprises.removeAll(nullList);

			assetName = CustomerAssetType.enterprise.getValue();
			for (Enterprise enterprise : enterprises) {
				asset.append(assetName + ":");
				asset.append(enterprise.toTagString());
				asset.append("<br/><br/>");
			}
			if (enterprises.size() > 0) {
				assetTitle.append("企业(" + enterprises.size() + ") ");
			}
		}

		// 信用卡
		List<CreditCard> creditCards = customer.getCreditCards();
		if (creditCards != null && creditCards.size() > 0) {
			// 清除全部为null的对象
			List<CreditCard> nullList = Arrays.asList(new CreditCard(null, "", "", "", null, ""));
			creditCards.removeAll(nullList);

			assetName = CustomerAssetType.creditCard.getValue();
			for (CreditCard creditCard : creditCards) {
				asset.append(assetName + ":");
				asset.append(creditCard.toTagString());
				asset.append("<br/><br/>");
			}
			if (creditCards.size() > 0) {
				assetTitle.append("信用卡(" + creditCards.size() + ") ");
			}
		}

		// 设置客户资产标题信息字符串
		customer.setCustomerAssetTitile(assetTitle.toString());
		// 设置客户资产详细信息字符串
		customer.setCustomerAsset(asset.toString());
	}

	/**
	 * 构建抵押物(客户资产)
	 * 
	 * @param customer
	 * @return
	 */
	public List<CollateralViewBean> buildCollateralViewBean(Customer customer) {
		List<CollateralViewBean> collateralViewBeans = new ArrayList<CollateralViewBean>();

		CollateralViewBean collateralViewBean = null;

		// 房产
		List<House> houses = customer.getHouses();
		for (House house : houses) {
			collateralViewBean = new CollateralViewBean();
			collateralViewBean.setId(house.getId());
			if (house.getType() == House.HOUSE) {
				collateralViewBean.setName(CustomerAssetType.house.getValue());
			} else if (house.getType() == House.STORE) {
				collateralViewBean.setName(CustomerAssetType.store.getValue());
			} else if (house.getType() == House.OFFICE) {
				collateralViewBean.setName(CustomerAssetType.office.getValue());
				;
			}
			collateralViewBeans.add(collateralViewBean);
		}
		// 土地
		List<Land> lands = customer.getLands();
		for (Land land : lands) {
			collateralViewBean = new CollateralViewBean(land.getId(), CustomerAssetType.land.getValue());
			collateralViewBeans.add(collateralViewBean);
		}
		// 汽车
		List<Car> cars = customer.getCars();
		for (Car car : cars) {
			collateralViewBean = new CollateralViewBean(car.getId(), CustomerAssetType.car.getValue());
			collateralViewBeans.add(collateralViewBean);
		}
		// 企业
		List<Enterprise> enterprises = customer.getEnterprises();
		for (Enterprise enterprise : enterprises) {
			collateralViewBean = new CollateralViewBean(enterprise.getId(), CustomerAssetType.enterprise.getValue());
			collateralViewBeans.add(collateralViewBean);
		}
		// 信用卡
		List<CreditCard> creditCards = customer.getCreditCards();
		for (CreditCard creditCard : creditCards) {
			collateralViewBean = new CollateralViewBean(creditCard.getId(), CustomerAssetType.creditCard.getValue());
			collateralViewBeans.add(collateralViewBean);
		}

		return collateralViewBeans;
	}

	@Override
	public Integer getcountByAddType(CustomerAddType addtype, int id) throws Exception {

		return customerDao.getCountByAddType(addtype.ordinal(), id);
	}

	@Override
	public List<Customer> getListByAddType(Integer rId, Integer count) throws Exception {
		return customerDao.getListByAddType(rId, count);
	}

	@Override
	public void applyNullity(Integer cId) throws Exception {
		// 修改客户状态为申请无效
		Customer customer = customerDao.get(cId);
		customer.setSignState("未签单");
		customer.setState(CustomerState.auditInvalid.ordinal());
		customer.setReleaseDate(new Date());
		customerDao.update(customer);
	}

	@Override
	public List<ShowCustomer> getApplyNullityList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception {

		Integer[] states = new Integer[1];
		states[0] = CustomerState.auditInvalid.ordinal();
		List<Customer> customers = customerDao.getByState(pageIndex, pageSize, id, keyStr, states);

		Integer[] ids = new Integer[pageSize];
		int i = 0;
		for (Customer customer : customers) {
			ids[i] = customer.getId();
			i++;
		}
		List<ShowCustomer> newCustomers = new ArrayList<>();
		return newCustomers;
	}

	@Override
	public Integer getApplyNullityCount(Integer[] ids, String keyStr) throws Exception {
		return customerDao.getApplyNullityCount(ids, keyStr);
	}

	@Override
	public void releasePublic(Integer cId) throws Exception {
		// 释放客户到公共池
		Customer customer = customerDao.get(cId);
		customer.setPublicView(1);
		customer.setSignState("未签单");
		customer.setState(CustomerState.commonPool.ordinal());
		customer.setReleaseDate(new Date());
		customerDao.update(customer);
	}

	@Deprecated
	@Override
	public Boolean isFollow(Integer customerId) throws Exception {
		return customerDao.getIsFollow(customerId) != null;
	}

	@Deprecated
	@Override
	public void updateFollowState(Integer customerId) throws Exception {
		customerDao.updateFollowState(customerId);
	}

	@Override
	public void verify(Integer[] cIds, Integer state) throws Exception {
		Integer s = 0;
		if (state == 1) { // 无效
			s = CustomerState.invalid.ordinal();
		} else { // 到公共池
			s = CustomerState.commonPool.ordinal();
		}
		for (int i = 0; i < cIds.length; i++) {
			Customer customer = customerDao.get(cIds[i]);
			customer.setState(s);
			if (s == CustomerState.commonPool.ordinal()) {
				customer.setPublicView(1);
			}
			customerDao.update(customer);
		}
	}

	@Override
	public List<ShowCustomer> getNullityList(Integer pageIndex, Integer pageSize, Integer[] id, String keyStr)
			throws Exception {
		Integer[] states = new Integer[1];
		states[0] = CustomerState.invalid.ordinal();
		List<Customer> customers = customerDao.getByState(pageIndex, pageSize, id, keyStr, states);

		Integer[] ids = new Integer[pageSize];
		int i = 0;
		for (Customer customer : customers) {
			ids[i] = customer.getId();
			i++;
		}
		List<ShowCustomer> newCustomers = new ArrayList<>();
		return newCustomers;
	}

	@Override
	public Integer getNullityCount(Integer[] ids, String keyStr) throws Exception {
		Integer[] states = new Integer[1];
		states[0] = CustomerState.invalid.ordinal();
		return customerDao.getCountByState(ids, keyStr, states);
	}

	@Override
	public void deleteCustomer(Integer id) throws Exception {
		Customer customer = customerDao.get(id);
		customer.setState(CustomerState.delete.ordinal());
		customerDao.update(customer);
	}

	@Override
	public void updateCustomer(Customer updateCustomer) throws Exception {
		// 计算负债总金额
		calcDebtTotal(updateCustomer);
		// 去掉电话号码的空格
		updateCustomer.setPhone(updateCustomer.getPhone().trim());
		// 修改客户
		customerDao.update(updateCustomer);
		// 构建客户资产信息
		buildAssetInfo(updateCustomer);
	}

	@Override
	public Customer getBasicCustomer(Integer id) throws Exception {
		return customerDao.getBasicCustomer(id);
	}

	@Override
	public List<Integer> getListByFllowDate() throws Exception {
		Integer day = configService.getCommonPoolDay();
		return customerDao.getListByFllowDate(day);
	}

	@Override
	public void releaseCommonPool(List<Integer> ids) throws Exception {
		releaseCommonPoolDao.release(ids, 1, 0);
	}

	@Override
	public void receive(Integer id, Employee employee) throws Exception {
		Customer customer = customerDao.get(id);
		customer.setPublicView(null);
		customer.setState(CustomerState.follow.ordinal());
		customer.setDepartment(employee.getDepartment());
		customer.setEmployee(employee);
		customer.setReceiveDate(new Date());
		customer.setFllowDate(new Date());
		customer.setCompanyId(employee.getCompanyId());
		customerDao.update(customer);
	}

	@Override
	public Integer getCustomerState(Integer customerId) throws Exception {
		return customerDao.getCustomerState(customerId);
	}

	@Override
	public Integer getCountByEId(Integer id) throws Exception {
		return customerDao.getCountByEId(id);
	}

	@Override
	public Integer getCustomerByPhoneAndState(String phone, List<Integer> validlist) throws Exception {
		return customerDao.getCountByPhoneAndState(phone, validlist);
	}

	@Override
	public void updateCustomerLevel(Integer id, String level) throws Exception {
		customerDao.updateCustomerLevel(id, level);
	}

	@Override
	public void updateAttentionLevel(Integer id, String level) throws Exception {
		customerDao.updateAttentionLevel(id, level);
	}

	@Override
	public Integer getReleaseById(Integer id, Date date) throws Exception {
		return customerDao.getReleaseCountById(id, date);
	}

	@Override
	public Integer getReceiveById(Integer id, Date date) throws Exception {
		return customerDao.getReceiveCountById(id, date);
	}

	@Override
	public void updateLastTime(Integer id) throws Exception {
		String hql = "update Customer set updateTime = ? where id = ?";
		customerDao.update(hql, new Date(), id);
	}

	@Override
	public Integer getValidCustomerCount(Integer eId) throws Exception {
		return customerDao.getValidCustomer(eId);
	}
}
