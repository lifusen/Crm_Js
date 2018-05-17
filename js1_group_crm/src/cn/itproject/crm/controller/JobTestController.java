package cn.itproject.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.job.ReleaseJob;
import cn.itproject.crm.service.CustomerService;
@Controller
@Scope("prototype")
@RequestMapping("/job")
public class JobTestController extends BaseController{
	private static Logger logger = Logger.getLogger(JobTestController.class);

	@Resource
	private CustomerService customerService;
	/**
	 * 释放到公共池
	 */
	@RequestMapping("test")
	public boolean releaseCommonPool(){
		logger.info("begin releaseCommonPool...");
		try {
			List<Integer> ids = customerService.getListByFllowDate();
			
			if (ids==null || ids.size()<=0) {
				logger.info("not find data list...");
				return false;
			}
			customerService.releaseCommonPool(ids);
			logger.info("release id list is:"+ids+"");
		} catch (Exception e) {
			logger.info("get customer error...",e);
		}
		return true;
	}
}
