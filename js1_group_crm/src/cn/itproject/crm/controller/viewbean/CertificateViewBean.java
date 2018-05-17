package cn.itproject.crm.controller.viewbean;

import java.io.Serializable;

/**
 * 证件视图Bean
 * @author MrLiu
 *
 */
public class CertificateViewBean implements Serializable{
	private static final long serialVersionUID = 8231482549105475953L;
	private Integer id;					//证件ID
	private String name;				//证件名称
	private String srcfileName;			//源文件名称
	private String path;				//保存路径
	public CertificateViewBean() {
		super();
	}
	public CertificateViewBean(Integer id, String name, String srcfileName,
			String path) {
		super();
		this.id = id;
		this.name = name;
		this.srcfileName = srcfileName;
		this.path = path;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrcfileName() {
		return srcfileName;
	}
	public void setSrcfileName(String srcfileName) {
		this.srcfileName = srcfileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return id+"-"+name+"-"+srcfileName+"-"+path;
	}
}
