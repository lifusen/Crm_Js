package cn.itproject.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Config;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Role;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.controller.viewbean.ConfigVO;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.ConfigService;
import cn.itproject.crm.service.RoleService;
import cn.itproject.crm.util.Constant;

/**
 * 参数配置控制器
 * 
 * @author MrLiu
 *
 */
@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {
	private static Logger log = Logger.getLogger(ConfigController.class);
	@Resource
	private ConfigService configService;
	@Resource
	private RoleService roleService;
	@Resource
	private CompanyService companyService;
	
	@RequestMapping("/getConfigInfo")
	public String gotoUpdate(Integer id,Model model) throws Exception{
		
		//获取公共池天数
		Map<String, String> configMap = configService.getConfigMap();
		

		//管理员工拥有客户数量上限
		//获取角色列表
		List<Role> roles = roleService.getRoleList();
		//获取公司
		List<Company> companys = companyService.getAllCompany(1);
		
		model.addAttribute("configMap", configMap);
		model.addAttribute("roles", roles);
		model.addAttribute("companys", companys);
		
		return "page/config/configInfo";
	}
	
	@RequestMapping("/updateConfig")
	@ResponseBody
	public boolean updateConfig(ConfigVO configVO) throws Exception{
		try {
			log.debug(configVO);
			Employee employee = getLoginEmployee();
			Integer employeeId = employee.getId();
			// 修改公共池时间
			Config commonPoolDayConfig = new Config();
			commonPoolDayConfig.setConfigKey(Config.COMMON_POOL_DAY);
			commonPoolDayConfig.setValue(configVO.getCommonPoolDay());
			commonPoolDayConfig.setUpdaterId(employeeId);
			commonPoolDayConfig.setUpdaterName(employee.getName());
			
			configService.updateConfig(commonPoolDayConfig);
			
			// 更新公共池天数
			String commonPoolDayString = configVO.getCommonPoolDay();
			if (StringUtils.isNotBlank(commonPoolDayString)) {
				Integer commonPoolDay = Integer.parseInt(commonPoolDayString);
				setApplication(Constant.COMMON_POOL_DAY, commonPoolDay);
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
