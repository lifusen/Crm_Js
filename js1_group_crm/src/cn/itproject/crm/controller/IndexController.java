package cn.itproject.crm.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itproject.crm.controller.base.BaseController;

/**
 * 首页的控制器
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月14日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
public class IndexController extends BaseController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/index")
	public ModelAndView index(Model model) throws Exception {
		return new ModelAndView("index");
	}
}