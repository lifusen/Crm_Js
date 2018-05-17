package cn.itproject.crm.job;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.itproject.crm.service.CustomerService;

public class ReleaseJob {
	private static Logger logger = Logger.getLogger(ReleaseJob.class);
	@Resource
	private CustomerService customerService;
	/**
	 * 释放到公共池
	 */
	public void releaseCommonPool(){
		logger.info("begin releaseCommonPool...");
		try {
			List<Integer> ids = customerService.getListByFllowDate();
			
			if (ids==null || ids.size()<=0) {
				logger.info("not find data list...");
				return ;
			}
			customerService.releaseCommonPool(ids);
			logger.info("release id list is:"+ids+"");
		} catch (Exception e) {
			logger.info("get customer error...",e);
		}
	}
}
