package cn.itproject.crm.controller.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.context.ServletContextAware;

import cn.itproject.crm.bean.Company;
import cn.itproject.crm.bean.Notification;
import cn.itproject.crm.cache.NotificationDB;
import cn.itproject.crm.service.CompanyService;
import cn.itproject.crm.service.ConfigService;
import cn.itproject.crm.service.NotificationService;
import cn.itproject.crm.util.Constant;

public class ApplicationInitListener implements ServletContextAware{
	private CompanyService companyService;
	private ConfigService configService;
	private NotificationService notificationService;
	@Override
	public void setServletContext(ServletContext application) {
		System.out.println("\nApplicationInitListener\n");
		try{
			// 获取所有公司信息
			List<Company> companies = companyService.getAllCompany(null);
			System.out.println(companies);
			// 存储所有公司的id-name
			Map<Integer, String> allCompanyNameMap = new HashMap<Integer,String>();
			// 存储所有公司的id-company对象
			Map<Integer, Company> allCompanyMap = new HashMap<Integer,Company>();
			if (companies!=null) {
				for (Company company : companies) {
					Integer id = company.getId();
					String name = company.getName();
					allCompanyNameMap.put(id,name); //id-name
					allCompanyMap.put(id, company); //id-company
				}
			}
			// 获取application对象
			// 将所有公司的list存储到application中
			application.setAttribute("allCompanyList", companies);
			// 将所有公司的id-name存储到application中
			application.setAttribute("allCompanyNameMap", allCompanyNameMap);
			// 将所有公司的id-company存储到application中
			application.setAttribute("allCompanyMap", allCompanyMap);
			System.out.println("\n");
			
			// 获取到公共池时间
			Integer commonPoolDay = configService.getCommonPoolDay();
			application.setAttribute(Constant.COMMON_POOL_DAY, commonPoolDay);
			System.out.println("commonPoolDay:"+commonPoolDay);
			System.out.println("\n");
			
			// 获取未读消息集合添加到缓存中
			List<Notification> notifications = notificationService.getAllUnreadNotificationOfMySQL();
			if (CollectionUtils.isNotEmpty(notifications)) {
				for (Notification notification : notifications) {
					NotificationDB.addNotification(notification);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("\n\nafterPropertiesSet\n\n");
		
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
}
