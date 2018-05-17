package cn.itproject.crm.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.util.Constant;

/**
 * 客户详细页面控制器
 * 
 * @author MrLiu
 *
 */
@Controller
@Scope("prototype")
public class CustomerDetailController extends BaseController{
	
	
	@RequestMapping("/getCustomerDetailView")
	public String getCustomerDetailView(Integer customerId,String customerDetailToken,Model model) throws Exception{
		model.addAttribute("customerId", customerId);
		model.addAttribute("closeWindow", true);
		
		// 如果参数中的token与登录时生成的token一致,表示通过:可以查看客户详情页面
		String token = (String) getSession(Constant.CUSTOMER_DETAIL_TOKEN);
		if (StringUtils.isNotBlank(customerDetailToken) && StringUtils.isNotBlank(token)
			&& customerDetailToken.equals(token)	
		) {
			return "customerDetail"; // 通过
		}
		return "invalidCustomerDetail"; // 不通过
	}

}
