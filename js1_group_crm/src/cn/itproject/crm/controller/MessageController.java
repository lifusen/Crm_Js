package cn.itproject.crm.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.bean.Message;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.MessageService;

/**
 * 
 * @author yangpeixin
 * 
 * @Date 2017年7月14日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/message")
public class MessageController extends BaseController {

	@Resource
	private MessageService messageService;

	@RequestMapping("/getDesktopList.do")
	public String getDesktopList(Model model) throws Exception {
		Integer pageIndex = 1;
		List<Message> noticeMessages = messageService.getMessages(Message.NOTICE, pageIndex, pageSize);
		List<Message> institutionMessages = messageService.getMessages(Message.INSTITUTION, pageIndex, pageSize);
		List<Message> excuseMessages = messageService.getMessages(Message.EXCUSE, pageIndex, pageSize);
		model.addAttribute("noticeMessages", noticeMessages);
		model.addAttribute("institutionMessages", institutionMessages);
		model.addAttribute("excuseMessages", excuseMessages);
		return "page/message/desktopMessage";
	}

	@RequestMapping("/getList.do")
	public String getList(Integer type, Model model, Integer pageIndex, Integer initPage) throws Exception {
		getMessageList(type, model, pageIndex, pageSize, initPage, "message/getList.do");
		return initPage == null ? "page/message/list" : "page/message/table";
	}

	@RequestMapping("/geBankProductList.do")
	public String geBankProductList(Model model, Integer pageIndex, Integer pageSize, Integer initPage)
			throws Exception {
		System.out.println("获取银行产品列表");
		System.out.println(getContextPath() + "message/geBankProductList.do");
		Integer type = Message.BANK_PRODUCT;
		getMessageList(type, model, pageIndex, pageSize, initPage, getContextPath() + "message/geBankProductList.do");
		return initPage == null ? "page/message/bankproductList" : "page/message/bankproductTable";
	}

	private void getMessageList(Integer type, Model model, Integer pageIndex, Integer pageSize, Integer initPage,
			String url) throws Exception {
		pageIndex = initPageIndex(pageIndex);
		pageSize = initPageSize(pageSize);
		List<Message> messages = messageService.getMessages(type, pageIndex, pageSize);
		Integer count = messageService.getMessagesCount(type);
		model.addAttribute("messages", messages);
		handlerType(model, type);
		builderParam(model, pageIndex, pageSize, count, url + "?initPage=1&type=" + type, "messageListArea");
	}

	@RequestMapping("/gotoAdd")
	public String gotoAdd(Integer type, Model model) throws Exception {
		handlerType(model, type);
		return "page/message/add";
	}

	/**
	 * 处理类型
	 * 
	 * @param model
	 * @param type
	 */
	private void handlerType(Model model, Integer type) {
		model.addAttribute("type", type);
		String typeName = "";
		switch (type) {
		case 0:
			typeName = "公司公告";
			break;
		case 1:
			typeName = "公司制度";
			break;
		case 2:
			typeName = "销售说辞";
			break;
		case 3:
			typeName = "银行产品";
			break;
		default:
			break;
		}
		model.addAttribute("typeName", typeName);
	}

	/**
	 * 添加
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Boolean add(Message message) throws Exception {
		try {
			// 获取登录对象
			Employee employee = getLoginEmployee();
			// 设置发布人
			message.setPublisher(employee);
			// 设置发布时间
			message.setPubdate(new Date());
			// 发布消息
			messageService.addMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/gotoUpdate")
	public String gotoUpdate(Model model, Integer id, Integer type) throws Exception {
		Message message = messageService.getEntity(id);
		model.addAttribute("message", message);
		handlerType(model, type);
		return "page/message/update";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Boolean update(Message message) throws Exception {
		try {
			// 获取登录对象
			Employee employee = getLoginEmployee();
			// 设置发布人
			message.setPublisher(employee);
			// 设置发布时间
			message.setPubdate(new Date());
			messageService.updateMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Boolean delete(Integer id) throws Exception {
		try {
		
			messageService.deleteMessage(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping("/gotoDetail")
	public String gotoDetail(Integer id, Integer type, Integer blank, Model model) throws Exception {
		Message message = messageService.getEntity(id);
		model.addAttribute("message", message);
		System.out.println(message);
		handlerType(model, message.getType());
		if (blank != null) {
			return "page/message/messageDetail";
		} else {
			if (type == null) {
				return "page/message/detail";
			}
		}
		return "page/message/messageModal";
	}
}
