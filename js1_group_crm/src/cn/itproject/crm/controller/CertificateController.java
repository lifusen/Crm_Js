package cn.itproject.crm.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itproject.crm.bean.Certificate;
import cn.itproject.crm.bean.Customer;
import cn.itproject.crm.bean.Employee;
import cn.itproject.crm.controller.base.BaseController;
import cn.itproject.crm.service.CertificateService;
import cn.itproject.crm.util.Constant;
import cn.itproject.crm.util.PropertieFactory;

/**
 * 客户证件控制器
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月22日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/certificate")
public class CertificateController extends BaseController {

	@Resource
	private CertificateService certificateService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@RequestParam(value = "file") MultipartFile file, String certificateName,
			Integer customerId, HttpServletRequest request) {
		System.out.println("upload......");
		System.out.println("certificateName:" + certificateName);
		System.out.println(file);
		System.out.println(file.isEmpty());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());

		// 如果没有文件
		if (file.isEmpty()) {
			return errorMsgMap("请选择文件");
		}
		// 文件内容类型
		String fileType = file.getContentType();
		System.out.println(fileType);

		if (!("application/download".equals(fileType) || "application/zip".equals(fileType)
				|| "application/x-rar-compressed".equals(fileType) || "application/x-7z-compressed".equals(fileType)
				|| "application/octet-stream".equals(fileType))) {
			return errorMsgMap("请上传zip/rar/7z格式的压缩包!");
		}
		// 文件大小,限制为10MB:1024*1024*5=
		long fileSize = file.getSize();
		if (fileSize > (1024 * 1024 * 5)) {
			return errorMsgMap("上传的文件大小不能超过5M！");
		}

		// 年月
		SimpleDateFormat ymDateFormat = new SimpleDateFormat("yyyyMM");
		// 保存路径
		String savePath = PropertieFactory.getProVal("uploadUrl") + File.separator + Constant.CERTIFICATES
				+ File.separator + ymDateFormat.format(new Date());
		System.out.println(savePath);

		File savePathFile = new File(savePath);
		if (!savePathFile.exists()) {
			savePathFile.mkdirs();
		}
		// 源文件名称
		String srcfileName = file.getOriginalFilename();
		// 文件后缀
		String fileSuffix = srcfileName.substring(srcfileName.lastIndexOf("."));

		// 时间-员工ID-客户ID-文件名
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		String currentDateString = dateFormat.format(new Date());
		Employee employee = getLoginEmployee();

		String newFileName = currentDateString + "_e" + employee.getId() + "_c" + customerId + fileSuffix;
		System.out.println(newFileName);

		// 最终保存的文件对象
		File saveFile = new File(savePathFile, newFileName);
		try {
			// 保存证件
			file.transferTo(saveFile);
			// 记录到数据库中
			Certificate certificate = new Certificate();
			certificate.setName(certificateName); // 证件名称
			certificate.setSrcfileName(srcfileName); // 源文件名称
			certificate.setPath(newFileName); // 保存路径
			certificate.setCreateTime(new Date()); // 创建时间
			certificate.setCustomer(new Customer(customerId)); // 属于哪个客户
			// 保存
			Serializable certificateId = certificateService.addEntity(certificate);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", certificateId);
			map.put("name", certificateName);
			map.put("path", newFileName);
			map.put("srcfileName", srcfileName);
			System.out.println(map);
			return msgMap(map);
		} catch (Exception e) {
			e.printStackTrace();
			return msgMap("添加证件失败!");
		}
	}

	@RequestMapping("/download")
	public void download(String path, HttpServletResponse response) throws Exception {
		System.out.println("download........");
		if (path == null || path.trim().equals("")) {
			return;
		}
		// 获取到目录
		String dir = path.substring(0, 6);
		System.out.println("dir:" + dir);

		// 获取到文件的访问路径
		String filePath = PropertieFactory.getProVal("uploadUrl") + File.separator + Constant.CERTIFICATES
				+ File.separator + dir + File.separator + path;
		System.out.println("filePath:" + filePath);

		// 下载
		File downloadFile = new File(filePath);
		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=" + path);
		response.setContentType("application/octet-stream; charset=utf-8");
		os.write(FileUtils.readFileToByteArray(downloadFile));
		os.flush();

	}

}
