package cn.itproject.crm.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.itproject.crm.service.ApplyPersonService;
import cn.itproject.crm.service.UploadService;
import cn.itproject.crm.util.web.Constants;

/**
 * 上传图片
 * 
 * @author yangpeixin
 * 
 * @Date 2017年6月22日
 *
 *       version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	private UploadService uploadService;

	@Autowired
	private ApplyPersonService applyPersonService;

	@RequestMapping("/uploadPic")
	public void uploadPic(@RequestParam(required = false) MultipartFile pic, HttpServletResponse response)
			throws IOException, JSONException {

		String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());

		String url = Constants.IMG_URL + path;
		System.out.println(url);

		JSONObject jo = new JSONObject();

		jo.put("situation", url);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}

	/**
	 * 下载文件
	 * 
	 * @param id
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download/{id}")
	public ResponseEntity<byte[]> downLoadPerson(@PathVariable Integer id, HttpServletRequest request)
			throws Exception {

		String url = applyPersonService.getPersonById(id);
		String filename = applyPersonService.getPersonNameById(id);
		System.out.println(url);
		// 找到服务器磁盘上的要下载文件，将文件读取到字节数组中
		File file = new File(request.getServletContext().getRealPath(url));
		byte[] byteArrOfFile = FileUtils.readFileToByteArray(file);

		// 创建针对下载响应的响应头信息（内容类型、内容形式、内容大小）
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		headers.setContentDispositionFormData("attachment", URLEncoder.encode(filename, "utf-8"));
		headers.setContentLength(file.length());

		return new ResponseEntity<byte[]>(byteArrOfFile, headers, HttpStatus.CREATED);

	}
}
