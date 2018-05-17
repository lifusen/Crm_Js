package cn.itproject.crm.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/testyczj")
public class Testyczj {

	@RequestMapping("/showyczjUi")
	public String showyczjUi(Model model) throws Exception {
		return "page/apply/uploadTest";
	}

}
