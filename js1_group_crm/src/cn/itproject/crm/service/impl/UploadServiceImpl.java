package cn.itproject.crm.service.impl;

import org.springframework.stereotype.Service;

import cn.itproject.crm.service.UploadService;
import cn.itproject.crm.util.fdfs.FastDFSUtils;

@Service
public class UploadServiceImpl implements UploadService {

	// 上传图片
	public String uploadPic(byte[] pic, String name, long size) {
		return FastDFSUtils.uploadPIC(pic, name, size);

	}

}
