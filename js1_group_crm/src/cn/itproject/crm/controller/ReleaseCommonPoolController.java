package cn.itproject.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itproject.crm.bean.Department;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.DepartmentService;
import cn.itproject.crm.service.ReleaseCommonPoolService;

@Controller
@RequestMapping("releaseCommonPool")
public class ReleaseCommonPoolController extends BaseController {
	private Logger log = Logger.getLogger(ReleaseCommonPoolController.class);
	@Resource
	private ReleaseCommonPoolService releaseCommonPoolService;
	@Resource
	private DepartmentService departmentService;

	/**
	 * 批量释放到公共池
	 * 
	 * @param departmentId
	 * @param employeeId
	 * @param customerLevel
	 * @param attentionLevel
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchRelease")
	@ResponseBody
	public Boolean batchRelease(Integer departmentId, Integer employeeId, String customerLevel, String attentionLevel,
			Model model) throws Exception {
		try {
			departmentId = departmentId==0?null:departmentId;
			employeeId = employeeId==0?null:employeeId;
			customerLevel = customerLevel.equals("0")?null:customerLevel;
			attentionLevel = attentionLevel.equals("0")?null:attentionLevel;
			Integer releaseId = getLoginEmployee().getId();
			releaseCommonPoolService.batchRelease(departmentId, employeeId, customerLevel, attentionLevel, releaseId);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 获取批量移交的数量
	 * 
	 * @param departmentId
	 * @param employeeId
	 * @param customerLevel
	 * @param attentionLevel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getbatchReleaseCount")
	@ResponseBody
	public Integer getbatchReleaseCount(Integer departmentId, Integer employeeId, String customerLevel,
			String attentionLevel) throws Exception {
		try {
			departmentId = departmentId==0?null:departmentId;
			employeeId = employeeId==0?null:employeeId;
			customerLevel = customerLevel.equals("0")?null:customerLevel;
			attentionLevel = attentionLevel.equals("0")?null:attentionLevel;
			Integer count = releaseCommonPoolService.getBatchReleaseCount(departmentId, employeeId, customerLevel,
					attentionLevel);
			return count;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return 0;
	}
	
	@RequestMapping("/batchReleaseView")
	public String batchReleaseView(Model model) throws Exception{
		List<Department> departments = departmentService.getDeparmentByName("业务");
		System.out.println("batchReleaseView....");
		model.addAttribute("departments",departments);
		return "page/batchRelease/batchRelease";
	}
}
