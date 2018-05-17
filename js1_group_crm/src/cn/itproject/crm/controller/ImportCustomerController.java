package cn.itproject.crm.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.CustomerRoster;
import cn.itproject.crm.bean.CustomerSource;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.ContactsService;
import cn.itproject.crm.service.CustomerRosterService;
import cn.itproject.crm.service.CustomerService;
import cn.itproject.crm.service.CustomerSourceService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.ExcelUtil;
import cn.itproject.crm.util.PropertieFactory;
import cn.itproject.crm.util.StringUtil;

/**
 * <code>
 * 导入Excel数据到数据库
 * 	1.市场部批量导入客户-->分配给部门
 * 	2.市场部批量导入客户到公共池
 * </code>
 * 
 * @author MrLiu
 * @date 2016-3-11
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class ImportCustomerController extends BaseController {
	private static final Logger log = Logger.getLogger(ImportCustomerController.class);
	@Resource
	private CustomerService customerService;
	@Resource
	private CustomerSourceService customerSourceService;
	@Resource
	private CustomerRosterService customerRosterService;
	@Resource
	private ContactsService contactsService;

	/**
	 * 跳转到批量导入客户的界面,市场部批量导入客户和批量导入客户到公共池公用同一个界面
	 * 
	 * @param operationType
	 *            1:批量导入客户/2:批量导入客户到公共池
	 * @return 导入客户的界面
	 */
	@RequestMapping("/importCustomerUI")
	public String importCustomerUI(Integer operationType, Model model) {
		if (operationType == null) {
			operationType = 1;
		}
		// 操作名称
		String operationTypeName = null;
		// 提交按钮名称
		String submitButtonName = null;
		// 颜色
		String themeColor = null;
		if (operationType == 1) {
			operationTypeName = "批量导入客户";
			submitButtonName = "导入";
			themeColor = "blue";
		} else if (operationType == 2) {
			operationTypeName = "批量导入客户到公共池";
			submitButtonName = "导入到公共池";
			themeColor = "yellow";
		}
		model.addAttribute("operationType", operationType);
		model.addAttribute("operationTypeName", operationTypeName);
		model.addAttribute("submitButtonName", submitButtonName);
		model.addAttribute("themeColor", themeColor);
		return "page/customer/importCustomer";
	}

	/**
	 * 批量导入客户/批量到客户到公共池的解析处理方法
	 * 
	 * @param file
	 *            Excel文件
	 * @param customerRosterName
	 *            名单名称
	 * @param operationType
	 *            操作类型 1:批量导入客户/2:批量导入客户到公共池
	 * @param request
	 * @return 处理结果
	 */
	@RequestMapping(value = "/importCustomer", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> importCustomer(@RequestParam(value = "file") MultipartFile file,
			String customerRosterName, Integer operationType, HttpServletRequest request) {
		System.out.println("importCustomer......");
		// 原文件名称
		String originalfileName = file.getOriginalFilename();
		System.out.println(file);
		System.out.println(file.isEmpty());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());

		// 消息提醒
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);

		// 如果没有文件
		if (file.isEmpty()) {
			map.put("msg", "请选择文件!");
			return map;
		}

		// 文件内容类型
		String fileType = file.getContentType();
		System.out.println(fileType);
		if (!("application/vnd.ms-excel".equals(fileType)
				|| "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(fileType))) {
			map.put("msg", "请上传xls或xlsx格式的Excel表格!");
			return map;
		}
		// 文件大小,限制为10MB:1024*1024*5=
		long fileSize = file.getSize();
		if (fileSize > (1024 * 1024 * 10)) {
			map.put("msg", "上传的文件大小不能超过10M！");
			return map;
		}

		// 使用POI解析Excel文件
		try {
			// 1.通过File获取Excel工作簿对象
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			System.out.println(workbook);
			// 3.获取到Sheet对象
			Sheet sheet = workbook.getSheetAt(0);
			System.out.println(sheet);

			// 判断是否是空文件
			Integer totalRowCount = sheet.getLastRowNum();
			System.out.println(totalRowCount);
			if (totalRowCount == 0) {
				map.put("msg", "您上传的Excel文件没有数据!");
				return map;
			}
			// 判断格式
			Row firstRow = sheet.getRow(0);
			System.out.println(firstRow);
			if (firstRow == null) {
				map.put("msg", "您上传的Excel文件格式有误,第一行必须有4列,分别是:客户姓名、手机号码、来源、备注,请核对Excel模板!");
				return map;
			}
			Integer totalCellCount = (int) firstRow.getLastCellNum();
			System.out.println(totalCellCount);
			if (totalCellCount < 4) {
				map.put("msg", "您上传的Excel文件格式有误,第一行必须有4列,分别是:客户姓名、手机号码、来源、备注,请核对Excel模板!");
				return map;
			}
			String cellA = ExcelUtil.getStringCellValue(firstRow.getCell(0));
			String cellB = ExcelUtil.getStringCellValue(firstRow.getCell(1));
			String cellC = ExcelUtil.getStringCellValue(firstRow.getCell(2));
			String cellD = ExcelUtil.getStringCellValue(firstRow.getCell(3));
			if (!"客户姓名".equals(cellA)) {
				map.put("msg", "第一行的第一列(1-A)只能是客户姓名,请检查Excel文件的第一行格式是否与模板一致!");
				return map;
			}
			if (!"手机号码".equals(cellB)) {
				map.put("msg", "第一行的第二列(1-B)只能是手机号码,请检查Excel文件的第一行格式是否与模板一致!");
				return map;
			}
			if (!"来源".equals(cellC)) {
				map.put("msg", "第一行的第三列(1-C)只能是来源,请检查Excel文件的第一行格式是否与模板一致!");
				return map;
			}
			if (!"备注".equals(cellD)) {
				map.put("msg", "第一行的第四列(1-D)只能是备注,请检查Excel文件的第一行格式是否与模板一致!");
				return map;
			}

			// 判断是否是导入的模板
			if (totalRowCount == 1) {
				map.put("msg", "模板中没有客户信息,请检查您上传的Excel文件!");
				return map;
			}

			// 创建客户对象集合
			List<Customer> customers = new ArrayList<Customer>();
			// 所有客户电话号码集合
			List<String> customerPhones = new ArrayList<String>();
			// 获取登陆对象
			Employee employee = getLoginEmployee();
			Integer employeeId = employee.getId();
			String employeeName = employee.getName();

			// 保存时的文件名称
			String newFileName = null;

			System.out.println("循环读取数据");
			for (Row row : sheet) {
				System.out.println(row.getRowNum());
				if (row.getRowNum() > 0) {
					try {
						Cell cell = null;

						// 1.获取客户姓名
						cell = row.getCell(0);
						String customerName = StringUtil.substring(ExcelUtil.getStringCellValue(cell), 30);

						// 2.手机号码
						cell = row.getCell(1);
						String customerPhone = StringUtil.substring(ExcelUtil.getStringCellValue(cell), 80);

						// 3.来源
						cell = row.getCell(2);
						String customerSourceName = StringUtil.substring(ExcelUtil.getStringCellValue(cell), 20);

						// 4.备注
						cell = row.getCell(3);
						String customerOtherInfo = ExcelUtil.getStringCellValue(cell);

						// 如果整行都没有数据,则不导入
						if ((customerName == null || customerName.equals(""))
								&& (customerPhone == null || customerPhone.equals(""))
								&& (customerSourceName == null || customerSourceName.equals(""))
								&& (customerOtherInfo == null || customerOtherInfo.equals(""))) {
							continue;
						}

						// 如果电话号码为空,则不导入
						if (customerPhone == null || customerPhone.equals("")) {
							map.put("msg", "请完善客户电话及客户来源后重新导入！");
							return map;
						}
						
						// 如果来源为空,则不导入
						if (customerSourceName == null || customerSourceName.equals("")) {
							map.put("msg", "请完善客户电话及客户来源后重新导入！");
							return map;
						}

						// 创建客户对象
						Customer customer = new Customer();
						// 客户名称
						customer.setName(customerName);
						// 电话号码
						customer.setPhone(customerPhone);
						// 客户来源
						customer.setCustomerSource(new CustomerSource(customerSourceName));
						// 备注
						customer.setOtherInfo(customerOtherInfo);

						// 添加到集合中
						customers.add(customer);
						// 将手机号码添加到集合中
						if (customerPhone != null && customerPhone.trim().length() > 0) {
							customerPhones.add(customerPhone);
						}
					} catch (Exception e) {
						System.out.println("------------------->异常");
						e.printStackTrace();
					}
				}
			}

			// 验证是否全部是空数据
			if (customers.size() == 0) {
				map.put("msg", "Excel中没有客户信息,请检查您上传的Excel文件!");
				return map;
			}

			if (customerPhones.size() == 0) {
				map.put("msg", "Excel中的手机号码列全部为空,请检查您上传的Excel文件,添加手机号码!");
				return map;
			}

			// 获取重复的电话号码列表
			List<String> dbRepeatPhones = contactsService.getRepeatPhones(customerPhones);
			// 如果电话号码全部重复,则直接提示错误
			if (customerPhones.size() == dbRepeatPhones.size()) {
				map.put("msg", "Excel中的所有手机号码在系统中全部存在,请检查您上传的Excel文件!");
				return map;
			}

			// 读取Excel成功
			// "读取Excel成功,共导入了位"+customers.size()+"学员的信息!"

			// 年月
			SimpleDateFormat ymDateFormat = new SimpleDateFormat("yyyyMM");
			// 保存路径
			String savePath = PropertieFactory.getProVal("uploadUrl") + File.separator + Constant.EXCELS
					+ File.separator + ymDateFormat.format(new Date());
			System.out.println(savePath);

			File savePathFile = new File(savePath);
			if (!savePathFile.exists()) {
				savePathFile.mkdirs();
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
			String currentDateString = format.format(new Date());

			// 保存时的文件名称
			newFileName = currentDateString + "-eId-" + employeeId + "-eName-" + employeeName + "-"
					+ file.getOriginalFilename();
			File saveFile = new File(savePathFile, newFileName);
			try {
				// 保存文件
				file.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 客户名单数量,第一次保存时记录整个Excel中的有效客户行数,如果有导入失败的客户,在修改名单数量
			Integer customerRosterCount = 0;
			try {
				// 客户名单数量
				customerRosterCount = customers.size();
				// 创建导入客户名单对象
				CustomerRoster customerRoster = new CustomerRoster(customerRosterName, customerRosterCount, employeeId,
						employeeName, originalfileName, newFileName);
				if (operationType == 2) {
					customerRoster.setState(0); // 表示不需要分配
				}
				// 保存客户名单记录
				customerRosterService.addEntity(customerRoster);

				// ---------------------------------------- 替代代码start
				// ----------------------------------------

				// 获取所有的客户来源信息
				List<CustomerSource> customerSources = customerSourceService.getCustomerSourceList();
				Map<String, Integer> customerSourceMap = new HashMap<String, Integer>();
				for (CustomerSource customerSource : customerSources) {
					customerSourceMap.put(customerSource.getSourceName(), customerSource.getId());
				}
				System.out.println(customerSourceMap);
				CustomerSource customerSource = null;

				// 成功导入的客户数量
				Integer successCount = 0;
				// 电话号码重复的错误数量
				Integer phoneUniqueErrorCount = 0;
				// 添加失败的数量(不包含重复的错误数量)
				Integer failCount = 0;

				// 批量添加客户
				for (Customer customer : customers) {
					//客户电话号码
					String phone = customer.getPhone();
					// 排除重复的电话号码
					if (dbRepeatPhones.contains(phone)) {
						phoneUniqueErrorCount++; // 重复错误累加
						continue;
					}

					// 获取客户来源ID
					Integer customerSourceId = customerSourceMap.get(customer.getCustomerSource().getSourceName());
					// 客户来源对象
					if (customerSourceId != null) {
						customerSource = new CustomerSource(customerSourceId);
					}
					try {
						customerService.addExcelCustomer(customer, customerRoster, employee, customerSource,
								operationType);
						successCount++;
					} catch (ConstraintViolationException e) {
						System.out.println(e.getMessage());
						phoneUniqueErrorCount++; // 重复错误累加
						dbRepeatPhones.add(phone);
					} catch (Exception e) {
						failCount++; // 失败错误累加
						e.printStackTrace();
					}
				}

				// 修改名单信息
				// 成功的数量
				customerRoster.setSuccessCount(successCount);
				// 失败的数量
				customerRoster.setFailureCount(customerRosterCount - successCount);
				// 如果一个都没有成功,则修改状态为失败
				if (successCount == 0) {
					customerRoster.setState(1);// 全部失败
				}
				// 修改名单信息
				customerRosterService.updateEntity(customerRoster);

				System.out.println("phoneUniqueErrorCount:" + phoneUniqueErrorCount);
				System.out.println("failCount:" + failCount);
				System.out.println("totalCount:" + customers.size());
				System.out.println("successCount:" + successCount);
				// ---------------------------------------- 替代代码end
				// ------------------------------------------

				if (successCount >= 1) {
					map.put("flag", true);
				} else {
					map.put("flag", false);
				}
				String msgTypeName = null;
				String operationTypeName = null;
				if (operationType == 1) {
					msgTypeName = "导入了";
					operationTypeName = "系统";
				} else if (operationType == 2) {
					msgTypeName = "释放了";
					operationTypeName = "公共池";
				}

				String msg = "";
				msg += "总共解析了" + customerRosterCount + "行客户数据";
				msg += "\n成功" + msgTypeName + successCount + "个客户到" + operationTypeName + "中!";
				if (phoneUniqueErrorCount > 0) {
					msg += "\n其中有" + phoneUniqueErrorCount + "个客户的电话号码重复,未导入到" + operationTypeName + "!";
					map.put("repeatPhones", dbRepeatPhones);
				}
				if (failCount > 0) {
					msg += "\n" + failCount + "个客户导入失败!";
				}
				map.put("msg", msg);
				return map;
			} catch (Exception e) {
				System.out.println("添加失败!");
				e.printStackTrace();
				map.put("msg", "添加失败!失败原因:" + e.getMessage());
				return map;
			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
			map.put("msg", "格式无效,请坚持您的Excel表格数据!");
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			map.put("msg", "解析Excel失败!");
			return map;
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("msg", "解析Excel失败!");
			return map;
		}
	}
}
